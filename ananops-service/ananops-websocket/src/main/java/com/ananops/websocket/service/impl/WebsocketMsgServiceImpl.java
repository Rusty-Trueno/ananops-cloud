package com.ananops.websocket.service.impl;

import com.alibaba.fastjson.JSON;
import com.ananops.common.config.PcObjectMapper;
import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.common.exception.BusinessException;
import com.ananops.common.utils.bean.UpdateInfoUtil;
import com.ananops.websocket.domain.AnWebsocketMsg;
import com.ananops.websocket.dto.MsgDto;
import com.ananops.websocket.dto.MsgStatusChangeDto;
import com.ananops.websocket.dto.WsMsgQueryDto;
import com.ananops.websocket.enums.WsMsgStatusEnum;
import com.ananops.websocket.mapper.AnWebsocketMsgMapper;
import com.ananops.websocket.service.WebsocketMsgService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by rongshuai on 2020/6/5 17:55
 */
@Service
public class WebsocketMsgServiceImpl implements WebsocketMsgService {

    @Resource
    private SimpMessagingTemplate template;

    @Resource
    private AnWebsocketMsgMapper anWebsocketMsgMapper;

    @Override
    public void SendMsgToFrontend(MsgDto msgDto){
        try {
            AnWebsocketMsg anWebsocketMsg = new AnWebsocketMsg();
            UpdateInfoUtil.setInsertInfo(anWebsocketMsg,msgDto.getUser());
            ObjectMapper objectMapper = PcObjectMapper.getObjectMapper();
            String msg = objectMapper.writeValueAsString(msgDto.getMsg());
            anWebsocketMsg.setMsg(msg);
            anWebsocketMsg.setMsgType(msgDto.getMsgType());
            anWebsocketMsg.setUserId(Long.valueOf(msgDto.getId()));
            anWebsocketMsg.setStatus(WsMsgStatusEnum.WAITING_FOR_CONSUME.getStatusNum());
            anWebsocketMsgMapper.insert(anWebsocketMsg);
            String dest = "/queue/" + msgDto.getId();
            System.out.println(dest);
            template.convertAndSend(dest,msgDto);
        } catch (Exception e) {
            throw new BusinessException("消息推送失败");
        }
    }

    @Override
    public List<AnWebsocketMsg> getWebsocketMsg(WsMsgQueryDto queryDto) {
        Long userId = queryDto.getUserId();
        Integer status = queryDto.getStatus();
        String msgType = queryDto.getMsgType();
        return anWebsocketMsgMapper.getWebsocketMsg(userId,status,msgType);
    }

    @Override
    public int changeMsgStatus(MsgStatusChangeDto statusChangeDto) {
        Long id = statusChangeDto.getId();
        Integer status = statusChangeDto.getStatus();
        if (null != id && null != status) {
            if (status == WsMsgStatusEnum.WAITING_FOR_CONSUME.getStatusNum() || status == WsMsgStatusEnum.CONSUMED.getStatusNum()) {
                AnWebsocketMsg anWebsocketMsg = new AnWebsocketMsg();
                anWebsocketMsg.setStatus(status);
                anWebsocketMsg.setId(id);
                return anWebsocketMsgMapper.updateByPrimaryKeySelective(anWebsocketMsg);
            } else {
                throw new BusinessException("参数异常");
            }
        } else {
            throw new BusinessException("参数异常");
        }
    }
}
