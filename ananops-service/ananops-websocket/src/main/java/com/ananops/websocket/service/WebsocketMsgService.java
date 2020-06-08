package com.ananops.websocket.service;

import com.ananops.websocket.domain.AnWebsocketMsg;
import com.ananops.websocket.dto.MsgDto;
import com.ananops.websocket.dto.MsgStatusChangeDto;
import com.ananops.websocket.dto.WsMsgQueryDto;

import java.util.List;

/**
 * Created by rongshuai on 2020/6/5 17:55
 */
public interface WebsocketMsgService {

    void SendMsgToFrontend(MsgDto msg);

    List<AnWebsocketMsg> getWebsocketMsg(WsMsgQueryDto queryDto);

    int changeMsgStatus(MsgStatusChangeDto statusChangeDto);
}
