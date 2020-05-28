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
import com.ananops.mdmc.domain.AnMdmcTaskItemLog;
import com.ananops.mdmc.service.IAnMdmcTaskItemLogService;

import javax.annotation.Resource;

/**
 * 维修任务子项日志 提供者
 *
 * @author ananops
 * @date 2020-05-25
 */
@RestController
@RequestMapping("维修任务子项日志")
@Api("维修任务子项日志")
public class AnMdmcTaskItemLogController extends BaseController
{

    @Resource
    private IAnMdmcTaskItemLogService anMdmcTaskItemLogService;

    /**
     * 查询维修任务子项日志
     */
    @ApiOperation(value = "查询维修任务子项日志")
    @GetMapping("get/{id}")
    public AnMdmcTaskItemLog get(@PathVariable("id") Long id)
    {
        return anMdmcTaskItemLogService.selectAnMdmcTaskItemLogById(id);

    }

    /**
     * 查询维修任务子项日志列表
     */
    @ApiOperation(value = "查询维修任务子项日志列表")
    @GetMapping("list")
    public R list(AnMdmcTaskItemLog anMdmcTaskItemLog)
    {
        startPage();
        return result(anMdmcTaskItemLogService.selectAnMdmcTaskItemLogList(anMdmcTaskItemLog));
    }


    /**
     * 新增保存维修任务子项日志
     */
    @ApiOperation(value = "新增保存维修任务子项日志")
    @PostMapping("save")
    public R addSave(@RequestBody AnMdmcTaskItemLog anMdmcTaskItemLog)
    {
        return toAjax(anMdmcTaskItemLogService.insertAnMdmcTaskItemLog(anMdmcTaskItemLog));
    }

    /**
     * 修改保存维修任务子项日志
     */
    @ApiOperation(value = "修改保存维修任务子项日志")
    @PostMapping("update")
    public R editSave(@RequestBody AnMdmcTaskItemLog anMdmcTaskItemLog)
    {
        return toAjax(anMdmcTaskItemLogService.updateAnMdmcTaskItemLog(anMdmcTaskItemLog));
    }

    /**
     * 删除维修任务子项日志
     */
    @ApiOperation(value = "删除维修任务子项日志")
    @PostMapping("remove")
    public R remove(String ids)
    {
        return toAjax(anMdmcTaskItemLogService.deleteAnMdmcTaskItemLogByIds(ids));
    }

}
