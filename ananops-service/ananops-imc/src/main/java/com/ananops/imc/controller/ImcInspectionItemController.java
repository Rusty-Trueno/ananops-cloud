package com.ananops.imc.controller;

import com.ananops.common.core.dto.LoginAuthDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ananops.common.core.domain.R;
import com.ananops.common.core.controller.BaseController;
import com.ananops.imc.domain.ImcInspectionItem;
import com.ananops.imc.service.IImcInspectionItemService;

/**
 * 巡检任务子项 提供者
 * 
 * @author ananops
 * @date 2020-05-19
 */
@RestController
@RequestMapping("item")
public class ImcInspectionItemController extends BaseController
{
	
	@Autowired
	private IImcInspectionItemService imcInspectionItemService;
	
	/**
	 * 查询${tableComment}
	 */
	@GetMapping("get/{id}")
	@ApiOperation(value = "根据用户id查询巡检任务子项")
	public ImcInspectionItem get(@PathVariable("id") Long id)
	{
		return imcInspectionItemService.selectImcInspectionItemById(id);
		
	}
	
	/**
	 * 查询巡检任务子项列表
	 */
	@GetMapping("list")
	@ApiOperation(value = "查询巡检任务子项列表")
	public R list(ImcInspectionItem imcInspectionItem)
	{
		startPage();
        return result(imcInspectionItemService.selectImcInspectionItemList(imcInspectionItem));
	}
	
	
	/**
	 * 新增保存巡检任务子项
	 */
	@PostMapping("save")
	@ApiOperation(value = "新增保存巡检任务子项")
	public R addSave(@RequestBody ImcInspectionItem imcInspectionItem)
	{
		LoginAuthDto user = getLoginAuthDto();
		logger.info("当前用户信息为：user={}",user);
		return toAjax(imcInspectionItemService.insertImcInspectionItem(imcInspectionItem,user));
	}

	/**
	 * 修改保存巡检任务子项
	 */
	@PostMapping("update")
	@ApiOperation(value = "修改保存巡检任务子项")
	public R editSave(@RequestBody ImcInspectionItem imcInspectionItem)
	{		
		return toAjax(imcInspectionItemService.updateImcInspectionItem(imcInspectionItem));
	}
	
	/**
	 * 删除${tableComment}
	 */
	@PostMapping("remove")
	@ApiOperation(value = "删除巡检任务子项")
	public R remove(String ids)
	{		
		return toAjax(imcInspectionItemService.deleteImcInspectionItemByIds(ids));
	}
	
}
