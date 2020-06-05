package com.ananops.mdc.controller;

import com.ananops.common.core.controller.BaseController;
import com.ananops.common.core.domain.R;
import com.ananops.mdc.domain.AnMdcFormSchema;
import com.ananops.mdc.service.IAnMdcFormSchemaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * 巡检表单的表结构 提供者
 * 
 * @author ananops
 * @date 2020-06-04
 */
@RestController
@RequestMapping("formSchema")
@Api("巡检表单的表结构")
public class AnMdcFormSchemaController extends BaseController
{
	
	@Autowired
	private IAnMdcFormSchemaService anMdcFormSchemaService;
	
	/**
	 * 查询巡检表单的表结构
	 */
	@ApiOperation(value = "查询巡检表单的表结构")
	@GetMapping("get/{id}")
	public AnMdcFormSchema get(@PathVariable("id") Long id)
	{
		return anMdcFormSchemaService.selectAnMdcFormSchemaById(id);
		
	}
	
	/**
	 * 查询巡检表单的表结构列表
	 */
	@ApiOperation(value = "查询巡检表单的表结构列表")
	@GetMapping("list")
	public R list(AnMdcFormSchema anMdcFormSchema)
	{
		startPage();
        return result(anMdcFormSchemaService.selectAnMdcFormSchemaList(anMdcFormSchema));
	}
	
	
	/**
	 * 新增保存巡检表单的表结构
	 */
	@ApiOperation(value = "新增保存巡检表单的表结构")
	@PostMapping("save")
	public R addSave(@RequestBody AnMdcFormSchema anMdcFormSchema)
	{		
		return toAjax(anMdcFormSchemaService.insertAnMdcFormSchema(anMdcFormSchema));
	}

	/**
	 * 修改保存巡检表单的表结构
	 */
	@ApiOperation(value = "修改保存巡检表单的表结构")
	@PostMapping("update")
	public R editSave(@RequestBody AnMdcFormSchema anMdcFormSchema)
	{		
		return toAjax(anMdcFormSchemaService.updateAnMdcFormSchema(anMdcFormSchema));
	}
	
	/**
	 * 删除巡检表单的表结构
	 */
	@ApiOperation(value = "删除巡检表单的表结构")
	@PostMapping("remove")
	public R remove(String ids)
	{		
		return toAjax(anMdcFormSchemaService.deleteAnMdcFormSchemaByIds(ids));
	}
	
}
