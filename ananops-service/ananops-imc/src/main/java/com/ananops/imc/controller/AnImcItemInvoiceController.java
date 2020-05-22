package com.ananops.imc.controller;

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
import com.ananops.imc.domain.AnImcItemInvoice;
import com.ananops.imc.service.IAnImcItemInvoiceService;

/**
 * 巡检记录 提供者
 * 
 * @author ananops
 * @date 2020-05-22
 */
@RestController
@RequestMapping("invoice")
@Api("巡检记录")
public class AnImcItemInvoiceController extends BaseController
{
	
	@Autowired
	private IAnImcItemInvoiceService anImcItemInvoiceService;
	
	/**
	 * 查询巡检记录
	 */
	@ApiOperation(value = "查询巡检记录")
	@GetMapping("get/{id}")
	public AnImcItemInvoice get(@PathVariable("id") Long id)
	{
		return anImcItemInvoiceService.selectAnImcItemInvoiceById(id);
		
	}
	
	/**
	 * 查询巡检记录列表
	 */
	@ApiOperation(value = "查询巡检记录列表")
	@GetMapping("list")
	public R list(AnImcItemInvoice anImcItemInvoice)
	{
		startPage();
        return result(anImcItemInvoiceService.selectAnImcItemInvoiceList(anImcItemInvoice));
	}
	
	
	/**
	 * 新增保存巡检记录
	 */
	@ApiOperation(value = "新增保存巡检记录")
	@PostMapping("save")
	public R addSave(@RequestBody AnImcItemInvoice anImcItemInvoice)
	{		
		return toAjax(anImcItemInvoiceService.insertAnImcItemInvoice(anImcItemInvoice,getLoginAuthDto()));
	}

	/**
	 * 修改保存巡检记录
	 */
	@ApiOperation(value = "修改保存巡检记录")
	@PostMapping("update")
	public R editSave(@RequestBody AnImcItemInvoice anImcItemInvoice)
	{		
		return toAjax(anImcItemInvoiceService.updateAnImcItemInvoice(anImcItemInvoice));
	}
	
	/**
	 * 删除巡检记录
	 */
	@ApiOperation(value = "删除巡检记录")
	@PostMapping("remove")
	public R remove(String ids)
	{		
		return toAjax(anImcItemInvoiceService.deleteAnImcItemInvoiceByIds(ids));
	}
	
}
