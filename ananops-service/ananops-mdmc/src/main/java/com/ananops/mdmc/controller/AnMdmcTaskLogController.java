package com.ananops.mdmc.controller;

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
import com.ananops.mdmc.domain.AnMdmcTaskLog;
import com.ananops.mdmc.service.IAnMdmcTaskLogService;

/**
 * 维修工单日志 提供者
 *
 * @author ananops
 * @date 2020-05-25
 */
@RestController
@RequestMapping("维修工单日志")
@Api("维修工单日志")
public class AnMdmcTaskLogController extends BaseController
{

    @Autowired
    private IAnMdmcTaskLogService anMdmcTaskLogService;

    /**
     * 查询维修工单日志表
     */
    @ApiOperation(value = "查询维修工单日志表")
    @GetMapping("get/{id}")
    public AnMdmcTaskLog get(@PathVariable("id") Long id)
    {
        return anMdmcTaskLogService.selectAnMdmcTaskLogById(id);

    }

    /**
     * 查询维修工单日志列表
     */
    @ApiOperation(value = "查询维修工单日志列表")
    @GetMapping("list")
    public R list(AnMdmcTaskLog anMdmcTaskLog)
    {
        startPage();
        return result(anMdmcTaskLogService.selectAnMdmcTaskLogList(anMdmcTaskLog));
    }


    /**
     * 新增保存维修工单日志
     */
    @ApiOperation(value = "新增保存维修工单日志")
    @PostMapping("save")
    public R addSave(@RequestBody AnMdmcTaskLog anMdmcTaskLog)
    {
        return toAjax(anMdmcTaskLogService.insertAnMdmcTaskLog(anMdmcTaskLog));
    }

    /**
     * 修改保存维修工单日志
     */
    @ApiOperation(value = "修改保存维修工单日志")
    @PostMapping("update")
    public R editSave(@RequestBody AnMdmcTaskLog anMdmcTaskLog)
    {
        return toAjax(anMdmcTaskLogService.updateAnMdmcTaskLog(anMdmcTaskLog));
    }

    /**
     * 删除维修工单日志表
     */
    @ApiOperation(value = "删除维修工单日志表")
    @PostMapping("remove")
    public R remove(String ids)
    {
        return toAjax(anMdmcTaskLogService.deleteAnMdmcTaskLogByIds(ids));
    }

}
