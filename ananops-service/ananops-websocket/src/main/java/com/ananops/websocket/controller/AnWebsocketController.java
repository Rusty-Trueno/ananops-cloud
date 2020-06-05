package com.ananops.websocket.controller;

import com.ananops.common.core.controller.BaseController;
import com.ananops.websocket.dto.MsgDto;
import com.ananops.websocket.service.WebsocketPushMsgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by rongshuai on 2020/6/5 16:31
 */
@RestController
@RequestMapping("websocket")
@Api("websocket")
public class AnWebsocketController extends BaseController {

    @Autowired
    private WebsocketPushMsgService websocketPushMsgService;

    @ApiOperation(value = "create")
    @PostMapping("create")
    public void createWebsocketMsg (@RequestBody MsgDto msgDto) {
        logger.info("msgDto is {}",msgDto);
        websocketPushMsgService.SendMsgToFrontend(msgDto);
    }
}
