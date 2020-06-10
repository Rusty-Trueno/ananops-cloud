package com.ananops.system.feign;

import com.ananops.common.constant.ServiceNameConstants;
import com.ananops.common.core.domain.R;
import com.ananops.system.domain.SysOss;
import com.ananops.system.dto.FileUploadDto;
import com.ananops.system.feign.factory.RemoteOssFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * Created by rongshuai on 2020/6/10 13:36
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteOssFallbackFactory.class)
public interface RemoteOssService {

    @PostMapping("oss/remoteUpload")
    public SysOss editSave(@RequestBody FileUploadDto fileUploadDto);

}
