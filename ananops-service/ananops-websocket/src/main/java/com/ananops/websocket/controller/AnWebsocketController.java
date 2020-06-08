package com.ananops.websocket.controller;

import com.ananops.common.core.controller.BaseController;
import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.websocket.dto.MsgDto;
import com.ananops.websocket.service.WebsocketMsgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by rongshuai on 2020/6/5 16:31
 */
@RestController
@RequestMapping("websocket")
@Api("websocket")
public class AnWebsocketController extends BaseController {

    @Autowired
    private WebsocketMsgService websocketMsgService;

    @ApiOperation(value = "create")
    @PostMapping("create")
    public void createWebsocketMsg (@RequestBody MsgDto msgDto) {
        logger.info("msgDto is {}",msgDto);
        websocketMsgService.SendMsgToFrontend(msgDto);
    }
}
