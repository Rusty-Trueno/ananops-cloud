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
import com.ananops.pmc.domain.AnPmcContract;
import com.ananops.pmc.service.IAnPmcContractService;

/**
 * 【请填写功能名称】 提供者
 * 
 * @author ananops
 * @date 2020-05-28
 */
@RestController
@RequestMapping("contract")
@Api("【请填写功能名称】")
public class AnPmcContractController extends BaseController
{
	
	@Autowired
	private IAnPmcContractService anPmcContractService;
	
	/**
	 * 查询
	 */
	@ApiOperation(value = "查询")
	@GetMapping("get/{id}")
	public AnPmcContract get(@PathVariable("id") Long id)
	{
		return anPmcContractService.selectAnPmcContractById(id);
		
	}
	
	/**
	 * 查询【请填写功能名称】列表
	 */
	@ApiOperation(value = "查询【请填写功能名称】列表")
	@GetMapping("list")
	public R list(AnPmcContract anPmcContract)
	{
		startPage();
        return result(anPmcContractService.selectAnPmcContractList(anPmcContract));
	}
	
	
	/**
	 * 新增保存【请填写功能名称】
	 */
	@ApiOperation(value = "新增保存【请填写功能名称】")
	@PostMapping("save")
	public R addSave(@RequestBody AnPmcContract anPmcContract)
	{		
		return toAjax(anPmcContractService.insertAnPmcContract(anPmcContract));
	}

	/**
	 * 修改保存【请填写功能名称】
	 */
	@ApiOperation(value = "修改保存【请填写功能名称】")
	@PostMapping("update")
	public R editSave(@RequestBody AnPmcContract anPmcContract)
	{		
		return toAjax(anPmcContractService.updateAnPmcContract(anPmcContract));
	}
	
	/**
	 * 删除
	 */
	@ApiOperation(value = "删除")
	@PostMapping("remove")
	public R remove(String ids)
	{		
		return toAjax(anPmcContractService.deleteAnPmcContractByIds(ids));
	}
	
}
