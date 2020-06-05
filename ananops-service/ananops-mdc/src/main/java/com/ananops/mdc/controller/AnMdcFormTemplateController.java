package com.ananops.mdc.controller;

import com.ananops.common.core.controller.BaseController;
import com.ananops.common.core.domain.R;
import com.ananops.mdc.domain.AnMdcFormTemplate;
import com.ananops.mdc.service.IAnMdcFormTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * 表单模板 提供者
 * 
 * @author ananops
 * @date 2020-06-04
 */
@RestController
@RequestMapping("formTemplate")
@Api("表单模板")
public class AnMdcFormTemplateController extends BaseController
{
	
	@Autowired
	private IAnMdcFormTemplateService anMdcFormTemplateService;
	
	/**
	 * 查询表单模板
	 */
	@ApiOperation(value = "查询表单模板")
	@GetMapping("get/{id}")
	public AnMdcFormTemplate get(@PathVariable("id") Long id)
	{
		return anMdcFormTemplateService.selectAnMdcFormTemplateById(id);
		
	}
	
	/**
	 * 查询表单模板列表
	 */
	@ApiOperation(value = "查询表单模板列表")
	@GetMapping("list")
	public R list(AnMdcFormTemplate anMdcFormTemplate)
	{
		startPage();
        return result(anMdcFormTemplateService.selectAnMdcFormTemplateList(anMdcFormTemplate));
	}
	
	
	/**
	 * 新增保存表单模板
	 */
	@ApiOperation(value = "新增保存表单模板")
	@PostMapping("save")
	public R addSave(@RequestBody AnMdcFormTemplate anMdcFormTemplate)
	{		
		return toAjax(anMdcFormTemplateService.insertAnMdcFormTemplate(anMdcFormTemplate));
	}

	/**
	 * 修改保存表单模板
	 */
	@ApiOperation(value = "修改保存表单模板")
	@PostMapping("update")
	public R editSave(@RequestBody AnMdcFormTemplate anMdcFormTemplate)
	{		
		return toAjax(anMdcFormTemplateService.updateAnMdcFormTemplate(anMdcFormTemplate));
	}
	
	/**
	 * 删除表单模板
	 */
	@ApiOperation(value = "删除表单模板")
	@PostMapping("remove")
	public R remove(String ids)
	{		
		return toAjax(anMdcFormTemplateService.deleteAnMdcFormTemplateByIds(ids));
	}
	
}
