package com.ananops.websocket.service.impl;

import com.alibaba.fastjson.JSON;
import com.ananops.common.config.PcObjectMapper;
import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.common.exception.BusinessException;
import com.ananops.common.utils.bean.UpdateInfoUtil;
import com.ananops.websocket.domain.AnWebsocketMsg;
import com.ananops.websocket.dto.MsgDto;
import com.ananops.websocket.mapper.AnWebsocketMsgMapper;
import com.ananops.websocket.service.WebsocketMsgService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
            anWebsocketMsgMapper.insert(anWebsocketMsg);
            String dest = "/queue/" + msgDto.getId();
            System.out.println(dest);
            template.convertAndSend(dest,msgDto);
        } catch (Exception e) {
            throw new BusinessException("消息推送失败");
        }


    }
}
