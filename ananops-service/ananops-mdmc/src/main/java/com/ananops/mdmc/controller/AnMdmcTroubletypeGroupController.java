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
import com.ananops.mdmc.domain.AnMdmcTroubletypeGroup;
import com.ananops.mdmc.service.IAnMdmcTroubletypeGroupService;

/**
 * 关联故障类型和组织 提供者
 *
 * @author ananops
 * @date 2020-05-25
 */
@RestController
@RequestMapping("关联故障类型和组织")
@Api("关联故障类型和组织")
public class AnMdmcTroubletypeGroupController extends BaseController
{

    @Autowired
    private IAnMdmcTroubletypeGroupService anMdmcTroubletypeGroupService;

    /**
     * 查询故障类型组织关联表
     */
    @ApiOperation(value = "查询故障类型组织关联表")
    @GetMapping("get/{id}")
    public AnMdmcTroubletypeGroup get(@PathVariable("id") Long id)
    {
        return anMdmcTroubletypeGroupService.selectAnMdmcTroubletypeGroupById(id);

    }

    /**
     * 查询关联故障类型和组织列表
     */
    @ApiOperation(value = "查询关联故障类型和组织列表")
    @GetMapping("list")
    public R list(AnMdmcTroubletypeGroup anMdmcTroubletypeGroup)
    {
        startPage();
        return result(anMdmcTroubletypeGroupService.selectAnMdmcTroubletypeGroupList(anMdmcTroubletypeGroup));
    }


    /**
     * 新增保存关联故障类型和组织
     */
    @ApiOperation(value = "新增保存关联故障类型和组织")
    @PostMapping("save")
    public R addSave(@RequestBody AnMdmcTroubletypeGroup anMdmcTroubletypeGroup)
    {
        return toAjax(anMdmcTroubletypeGroupService.insertAnMdmcTroubletypeGroup(anMdmcTroubletypeGroup));
    }

    /**
     * 修改保存关联故障类型和组织
     */
    @ApiOperation(value = "修改保存关联故障类型和组织")
    @PostMapping("update")
    public R editSave(@RequestBody AnMdmcTroubletypeGroup anMdmcTroubletypeGroup)
    {
        return toAjax(anMdmcTroubletypeGroupService.updateAnMdmcTroubletypeGroup(anMdmcTroubletypeGroup));
    }

    /**
     * 删除故障类型组织关联表
     */
    @ApiOperation(value = "删除故障类型组织关联表")
    @PostMapping("remove")
    public R remove(String ids)
    {
        return toAjax(anMdmcTroubletypeGroupService.deleteAnMdmcTroubletypeGroupByIds(ids));
    }

}
