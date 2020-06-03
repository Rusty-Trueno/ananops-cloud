package com.ananops.system.controller;

import com.ananops.base.dto.MessageQueryDto;
import com.ananops.base.dto.MqMessageVo;
import com.ananops.provider.service.MqMessageService;
import com.ananops.wrapper.WrapMapper;
import com.ananops.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created By ChengHao On 2020/6/3
 */
@Slf4j
@RestController
@RequestMapping("message")
public class SysMqController {
    @Resource
    private MqMessageService mqMessageService;

    @PostMapping("/queryMessageKeyList")
    public Wrapper<List<String>> queryMessageKeyList(@RequestParam("messageKeyList") List<String> messageKeyList) {
        log.info("查询消息KEY. messageKeyList={}", messageKeyList);
        return WrapMapper.ok(mqMessageService.queryMessageKeyList(messageKeyList));
    }

    @PostMapping("/queryMessageListWithPage")
    public Wrapper<PageInfo<MqMessageVo>> queryMessageListWithPage(@RequestBody MessageQueryDto messageQueryDto) {
        return mqMessageService.queryMessageListWithPage(messageQueryDto);
    }
}
