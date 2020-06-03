package com.ananops.system.feign.factory;

import com.ananops.system.feign.RemoteMqMessageService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created By ChengHao On 2020/6/3
 */
@Slf4j
@Component
public class RemoteMqMessageFallbackFactory implements FallbackFactory<RemoteMqMessageService> {
    @Override
    public RemoteMqMessageService create(Throwable throwable) {
        log.error(throwable.getMessage());
        return null;
    }
}
