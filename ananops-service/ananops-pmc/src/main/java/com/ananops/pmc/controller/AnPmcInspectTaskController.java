package com.ananops.pmc.controller;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.common.utils.bean.BeanUtils;
import com.ananops.pmc.dto.PmcInspectTaskDto;
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
import com.ananops.pmc.domain.AnPmcInspectTask;
import com.ananops.pmc.service.IAnPmcInspectTaskService;

import java.util.List;

/**
 * 巡检任务管理 提供者
 * 
 * @author ananops
 * @date 2020-05-28
 */
@RestController
@RequestMapping("task")
@Api("巡检任务管理")
public class AnPmcInspectTaskController extends BaseController
{
	
	@Autowired
	private IAnPmcInspectTaskService anPmcInspectTaskService;
	
	/**
	 * 查询
	 */
	@ApiOperation(value = "查询巡检任务")
	@GetMapping("get/{id}")
	public AnPmcInspectTask get(@PathVariable("id") Long id)
	{
		return anPmcInspectTaskService.selectAnPmcInspectTaskById(id);
		
	}
	
	/**
	 * 查询巡检任务列表
	 */
	@ApiOperation(value = "查询巡检任务列表")
	@GetMapping("list")
	public R list(AnPmcInspectTask anPmcInspectTask)
	{
		startPage();
        return result(anPmcInspectTaskService.selectAnPmcInspectTaskList(anPmcInspectTask));
	}
	
	
	/**
	 * 新增保存巡检任务
	 */
	@ApiOperation(value = "新增保存巡检任务")
	@PostMapping("save")
	public R addSave(@RequestBody PmcInspectTaskDto pmcInspectTaskDto)
	{
		LoginAuthDto loginAuthDto = getLoginAuthDto();
		AnPmcInspectTask anPmcInspectTask = new AnPmcInspectTask();
		BeanUtils.copyProperties(pmcInspectTaskDto, anPmcInspectTask);
		return toAjax(anPmcInspectTaskService.insertAnPmcInspectTask(anPmcInspectTask,loginAuthDto));
	}

	/**
	 * 修改保存巡检任务
	 */
	@ApiOperation(value = "修改保存巡检任务")
	@PostMapping("update")
	public R editSave(@RequestBody AnPmcInspectTask anPmcInspectTask)
	{		
		return toAjax(anPmcInspectTaskService.updateAnPmcInspectTask(anPmcInspectTask));
	}
	
	/**
	 * 删除
	 */
	@ApiOperation(value = "删除巡检任务")
	@PostMapping("remove")
	public R remove(String ids)
	{		
		return toAjax(anPmcInspectTaskService.deleteAnPmcInspectTaskByIds(ids));
	}




	@GetMapping("getTasksByProjectId/{projectId}")
	@ApiOperation(value = "获取某个项目的巡检任务")
	public R getTasksByProjectId(@PathVariable Long projectId){
		List<AnPmcInspectTask> pmcInspectTasks = anPmcInspectTaskService.getTasksByProjectId(projectId);
		return R.data(pmcInspectTasks);
	}


	@PostMapping("deleteTaskByProjectId/{projectId}")
	@ApiOperation(value = "通过项目id删除巡检任务")
	public R deleteTaskByProjectId(@PathVariable Long projectId){
		return toAjax(anPmcInspectTaskService.deleteTaskByProjectId(projectId));
	}

}
