package com.ananops.mdc.controller;

import com.ananops.common.core.controller.BaseController;
import com.ananops.common.core.domain.R;
import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.mdc.domain.AnMdcSysDict;
import com.ananops.mdc.dto.MdcAddDictDto;
import com.ananops.mdc.service.IAnMdcSysDictEnService;
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
	private IAnMdcSysDictEnService anMdcSysDictEnService;
	
	/**
	 * 查询字典
	 */
	@ApiOperation(value = "查询字典")
	@GetMapping("get/{id}")
	public R get(@PathVariable("id") Long id)
	{
		logger.info("query dict by {}",id);
		LoginAuthDto loginAuthDto = getLoginAuthDto();
		return R.data(anMdcSysDictEnService.selectAnMdcSysDictById(id,loginAuthDto));
		
	}
	
	/**
	 * 查询字典列表
	 */
	@ApiOperation(value = "查询字典列表")
	@PostMapping("list")
	public R list(@RequestBody MdcAddDictDto mdcAddDictDto)
	{
		logger.info("query dictlist by {}",mdcAddDictDto);
		LoginAuthDto loginAuthDto = getLoginAuthDto();
		startPage();
        return result(anMdcSysDictEnService.selectAnMdcSysDictList(mdcAddDictDto,loginAuthDto));
	}
	
	
	/**
	 * 新增保存字典
	 */
	@ApiOperation(value = "新增保存字典")
	@PostMapping("save")
	public R addSave(@RequestBody MdcAddDictDto mdcAddDictDto)
	{
		LoginAuthDto loginAuthDto = getLoginAuthDto();
		return R.data(anMdcSysDictEnService.insertAnMdcSysDict(mdcAddDictDto,loginAuthDto));
	}

	/**
	 * 修改保存字典
	 */
	@ApiOperation(value = "修改保存字典")
	@PostMapping("update")
	public R editSave(@RequestBody MdcAddDictDto mdcAddDictDto)
	{
		LoginAuthDto loginAuthDto = getLoginAuthDto();
		return R.data(anMdcSysDictEnService.insertAnMdcSysDict(mdcAddDictDto,loginAuthDto));
	}
	
	/**
	 * 删除字典
	 */
	@ApiOperation(value = "删除字典")
	@PostMapping("remove")
	public R remove(String ids)
	{
		LoginAuthDto loginAuthDto = getLoginAuthDto();
		return toAjax(anMdcSysDictEnService.deleteAnMdcSysDictByIds(ids,loginAuthDto));
	}
	
}
