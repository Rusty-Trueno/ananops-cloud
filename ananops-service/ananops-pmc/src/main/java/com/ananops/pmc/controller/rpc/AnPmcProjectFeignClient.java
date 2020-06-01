package com.ananops.pmc.controller.rpc;

import com.ananops.common.core.domain.R;
import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.common.utils.Threads;
import com.ananops.common.utils.bean.BeanUtils;
import com.ananops.pmc.domain.AnPmcProject;
import com.ananops.pmc.dto.PmcProjectDto;
import com.ananops.pmc.service.impl.AnPmcProjectServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @CreatedTime 2020/5/30 16:10
 **/
@RefreshScope
@RestController
@Api(value = "API - AnPmcProjectFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AnPmcProjectFeignClient {

    private static final Logger logger = LoggerFactory.getLogger(Threads.class);

    @Resource
    private AnPmcProjectServiceImpl anPmcProjectService;

    @ApiOperation(httpMethod = "POST", value = "根据项目Id查询项目信息")
    public R getProjectByProjectId(@PathVariable(value = "projectId") Long projectId) {
        logger.info("getProjectByProjectId - 根据项目Id查询项目信息. projectId={}", projectId);
        AnPmcProject anPmcProject = anPmcProjectService.selectAnPmcProjectById(projectId);
        PmcProjectDto pmcProjectDto = new PmcProjectDto();
        BeanUtils.copyProperties(anPmcProject, pmcProjectDto);
        logger.info("getProjectByProjectId - 根据项目Id查询项目信息. [OK] pmcProjectDto={}", pmcProjectDto);
        return R.data(pmcProjectDto);
    }

    @ApiOperation(httpMethod = "POST", value = "根据公司部门id查询项目信息")
    public R getProjectListByGroupId(@PathVariable(value = "companyId") Long companyId) {
        List<AnPmcProject> pmcProjectList = anPmcProjectService.getProjectListByCompanyId(companyId);
        List<PmcProjectDto> pmcProjectDtoList = new ArrayList<>();
        BeanUtils.copyProperties(pmcProjectList, pmcProjectDtoList);
        return R.data(pmcProjectDtoList);
    }

    @ApiOperation(httpMethod = "POST", value = "保存项目")
    public R saveProject(@RequestBody PmcProjectDto pmcProjectDto) {
//        LoginAuthDto loginAuthDto = getLoginAuthDto();
        LoginAuthDto loginAuthDto = new LoginAuthDto();
        loginAuthDto.setUserId((long) 1);
        loginAuthDto.setUserName("admin");
        AnPmcProject anPmcProject = new AnPmcProject();
        BeanUtils.copyProperties(pmcProjectDto, anPmcProject);
        int result = anPmcProjectService.insertAnPmcProject(anPmcProject, loginAuthDto);
        return R.data(result);
    }
}
