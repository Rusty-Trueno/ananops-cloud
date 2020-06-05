package com.ananops.websocket.service.impl;

import com.ananops.websocket.dto.MsgDto;
import com.ananops.websocket.service.WebsocketPushMsgService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by rongshuai on 2020/6/5 17:55
 */
@Service
public class WebsocketPushMsgServiceImpl implements WebsocketPushMsgService {

    @Resource
    private SimpMessagingTemplate template;

    @Override
    public void SendMsgToFrontend(MsgDto msgDto){
        String dest = "/queue/" + msgDto.getId();
        System.out.println(dest);
        template.convertAndSend(dest,msgDto);
    }
}
