package com.ananops.pmc.controller;

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
import com.ananops.pmc.domain.AnPmcProject;
import com.ananops.pmc.service.IAnPmcProjectService;

/**
 * 【请填写功能名称】 提供者
 * 
 * @author ananops
 * @date 2020-05-28
 */
@RestController
@RequestMapping("project")
@Api("【请填写功能名称】")
public class AnPmcProjectController extends BaseController
{
	
	@Autowired
	private IAnPmcProjectService anPmcProjectService;
	
	/**
	 * 查询
	 */
	@ApiOperation(value = "查询")
	@GetMapping("get/{id}")
	public AnPmcProject get(@PathVariable("id") Long id)
	{
		return anPmcProjectService.selectAnPmcProjectById(id);
		
	}
	
	/**
	 * 查询【请填写功能名称】列表
	 */
	@ApiOperation(value = "查询【请填写功能名称】列表")
	@GetMapping("list")
	public R list(AnPmcProject anPmcProject)
	{
		startPage();
        return result(anPmcProjectService.selectAnPmcProjectList(anPmcProject));
	}
	
	
	/**
	 * 新增保存【请填写功能名称】
	 */
	@ApiOperation(value = "新增保存【请填写功能名称】")
	@PostMapping("save")
	public R addSave(@RequestBody AnPmcProject anPmcProject)
	{		
		return toAjax(anPmcProjectService.insertAnPmcProject(anPmcProject));
	}

	/**
	 * 修改保存【请填写功能名称】
	 */
	@ApiOperation(value = "修改保存【请填写功能名称】")
	@PostMapping("update")
	public R editSave(@RequestBody AnPmcProject anPmcProject)
	{		
		return toAjax(anPmcProjectService.updateAnPmcProject(anPmcProject));
	}
	
	/**
	 * 删除
	 */
	@ApiOperation(value = "删除")
	@PostMapping("remove")
	public R remove(String ids)
	{		
		return toAjax(anPmcProjectService.deleteAnPmcProjectByIds(ids));
	}
	
}
