package com.ananops.amc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ananops.common.core.domain.R;
import com.ananops.common.core.controller.BaseController;
import com.ananops.amc.domain.AnAmcAlarm;
import com.ananops.amc.service.IAnAmcAlarmService;

/**
 * 告警 提供者
 * 
 * @author ananops
 * @date 2020-05-18
 */
@RestController
@RequestMapping("alarm")
public class AnAmcAlarmController extends BaseController
{
	
	@Autowired
	private IAnAmcAlarmService anAmcAlarmService;
	
	/**
	 * 查询${tableComment}
	 */
	@GetMapping("get/{alarmId}")
	public AnAmcAlarm get(@PathVariable("alarmId") Long alarmId)
	{
		return anAmcAlarmService.selectAnAmcAlarmById(alarmId);
		
	}
	
	/**
	 * 查询告警列表
	 */
	@GetMapping("list")
	public R list(AnAmcAlarm anAmcAlarm)
	{
		startPage();
        return result(anAmcAlarmService.selectAnAmcAlarmList(anAmcAlarm));
	}
	
	
	/**
	 * 新增保存告警
	 */
	@PostMapping("save")
	public R addSave(@RequestBody AnAmcAlarm anAmcAlarm)
	{		
		return toAjax(anAmcAlarmService.insertAnAmcAlarm(anAmcAlarm));
	}

	/**
	 * 修改保存告警
	 */
	@PostMapping("update")
	public R editSave(@RequestBody AnAmcAlarm anAmcAlarm)
	{		
		return toAjax(anAmcAlarmService.updateAnAmcAlarm(anAmcAlarm));
	}
	
	/**
	 * 删除${tableComment}
	 */
	@PostMapping("remove")
	public R remove(String ids)
	{		
		return toAjax(anAmcAlarmService.deleteAnAmcAlarmByIds(ids));
	}
	
}
