package com.ananops.mdc.controller;

import com.ananops.common.core.controller.BaseController;
import com.ananops.common.core.domain.R;
import com.ananops.mdc.domain.AnMdcFormTemplateItem;
import com.ananops.mdc.service.IAnMdcFormTemplateItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * 表单模板子项 提供者
 * 
 * @author ananops
 * @date 2020-06-04
 */
@RestController
@RequestMapping("formTemplateItem")
@Api("表单模板子项")
public class AnMdcFormTemplateItemController extends BaseController
{
	
	@Autowired
	private IAnMdcFormTemplateItemService anMdcFormTemplateItemService;
	
	/**
	 * 查询表单模板子项
	 */
	@ApiOperation(value = "查询表单模板子项")
	@GetMapping("get/{id}")
	public AnMdcFormTemplateItem get(@PathVariable("id") Long id)
	{
		return anMdcFormTemplateItemService.selectAnMdcFormTemplateItemById(id);
		
	}
	
	/**
	 * 查询表单模板子项列表
	 */
	@ApiOperation(value = "查询表单模板子项列表")
	@GetMapping("list")
	public R list(AnMdcFormTemplateItem anMdcFormTemplateItem)
	{
		startPage();
        return result(anMdcFormTemplateItemService.selectAnMdcFormTemplateItemList(anMdcFormTemplateItem));
	}
	
	
	/**
	 * 新增保存表单模板子项
	 */
	@ApiOperation(value = "新增保存表单模板子项")
	@PostMapping("save")
	public R addSave(@RequestBody AnMdcFormTemplateItem anMdcFormTemplateItem)
	{		
		return toAjax(anMdcFormTemplateItemService.insertAnMdcFormTemplateItem(anMdcFormTemplateItem));
	}

	/**
	 * 修改保存表单模板子项
	 */
	@ApiOperation(value = "修改保存表单模板子项")
	@PostMapping("update")
	public R editSave(@RequestBody AnMdcFormTemplateItem anMdcFormTemplateItem)
	{		
		return toAjax(anMdcFormTemplateItemService.updateAnMdcFormTemplateItem(anMdcFormTemplateItem));
	}
	
	/**
	 * 删除表单模板子项
	 */
	@ApiOperation(value = "删除表单模板子项")
	@PostMapping("remove")
	public R remove(String ids)
	{		
		return toAjax(anMdcFormTemplateItemService.deleteAnMdcFormTemplateItemByIds(ids));
	}
	
}
