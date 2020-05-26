package com.ananops.system.controller;

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
import com.ananops.system.domain.AnBmcBill;
import com.ananops.system.service.IAnBmcBillService;

/**
 * 账单 提供者
 * 
 * @author ananops
 * @date 2020-05-26
 */
@RestController
@RequestMapping("bill")
@Api("账单")
public class AnBmcBillController extends BaseController
{
	
	@Autowired
	private IAnBmcBillService anBmcBillService;
	
	/**
	 * 查询账单表
	 */
	@ApiOperation(value = "查询账单表")
	@GetMapping("get/{id}")
	public AnBmcBill get(@PathVariable("id") Long id)
	{
		return anBmcBillService.selectAnBmcBillById(id);
		
	}
	
	/**
	 * 查询账单列表
	 */
	@ApiOperation(value = "查询账单列表")
	@GetMapping("list")
	public R list(AnBmcBill anBmcBill)
	{
		startPage();
        return result(anBmcBillService.selectAnBmcBillList(anBmcBill));
	}
	
	
	/**
	 * 新增保存账单
	 */
	@ApiOperation(value = "新增保存账单")
	@PostMapping("save")
	public R addSave(@RequestBody AnBmcBill anBmcBill)
	{		
		return toAjax(anBmcBillService.insertAnBmcBill(anBmcBill));
	}

	/**
	 * 修改保存账单
	 */
	@ApiOperation(value = "修改保存账单")
	@PostMapping("update")
	public R editSave(@RequestBody AnBmcBill anBmcBill)
	{		
		return toAjax(anBmcBillService.updateAnBmcBill(anBmcBill));
	}
	
	/**
	 * 删除账单表
	 */
	@ApiOperation(value = "删除账单表")
	@PostMapping("remove")
	public R remove(String ids)
	{		
		return toAjax(anBmcBillService.deleteAnBmcBillByIds(ids));
	}
	
}
