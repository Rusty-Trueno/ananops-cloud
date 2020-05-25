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
import com.ananops.mdmc.domain.AnMdmcTroubleaddressGroup;
import com.ananops.mdmc.service.IAnMdmcTroubleaddressGroupService;

/**
 * 关联故障地址与组织 提供者
 *
 * @author ananops
 * @date 2020-05-25
 */
@RestController
@RequestMapping("关联故障地址与组织")
@Api("关联故障地址与组织")
public class AnMdmcTroubleaddressGroupController extends BaseController
{

    @Autowired
    private IAnMdmcTroubleaddressGroupService anMdmcTroubleaddressGroupService;

    /**
     * 查询故障地址组织关联表
     */
    @ApiOperation(value = "查询故障地址组织关联表")
    @GetMapping("get/{id}")
    public AnMdmcTroubleaddressGroup get(@PathVariable("id") Long id)
    {
        return anMdmcTroubleaddressGroupService.selectAnMdmcTroubleaddressGroupById(id);

    }

    /**
     * 查询关联故障地址与组织列表
     */
    @ApiOperation(value = "查询关联故障地址与组织列表")
    @GetMapping("list")
    public R list(AnMdmcTroubleaddressGroup anMdmcTroubleaddressGroup)
    {
        startPage();
        return result(anMdmcTroubleaddressGroupService.selectAnMdmcTroubleaddressGroupList(anMdmcTroubleaddressGroup));
    }


    /**
     * 新增保存关联故障地址与组织
     */
    @ApiOperation(value = "新增保存关联故障地址与组织")
    @PostMapping("save")
    public R addSave(@RequestBody AnMdmcTroubleaddressGroup anMdmcTroubleaddressGroup)
    {
        return toAjax(anMdmcTroubleaddressGroupService.insertAnMdmcTroubleaddressGroup(anMdmcTroubleaddressGroup));
    }

    /**
     * 修改保存关联故障地址与组织
     */
    @ApiOperation(value = "修改保存关联故障地址与组织")
    @PostMapping("update")
    public R editSave(@RequestBody AnMdmcTroubleaddressGroup anMdmcTroubleaddressGroup)
    {
        return toAjax(anMdmcTroubleaddressGroupService.updateAnMdmcTroubleaddressGroup(anMdmcTroubleaddressGroup));
    }

    /**
     * 删除故障地址组织关联表
     */
    @ApiOperation(value = "删除故障地址组织关联表")
    @PostMapping("remove")
    public R remove(String ids)
    {
        return toAjax(anMdmcTroubleaddressGroupService.deleteAnMdmcTroubleaddressGroupByIds(ids));
    }

}
