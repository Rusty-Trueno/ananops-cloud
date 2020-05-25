package com.ananops.imc.controller;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.imc.dto.ImcAddInspectionItemDto;
import com.ananops.imc.dto.ImcItemChangeStatusDto;
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
import com.ananops.imc.domain.AnImcInspectionItem;
import com.ananops.imc.service.IAnImcInspectionItemService;

/**
 * 巡检任务子项 提供者
 * 
 * @author ananops
 * @date 2020-05-22
 */
@RestController
@RequestMapping("inspectionItem")
@Api("巡检任务子项")
public class AnImcInspectionItemController extends BaseController
{
	
	@Autowired
	private IAnImcInspectionItemService anImcInspectionItemService;
	
	/**
	 * 查询巡检任务子项
	 */
	@ApiOperation(value = "查询巡检任务子项")
	@GetMapping("get/{id}")
	public AnImcInspectionItem get(@PathVariable("id") Long id)
	{
		return anImcInspectionItemService.selectAnImcInspectionItemById(id);
		
	}
	
	/**
	 * 查询巡检任务子项列表
	 */
	@ApiOperation(value = "查询巡检任务子项列表")
	@GetMapping("list")
	public R list(AnImcInspectionItem anImcInspectionItem)
	{
		startPage();
        return result(anImcInspectionItemService.selectAnImcInspectionItemList(anImcInspectionItem));
	}
	
	
	/**
	 * 新增保存巡检任务子项
	 */
	@ApiOperation(value = "新增保存巡检任务子项")
	@PostMapping("save")
	public R addSave(@RequestBody ImcAddInspectionItemDto imcAddInspectionItemDto)
	{		
		return R.data(anImcInspectionItemService.insertAnImcInspectionItem(imcAddInspectionItemDto,getLoginAuthDto()));
	}

	/**
	 * 修改保存巡检任务子项
	 */
	@ApiOperation(value = "修改保存巡检任务子项")
	@PostMapping("update")
	public R editSave(@RequestBody AnImcInspectionItem anImcInspectionItem)
	{		
		return toAjax(anImcInspectionItemService.updateAnImcInspectionItem(anImcInspectionItem));
	}
	
	/**
	 * 删除巡检任务子项
	 */
	@ApiOperation(value = "删除巡检任务子项")
	@PostMapping("remove")
	public R remove(String ids)
	{		
		return toAjax(anImcInspectionItemService.deleteAnImcInspectionItemByIds(ids));
	}

	@ApiOperation(value = "修改巡检任务子项的状态")
	@PostMapping("modifyItemStatusByItemId")
	public R modifyItemStatusByItemId(@RequestBody ImcItemChangeStatusDto imcItemChangeStatusDto){
		LoginAuthDto user = getLoginAuthDto();
		imcItemChangeStatusDto.setLoginAuthDto(user);
		return R.data(anImcInspectionItemService.modifyImcItemStatus(imcItemChangeStatusDto,user));
	}
}
