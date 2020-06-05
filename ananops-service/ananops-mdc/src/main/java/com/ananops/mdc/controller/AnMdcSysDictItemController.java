package com.ananops.mdc.controller;

import com.ananops.common.core.controller.BaseController;
import com.ananops.common.core.domain.R;
import com.ananops.mdc.domain.AnMdcSysDictItem;
import com.ananops.mdc.service.IAnMdcSysDictItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * 字典子项 提供者
 * 
 * @author ananops
 * @date 2020-06-04
 */
@RestController
@RequestMapping("dictItem")
@Api("字典子项")
public class AnMdcSysDictItemController extends BaseController
{
	
	@Autowired
	private IAnMdcSysDictItemService anMdcSysDictItemService;
	
	/**
	 * 查询字典子项
	 */
	@ApiOperation(value = "查询字典子项")
	@GetMapping("get/{id}")
	public AnMdcSysDictItem get(@PathVariable("id") Long id)
	{
		return anMdcSysDictItemService.selectAnMdcSysDictItemById(id);
		
	}
	
	/**
	 * 查询字典子项列表
	 */
	@ApiOperation(value = "查询字典子项列表")
	@GetMapping("list")
	public R list(AnMdcSysDictItem anMdcSysDictItem)
	{
		startPage();
        return result(anMdcSysDictItemService.selectAnMdcSysDictItemList(anMdcSysDictItem));
	}
	
	
	/**
	 * 新增保存字典子项
	 */
	@ApiOperation(value = "新增保存字典子项")
	@PostMapping("save")
	public R addSave(@RequestBody AnMdcSysDictItem anMdcSysDictItem)
	{		
		return toAjax(anMdcSysDictItemService.insertAnMdcSysDictItem(anMdcSysDictItem));
	}

	/**
	 * 修改保存字典子项
	 */
	@ApiOperation(value = "修改保存字典子项")
	@PostMapping("update")
	public R editSave(@RequestBody AnMdcSysDictItem anMdcSysDictItem)
	{		
		return toAjax(anMdcSysDictItemService.updateAnMdcSysDictItem(anMdcSysDictItem));
	}
	
	/**
	 * 删除字典子项
	 */
	@ApiOperation(value = "删除字典子项")
	@PostMapping("remove")
	public R remove(String ids)
	{		
		return toAjax(anMdcSysDictItemService.deleteAnMdcSysDictItemByIds(ids));
	}
	
}
