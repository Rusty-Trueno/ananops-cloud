package com.ananops.mdc.controller;

import com.ananops.common.core.controller.BaseController;
import com.ananops.common.core.domain.R;
import com.ananops.mdc.domain.AnMdcExceptionLog;
import com.ananops.mdc.service.IAnMdcExceptionLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 全局异常记录 提供者
 * 
 * @author ananops
 * @date 2020-06-04
 */
@RestController
@RequestMapping("exception")
@Api("全局异常记录")
public class AnMdcExceptionLogController extends BaseController
{
	
	@Autowired
	private IAnMdcExceptionLogService anMdcExceptionLogService;
	
	/**
	 * 查询全局异常记录
	 */
	@ApiOperation(value = "查询全局异常记录")
	@GetMapping("get/{id}")
	public AnMdcExceptionLog get(@PathVariable("id") Long id)
	{
		return anMdcExceptionLogService.selectAnMdcExceptionLogById(id);
		
	}
	
	/**
	 * 查询全局异常记录列表
	 */
	@ApiOperation(value = "查询全局异常记录列表")
	@GetMapping("list")
	public R list(AnMdcExceptionLog anMdcExceptionLog)
	{
		startPage();
        return result(anMdcExceptionLogService.selectAnMdcExceptionLogList(anMdcExceptionLog));
	}
	
	
	/**
	 * 新增保存全局异常记录
	 */
	@ApiOperation(value = "新增保存全局异常记录")
	@PostMapping("save")
	public R addSave(@RequestBody AnMdcExceptionLog anMdcExceptionLog)
	{		
		return toAjax(anMdcExceptionLogService.insertAnMdcExceptionLog(anMdcExceptionLog));
	}

	/**
	 * 修改保存全局异常记录
	 */
	@ApiOperation(value = "修改保存全局异常记录")
	@PostMapping("update")
	public R editSave(@RequestBody AnMdcExceptionLog anMdcExceptionLog)
	{		
		return toAjax(anMdcExceptionLogService.updateAnMdcExceptionLog(anMdcExceptionLog));
	}
	
	/**
	 * 删除全局异常记录
	 */
	@ApiOperation(value = "删除全局异常记录")
	@PostMapping("remove")
	public R remove(String ids)
	{		
		return toAjax(anMdcExceptionLogService.deleteAnMdcExceptionLogByIds(ids));
	}
	
}
