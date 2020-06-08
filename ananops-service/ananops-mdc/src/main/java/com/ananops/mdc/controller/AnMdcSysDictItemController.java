package com.ananops.mdc.controller;

import com.ananops.common.core.controller.BaseController;
import com.ananops.common.core.domain.R;
import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.mdc.domain.AnMdcSysDictItem;
import com.ananops.mdc.dto.MdcAddDictItemDto;
import com.ananops.mdc.dto.SysDictItemsDto;
import com.ananops.mdc.service.IAnMdcSysDictItemEnService;
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
	private IAnMdcSysDictItemEnService iAnMdcSysDictItemEnService;
	
	/**
	 * 查询字典子项
	 */
	@ApiOperation(value = "查询字典子项")
	@GetMapping("get/{id}")
	public R get(@PathVariable("id") Long id)
	{
		LoginAuthDto loginAuthDto = getLoginAuthDto();
		return R.data(iAnMdcSysDictItemEnService.selectAnMdcSysDictItemById(id,loginAuthDto));
		
	}
	
	/**
	 * 查询字典子项列表
	 */
	@ApiOperation(value = "查询字典子项列表")
	@PostMapping("list")
	public R list(@RequestBody MdcAddDictItemDto mdcAddDictItemDto)
	{
		LoginAuthDto loginAuthDto = getLoginAuthDto();
		startPage();
        return result(iAnMdcSysDictItemEnService.selectAnMdcSysDictItemList(mdcAddDictItemDto,loginAuthDto));
	}
	
	
	/**
	 * 新增保存字典子项
	 */
	@ApiOperation(value = "新增保存字典子项")
	@PostMapping("save")
	public R addSave(@RequestBody MdcAddDictItemDto mdcAddDictItemDto)
	{
		LoginAuthDto loginAuthDto = getLoginAuthDto();
		return R.data(iAnMdcSysDictItemEnService.insertAnMdcSysDictItem(mdcAddDictItemDto,loginAuthDto));
	}

	/**
	 * 修改保存字典子项
	 */
	@ApiOperation(value = "修改保存字典子项")
	@PostMapping("update")
	public R editSave(@RequestBody MdcAddDictItemDto mdcAddDictItemDto)
	{
		LoginAuthDto loginAuthDto = getLoginAuthDto();
		return R.data(iAnMdcSysDictItemEnService.updateAnMdcSysDictItem(mdcAddDictItemDto,loginAuthDto));
	}
	
	/**
	 * 删除字典子项
	 */
	@ApiOperation(value = "删除字典子项")
	@PostMapping("remove")
	public R remove(String ids)
	{
		LoginAuthDto loginAuthDto = getLoginAuthDto();
		return toAjax(iAnMdcSysDictItemEnService.deleteAnMdcSysDictItemByIds(ids,loginAuthDto));
	}

	/**
	 * 为维修工单页面提供准备数据
	 *
	 * @return 返回
	 */
	@GetMapping(value = "/getSysDictItemList")
	@ApiOperation(httpMethod = "GET",value = "为维修工单页面提供准备数据")
	public R getSysDictItemList(){
		LoginAuthDto loginAuthDto = getLoginAuthDto();
		SysDictItemsDto sysDictItemsDto = iAnMdcSysDictItemEnService.getSysDictItems(loginAuthDto);
		return R.data(sysDictItemsDto);
	}
	
}
