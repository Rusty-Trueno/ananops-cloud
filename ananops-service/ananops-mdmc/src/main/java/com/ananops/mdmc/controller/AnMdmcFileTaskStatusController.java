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
import com.ananops.mdmc.domain.AnMdmcFileTaskStatus;
import com.ananops.mdmc.service.IAnMdmcFileTaskStatusService;

/**
 * 不同状态工单上传文件 提供者
 *
 * @author ananops
 * @date 2020-05-25
 */
@RestController
@RequestMapping("不同状态工单上传文件")
@Api("不同状态工单上传文件")
public class AnMdmcFileTaskStatusController extends BaseController
{

    @Autowired
    private IAnMdmcFileTaskStatusService anMdmcFileTaskStatusService;

    /**
     * 查询工单状态文件关联表
     */
    @ApiOperation(value = "查询工单状态文件关联表")
    @GetMapping("get/{id}")
    public AnMdmcFileTaskStatus get(@PathVariable("id") Long id)
    {
        return anMdmcFileTaskStatusService.selectAnMdmcFileTaskStatusById(id);

    }

    /**
     * 查询不同状态工单上传文件列表
     */
    @ApiOperation(value = "查询不同状态工单上传文件列表")
    @GetMapping("list")
    public R list(AnMdmcFileTaskStatus anMdmcFileTaskStatus)
    {
        startPage();
        return result(anMdmcFileTaskStatusService.selectAnMdmcFileTaskStatusList(anMdmcFileTaskStatus));
    }


    /**
     * 新增保存不同状态工单上传文件
     */
    @ApiOperation(value = "新增保存不同状态工单上传文件")
    @PostMapping("save")
    public R addSave(@RequestBody AnMdmcFileTaskStatus anMdmcFileTaskStatus)
    {
        return toAjax(anMdmcFileTaskStatusService.insertAnMdmcFileTaskStatus(anMdmcFileTaskStatus));
    }

    /**
     * 修改保存不同状态工单上传文件
     */
    @ApiOperation(value = "修改保存不同状态工单上传文件")
    @PostMapping("update")
    public R editSave(@RequestBody AnMdmcFileTaskStatus anMdmcFileTaskStatus)
    {
        return toAjax(anMdmcFileTaskStatusService.updateAnMdmcFileTaskStatus(anMdmcFileTaskStatus));
    }

    /**
     * 删除工单状态文件关联表
     */
    @ApiOperation(value = "删除工单状态文件关联表")
    @PostMapping("remove")
    public R remove(String ids)
    {
        return toAjax(anMdmcFileTaskStatusService.deleteAnMdmcFileTaskStatusByIds(ids));
    }

}
