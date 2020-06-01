package com.ananops.pmc.controller.rpc;

import com.ananops.common.core.domain.R;
import com.ananops.common.utils.bean.BeanUtils;
import com.ananops.pmc.domain.AnPmcProject;
import com.ananops.pmc.domain.AnPmcProjectUser;
import com.ananops.pmc.dto.PmcBatchProUser;
import com.ananops.pmc.dto.PmcProjectUserDto;
import com.ananops.pmc.service.impl.AnPmcProjectServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangyue
 * @CreatedTime 2020/5/30 16:03
 **/
@RefreshScope
@RestController
@Api(value = "API -  AnPmcProjectEngineerFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AnPmcProjectEngineerFeignClient {
    @Resource
    private AnPmcProjectServiceImpl anPmcProjectService;


    @ApiOperation(httpMethod = "POST", value = "批量插入项目工程师关系")
    public R saveProUserList(@RequestBody PmcBatchProUser pmcBatchProUser) {
        int result = 0;
        List<PmcProjectUserDto> pmcProjectUserDtoList = pmcBatchProUser.getPmcProjectUserDtoList();
        List<AnPmcProjectUser> anPmcProjectUserList = new ArrayList<AnPmcProjectUser>();
        BeanUtils.copyProperties(pmcProjectUserDtoList, anPmcProjectUserList);
        for (AnPmcProjectUser anPmcProjectUser : anPmcProjectUserList) {
            int count = anPmcProjectService.addProUser(anPmcProjectUser);
            result += count;
        }
        return R.data(result);
    }


    @ApiOperation(httpMethod = "POST", value = "根据项目id获取工程师id列表")
    public R getEngineersIdByProjectId(@PathVariable(value = "projectId") Long projectId) {
        List<Long> engineersId = anPmcProjectService.getEngineersIdByProjectId(projectId);
        return R.data(engineersId);
    }
}
