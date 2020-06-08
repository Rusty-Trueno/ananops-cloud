package com.ananops.websocket.controller;

import com.ananops.common.core.controller.BaseController;
import com.ananops.common.core.domain.R;
import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.websocket.dto.MsgDto;
import com.ananops.websocket.dto.MsgStatusChangeDto;
import com.ananops.websocket.dto.WsMsgQueryDto;
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

    @ApiOperation(value = "getWsMsg")
    @PostMapping("getWsMsg")
    public R getWsMsg(@RequestBody WsMsgQueryDto queryDto) {
        logger.info("queryDto is {}",queryDto);
        return result(websocketMsgService.getWebsocketMsg(queryDto));
    }

    @ApiOperation(value = "changeWsMsgStatus")
    @PostMapping("changeWsMsgStatus")
    public R changeWsMsgStatus(@RequestBody MsgStatusChangeDto statusChangeDto) {
        logger.info("statusChangeDto is {}",statusChangeDto);
        return R.data(websocketMsgService.changeMsgStatus(statusChangeDto));
    }
}
