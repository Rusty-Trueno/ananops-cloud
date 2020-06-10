package com.ananops.system.feign.factory;



import com.ananops.common.core.domain.R;
import com.ananops.system.dto.FileUploadDto;
import com.ananops.system.feign.RemoteOssService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * Created by rongshuai on 2020/6/10 13:37
 */
@Slf4j
@Component
public class RemoteOssFallbackFactory implements FallbackFactory<RemoteOssService> {

    @Override
    public RemoteOssService create(Throwable throwable) {
        log.error(throwable.getMessage());
        return new RemoteOssService()
        {

            @Override
            public R editSave(FileUploadDto fileUploadDto)
            {
                return null;
            }
        };
    }
}
