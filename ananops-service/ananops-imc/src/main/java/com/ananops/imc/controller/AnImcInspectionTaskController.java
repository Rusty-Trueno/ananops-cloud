package com.ananops.imc.controller;

import com.ananops.common.core.dto.LoginAuthDto;
import io.swagger.annotations.Api;
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
import com.ananops.imc.domain.AnImcInspectionTask;
import com.ananops.imc.service.IAnImcInspectionTaskService;

/**
 * 巡检任务 提供者
 *
 * @author rongshuai
 * @date 2020-05-18
 */
@RestController
@RequestMapping("task")
@Api("巡检任务")
public class AnImcInspectionTaskController extends BaseController
{

    @Autowired
    private IAnImcInspectionTaskService anImcInspectionTaskService;


    /**
     * 查询${tableComment}
     */
    @ApiOperation(value = "根据任务id获取巡检任务", notes = "根据任务id获取巡检任务")
    @GetMapping("get/{id}")
    public AnImcInspectionTask get(@PathVariable("id") Long id)
    {
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        logger.info("currentUser is {}",loginAuthDto);
        return anImcInspectionTaskService.selectAnImcInspectionTaskById(id);

    }

    /**
     * 查询巡检任务列表
     */
    @ApiOperation(value = "查询巡检任务列表", notes = "查询巡检任务列表")
    @GetMapping("list")
    public R list(AnImcInspectionTask anImcInspectionTask)
    {
        startPage();
        return result(anImcInspectionTaskService.selectAnImcInspectionTaskList(anImcInspectionTask));
    }


    /**
     * 新增保存巡检任务
     */
    @ApiOperation(value = "新增保存巡检任务", notes = "新增保存巡检任务")
    @PostMapping("save")
    public R addSave(@RequestBody AnImcInspectionTask anImcInspectionTask)
    {
        return toAjax(anImcInspectionTaskService.insertAnImcInspectionTask(anImcInspectionTask));
    }

    /**
     * 修改保存巡检任务
     */
    @ApiOperation(value = "修改保存巡检任务", notes = "修改保存巡检任务")
    @PostMapping("update")
    public R editSave(@RequestBody AnImcInspectionTask anImcInspectionTask)
    {
        return toAjax(anImcInspectionTaskService.updateAnImcInspectionTask(anImcInspectionTask));
    }

    /**
     * 删除${tableComment}
     */
    @ApiOperation(value = "删除指定id的巡检任务", notes = "删除指定id的巡检任务")
    @PostMapping("remove")
    public R remove(String ids)
    {
        return toAjax(anImcInspectionTaskService.deleteAnImcInspectionTaskByIds(ids));
    }

}