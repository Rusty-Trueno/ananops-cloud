package com.ananops.amc.controller;

import com.ananops.amc.domain.AnAmcAlarm;
import com.ananops.amc.service.IAnAmcAlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import com.ananops.common.core.domain.R;
import com.ananops.common.core.controller.BaseController;


/**
 * 告警 提供者
 * 
 * @author ananops
 * @date 2020-05-19
 */
@RestController
@RequestMapping("alarm")
@Api("告警")
public class AnAmcAlarmController extends BaseController
{
	
	@Autowired
	private IAnAmcAlarmService anAmcAlarmService;
	
	/**
	 * 查询告警表
	 */
	@ApiOperation(value = "查询告警信息")
	@ApiImplicitParams({@ApiImplicitParam(name = "alarmId", value = "ID，主键", required = true)})
	@GetMapping("get/{alarmId}")
	public AnAmcAlarm get(@PathVariable("alarmId") Long alarmId)
	{
		return anAmcAlarmService.selectAnAmcAlarmById(alarmId);
		
	}
	
	/**
	 * 查询告警列表
	 */
	@ApiOperation(value = "查询告警列表")
	@GetMapping("list")
	public R list(AnAmcAlarm anAmcAlarm)
	{
		startPage();
        return result(anAmcAlarmService.selectAnAmcAlarmList(anAmcAlarm));
	}
	
	
	/**
	 * 新增保存告警
	 */
	@ApiOperation(value = "新增保存告警")
	@ApiImplicitParams({@ApiImplicitParam(name = "anAmcAlarm", value = "告警表", required = true)})
	@PostMapping("save")
	public R addSave(@RequestBody AnAmcAlarm anAmcAlarm)
	{		
		return toAjax(anAmcAlarmService.insertAnAmcAlarm(anAmcAlarm));
	}

	/**
	 * 修改保存告警
	 */
	@ApiOperation(value = "修改保存告警", notes = "修改保存告警")
	@ApiImplicitParams({@ApiImplicitParam(name = "anAmcAlarm", value = "告警表", required = true)})
	@PostMapping("update")
	public R editSave(@RequestBody AnAmcAlarm anAmcAlarm)
	{		
		return toAjax(anAmcAlarmService.updateAnAmcAlarm(anAmcAlarm));
	}
	
	/**
	 * 删除告警表
	 */
	@ApiOperation(value = "删除告警表", notes = "删除告警表")
	@ApiImplicitParams({@ApiImplicitParam(name = "ids", value = "alarmIds", required = true)})
	@PostMapping("remove")
	public R remove(String ids)
	{		
		return toAjax(anAmcAlarmService.deleteAnAmcAlarmByIds(ids));
	}
	
}
