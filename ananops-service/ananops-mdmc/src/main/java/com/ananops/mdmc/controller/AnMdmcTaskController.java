package com.ananops.mdmc.controller;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.mdmc.dto.MdmcAddTaskDto;
import com.ananops.mdmc.dto.MdmcQueryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.ananops.common.core.domain.R;
import com.ananops.common.core.controller.BaseController;
import com.ananops.mdmc.domain.AnMdmcTask;
import com.ananops.mdmc.service.IAnMdmcTaskService;

/**
 * 维修工单 提供者
 *
 * @author ananops
 * @date 2020-05-25
 */
@RestController
@RequestMapping("mdmcTask")
@Api("维修工单")
public class AnMdmcTaskController extends BaseController
{

    @Autowired
    private IAnMdmcTaskService anMdmcTaskService;

    /**
     * 查询维修工单表
     */
    @ApiOperation(value = "查询维修工单表")
    @GetMapping("get/{id}")
    public AnMdmcTask get(@PathVariable("id") Long id)
    {
        return anMdmcTaskService.selectAnMdmcTaskById(id);

    }

    /**
     * 查询维修工单列表
     */
    @ApiOperation(value = "查询维修工单列表")
    @GetMapping("getTaskList")
    public R list(MdmcQueryDto queryDto)
    {
        startPage();
        return result(anMdmcTaskService.selectAnMdmcTaskList(queryDto));
    }


    /**
     * 新增保存维修工单
     */
    @ApiOperation(value = "新增保存维修工单")
    @PostMapping("save")
    public R addSave(@RequestBody MdmcAddTaskDto mdmcAddTaskDto)
    {
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        return R.data(anMdmcTaskService.insertAnMdmcTask(mdmcAddTaskDto,loginAuthDto));
    }

    /**
     * 修改保存维修工单
     */
    @ApiOperation(value = "修改保存维修工单")
    @PostMapping("update")
    public R editSave(@RequestBody MdmcAddTaskDto updateTaskDto)
    {
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        return R.data(anMdmcTaskService.updateAnMdmcTask(updateTaskDto,loginAuthDto));
    }

    /**
     * 删除维修工单表
     */
    @ApiOperation(value = "删除维修工单表")
    @PostMapping("remove")
    public R remove(String ids)
    {
        return toAjax(anMdmcTaskService.deleteAnMdmcTaskByIds(ids));
    }


}
