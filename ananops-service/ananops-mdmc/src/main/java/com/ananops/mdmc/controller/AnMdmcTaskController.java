package com.ananops.mdmc.controller;

import com.ananops.common.core.dto.LoginAuthDto;
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
 * @date 2020-05-22
 */
@RestController
@RequestMapping("task")
@Api("维修工单")
public class AnMdmcTaskController extends BaseController
{

    @Autowired
    private IAnMdmcTaskService anMdmcTaskService;

    /**
     * 查询
     */
    @ApiOperation(value = "根据id查询工单")
    @GetMapping("get/{id}")
    public AnMdmcTask get(@PathVariable("id") Long id)
    {
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        logger.info("currentUser is {}",loginAuthDto);
        return anMdmcTaskService.selectAnMdmcTaskById(id);

    }

    /**
     * 查询维修工单列表
     */
    @ApiOperation(value = "查询维修工单列表")
    @GetMapping("list")
    public R list(AnMdmcTask anMdmcTask)
    {
        startPage();
        return result(anMdmcTaskService.selectAnMdmcTaskList(anMdmcTask));
    }


    /**
     * 新增保存维修工单
     */
    @ApiOperation(value = "新增保存维修工单")
    @PostMapping("save")
    public R addSave(@RequestBody AnMdmcTask anMdmcTask)
    {
        return toAjax(anMdmcTaskService.insertAnMdmcTask(anMdmcTask));
    }

    /**
     * 修改保存维修工单
     */
    @ApiOperation(value = "修改保存维修工单")
    @PostMapping("update")
    public R editSave(@RequestBody AnMdmcTask anMdmcTask)
    {
        return toAjax(anMdmcTaskService.updateAnMdmcTask(anMdmcTask));
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除工单")
    @PostMapping("remove")
    public R remove(String ids)
    {
        return toAjax(anMdmcTaskService.deleteAnMdmcTaskByIds(ids));
    }

}
