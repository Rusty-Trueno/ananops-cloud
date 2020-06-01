package com.ananops.pmc.controller;

import com.ananops.common.utils.bean.BeanUtils;
import com.ananops.pmc.dto.PmcInspectDetailDto;
import io.swagger.annotations.ApiParam;
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
import com.ananops.pmc.domain.AnPmcInspectDetail;
import com.ananops.pmc.service.IAnPmcInspectDetailService;

import java.util.List;

/**
 * 巡检详情 提供者
 * 
 * @author ananops
 * @date 2020-05-28
 */
@RestController
@RequestMapping("detail")
@Api("巡检详情管理")
public class AnPmcInspectDetailController extends BaseController
{
	
	@Autowired
	private IAnPmcInspectDetailService anPmcInspectDetailService;
	
	/**
	 * 查询
	 */
	@ApiOperation(value = "查询巡检详情")
	@GetMapping("get/{id}")
	public AnPmcInspectDetail get(@PathVariable("id") Long id)
	{
		return anPmcInspectDetailService.selectAnPmcInspectDetailById(id);
		
	}
	
	/**
	 * 查询【请填写功能名称】列表
	 */
	@ApiOperation(value = "查询巡检详情列表")
	@GetMapping("list")
	public R list(AnPmcInspectDetail anPmcInspectDetail)
	{
		startPage();
        return result(anPmcInspectDetailService.selectAnPmcInspectDetailList(anPmcInspectDetail));
	}
	
	
	/**
	 * 新增保存【请填写功能名称】
	 */
	@ApiOperation(value = "新增保存巡检详情")
	@PostMapping("save")
	public R addSave(@RequestBody PmcInspectDetailDto pmcInspectDetailDto)
	{
		AnPmcInspectDetail anPmcInspectDetail = new AnPmcInspectDetail();
		BeanUtils.copyProperties(pmcInspectDetailDto, anPmcInspectDetail);
		return toAjax(anPmcInspectDetailService.insertAnPmcInspectDetail(anPmcInspectDetail));
	}

	/**
	 * 修改保存【请填写功能名称】
	 */
	@ApiOperation(value = "修改保存巡检详情")
	@PostMapping("update")
	public R editSave(@RequestBody AnPmcInspectDetail anPmcInspectDetail)
	{		
		return toAjax(anPmcInspectDetailService.updateAnPmcInspectDetail(anPmcInspectDetail));
	}
	
	/**
	 * 删除
	 */
	@ApiOperation(value = "删除巡检详情")
	@PostMapping("remove")
	public R remove(String ids)
	{		
		return toAjax(anPmcInspectDetailService.deleteAnPmcInspectDetailByIds(ids));
	}



	@GetMapping("getInspectDetailById/{id}")
	@ApiOperation(value = "根据id获取巡检详情")
	public R getInspectDetailById(@PathVariable Long id) {
		AnPmcInspectDetail anPmcInspectDetail = anPmcInspectDetailService.getInspectDetailById(id);
		return R.data(anPmcInspectDetail);
	}

	@GetMapping("getInspectDetailList/{inspectTaskId}")
	@ApiOperation(value = "获取巡检任务详情")
	public R getInspectDetailList(@PathVariable Long inspectTaskId) {
		List<AnPmcInspectDetail> pmcInspectDetailList = anPmcInspectDetailService.getInspectDetailList(inspectTaskId);
		return R.data(pmcInspectDetailList);
	}

//	@PostMapping("deleteDetailById/{id}")
//	@ApiOperation(httpMethod = "POST", value = "删除巡检详情")
//	public R deleteDetailById(@PathVariable Long id) {
//		return anPmcInspectDetailService.deleteDetailById(id);
//		return WrapMapper.ok();
//	}

	@PostMapping("deleteDetailByTaskId/{taskId}")
	@ApiOperation(value = "删除巡检详情")
	public R deleteDetailByTaskId(@ApiParam(value = "巡检任务id") @PathVariable Long taskId) {
		return toAjax(anPmcInspectDetailService.deleteDetailByTaskId(taskId));
	}
	
}
