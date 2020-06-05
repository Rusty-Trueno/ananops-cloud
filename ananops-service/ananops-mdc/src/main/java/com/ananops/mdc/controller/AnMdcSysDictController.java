package com.ananops.mdc.controller;

import com.ananops.common.core.controller.BaseController;
import com.ananops.common.core.domain.R;
import com.ananops.mdc.domain.AnMdcSysDict;
import com.ananops.mdc.service.IAnMdcSysDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * 字典 提供者
 * 
 * @author ananops
 * @date 2020-06-04
 */
@RestController
@RequestMapping("dict")
@Api("字典")
public class AnMdcSysDictController extends BaseController
{
	
	@Autowired
	private IAnMdcSysDictService anMdcSysDictService;
	
	/**
	 * 查询字典
	 */
	@ApiOperation(value = "查询字典")
	@GetMapping("get/{id}")
	public AnMdcSysDict get(@PathVariable("id") Long id)
	{
		return anMdcSysDictService.selectAnMdcSysDictById(id);
		
	}
	
	/**
	 * 查询字典列表
	 */
	@ApiOperation(value = "查询字典列表")
	@GetMapping("list")
	public R list(AnMdcSysDict anMdcSysDict)
	{
		startPage();
        return result(anMdcSysDictService.selectAnMdcSysDictList(anMdcSysDict));
	}
	
	
	/**
	 * 新增保存字典
	 */
	@ApiOperation(value = "新增保存字典")
	@PostMapping("save")
	public R addSave(@RequestBody AnMdcSysDict anMdcSysDict)
	{		
		return toAjax(anMdcSysDictService.insertAnMdcSysDict(anMdcSysDict));
	}

	/**
	 * 修改保存字典
	 */
	@ApiOperation(value = "修改保存字典")
	@PostMapping("update")
	public R editSave(@RequestBody AnMdcSysDict anMdcSysDict)
	{		
		return toAjax(anMdcSysDictService.updateAnMdcSysDict(anMdcSysDict));
	}
	
	/**
	 * 删除字典
	 */
	@ApiOperation(value = "删除字典")
	@PostMapping("remove")
	public R remove(String ids)
	{		
		return toAjax(anMdcSysDictService.deleteAnMdcSysDictByIds(ids));
	}
	
}
