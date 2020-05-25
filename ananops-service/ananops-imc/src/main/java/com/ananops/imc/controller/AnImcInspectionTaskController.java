package com.ananops.imc.controller;

import com.ananops.imc.dto.ImcAddInspectionTaskDto;
import com.ananops.imc.dto.ImcTaskChangeStatusDto;
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
import com.ananops.imc.domain.AnImcInspectionTask;
import com.ananops.imc.service.IAnImcInspectionTaskService;

/**
 * 巡检任务表 提供者
 * 
 * @author ananops
 * @date 2020-05-22
 */
@RestController
@RequestMapping("inspectionTask")
@Api("巡检任务表")
public class AnImcInspectionTaskController extends BaseController
{
	
	@Autowired
	private IAnImcInspectionTaskService anImcInspectionTaskService;
	
	/**
	 * 查询巡检任务
	 */
	@ApiOperation(value = "根据任务的ID，查询巡检任务")
	@GetMapping("getTaskByTaskId/{id}")
	public AnImcInspectionTask get(@PathVariable("id") Long id)
	{
		return anImcInspectionTaskService.selectAnImcInspectionTaskById(id);
		
	}
	
	/**
	 * 查询巡检任务表列表
	 */
	@ApiOperation(value = "查询巡检任务表列表")
	@GetMapping("list")
	public R list(AnImcInspectionTask anImcInspectionTask)
	{
		startPage();
        return result(anImcInspectionTaskService.selectAnImcInspectionTaskList(anImcInspectionTask));
	}
	
	
	/**
	 * 新增保存巡检任务表
	 */
	@ApiOperation(value = "新增保存巡检任务表")
	@PostMapping("save")
	public R addSave(@RequestBody ImcAddInspectionTaskDto imcAddInspectionTaskDto)
	{		
		return R.data(anImcInspectionTaskService.insertAnImcInspectionTask(imcAddInspectionTaskDto,getLoginAuthDto()));
	}

	/**
	 * 修改保存巡检任务表
	 */
	@ApiOperation(value = "修改保存巡检任务表")
	@PostMapping("update")
	public R editSave(@RequestBody AnImcInspectionTask anImcInspectionTask)
	{		
		return toAjax(anImcInspectionTaskService.updateAnImcInspectionTask(anImcInspectionTask));
	}
	
	/**
	 * 删除巡检任务
	 */
	@ApiOperation(value = "删除巡检任务")
	@PostMapping("remove")
	public R remove(String ids)
	{		
		return toAjax(anImcInspectionTaskService.deleteAnImcInspectionTaskByIds(ids));
	}


	/**
	 * 更改巡检任务的状态
	 */
	@ApiOperation(value = "更改巡检任务的状态")
	@PostMapping("modifyTaskStatusByTaskId")
	public ImcTaskChangeStatusDto modifyTaskStatusByTaskId(@RequestBody ImcTaskChangeStatusDto imcTaskChangeStatusDto){
		anImcInspectionTaskService.modifyTaskStatus(imcTaskChangeStatusDto,getLoginAuthDto());
		return imcTaskChangeStatusDto;
	}

}
