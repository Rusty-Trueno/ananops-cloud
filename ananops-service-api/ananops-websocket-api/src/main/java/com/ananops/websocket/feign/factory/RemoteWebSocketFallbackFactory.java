package com.ananops.websocket.feign.factory;

import com.ananops.websocket.dto.MsgDto;
import com.ananops.websocket.feign.RemoteWebSocketService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;

/**
 * Created by rongshuai on 2020/6/5 20:36
 */
@Slf4j
@Component
public class RemoteWebSocketFallbackFactory implements FallbackFactory<RemoteWebSocketService> {

    @Override
    public RemoteWebSocketService create(Throwable throwable) {
        log.error(throwable.getMessage());
        return new RemoteWebSocketService() {
            @Override
            public Boolean createWebsocketMsg (MsgDto msgDto) {
                return null;
            }
        };
    }
}
