package com.ananops.websocket.service;

import com.ananops.websocket.dto.MsgDto;

/**
 * Created by rongshuai on 2020/6/5 17:55
 */
public interface WebsocketPushMsgService {

    void SendMsgToFrontend(MsgDto msg);
}
