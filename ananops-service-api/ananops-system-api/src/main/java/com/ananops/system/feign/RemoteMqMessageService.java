package com.ananops.system.feign;

import com.ananops.base.dto.MessageQueryDto;
import com.ananops.base.dto.MqMessageVo;
import com.ananops.common.constant.ServiceNameConstants;
import com.ananops.system.feign.factory.RemoteMqMessageFallbackFactory;
import com.ananops.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Created By ChengHao On 2020/6/3
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteMqMessageFallbackFactory.class)
public interface RemoteMqMessageService {

    /**
     * Query waiting confirm message list wrapper.
     *
     * @param messageKeyList the message key list
     *
     * @return the wrapper
     */
    @PostMapping(value = "/message/queryMessageKeyList")
    Wrapper<List<String>> queryMessageKeyList(List<String> messageKeyList);

    /**
     * Query message list with page wrapper.
     *
     * @param messageQueryDto the message query dto
     *
     * @return the wrapper
     */
    @PostMapping(value = "/message/queryMessageListWithPage")
    Wrapper<PageInfo<MqMessageVo>> queryMessageListWithPage(@RequestBody MessageQueryDto messageQueryDto);
}
