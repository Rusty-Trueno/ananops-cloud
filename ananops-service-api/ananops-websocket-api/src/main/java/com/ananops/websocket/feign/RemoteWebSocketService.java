package com.ananops.websocket.feign;

import com.ananops.common.constant.ServiceNameConstants;
import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.websocket.dto.MsgDto;
import com.ananops.websocket.feign.factory.RemoteWebSocketFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by rongshuai on 2020/6/5 20:35
 */
@FeignClient(name = ServiceNameConstants.WEBSOCKET_SERVICE, fallbackFactory = RemoteWebSocketFallbackFactory.class)
public interface RemoteWebSocketService {
    @PostMapping("websocket/create")
    public Boolean createWebsocketMsg (MsgDto msgDto);
}
