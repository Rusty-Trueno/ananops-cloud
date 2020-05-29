package com.ananops.mdmc.controller;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.mdmc.aop.AnMdmcLog;
import com.ananops.mdmc.dto.MdmcAddTaskDto;
import com.ananops.mdmc.dto.MdmcChangeStatusDto;
import com.ananops.mdmc.dto.MdmcDispatchDto;
import com.ananops.mdmc.dto.MdmcQueryDto;
import com.ananops.mdmc.enums.MdmcTaskStatusEnum;
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
import com.ananops.mdmc.domain.AnMdmcTask;
import com.ananops.mdmc.service.IAnMdmcTaskService;

import javax.annotation.Resource;

/**
 * 维修工单 提供者
 *
 * @author ananops
 * @date 2020-05-25
 */
@RestController
@RequestMapping("mdmcTask")
@Api("维修工单")
public class AnMdmcTaskController extends BaseController
{

    @Resource
    private IAnMdmcTaskService anMdmcTaskService;

    /**
     * 查询维修工单表
     */
    @ApiOperation(value = "查询维修工单详情，可测")
    @GetMapping("getTaskDetailByTaskId/{id}")
    public R get(@PathVariable("id") Long id)
    {
        return R.data(anMdmcTaskService.selectAnMdmcTaskById(id));

    }

    /**
     * 查询维修工单列表
     */
    @ApiOperation(value = "查询维修工单列表，可测")
    @PostMapping("getTaskList")
    public R list(@RequestBody MdmcQueryDto queryDto)
    {
        return R.data(anMdmcTaskService.selectAnMdmcTaskList(queryDto));
    }


    /**
     * 新增保存维修工单
     */
    @ApiOperation(value = "新增保存维修工单，可测")
    @PostMapping("save")
    @AnMdmcLog
    public R addSave(@RequestBody MdmcAddTaskDto mdmcAddTaskDto)
    {
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        return R.data(anMdmcTaskService.insertAnMdmcTask(mdmcAddTaskDto,loginAuthDto));
    }

    /**
     * 上传维修结果
     */
    @ApiOperation(value = "上传维修结果，可测")
    @PostMapping("update")
    @AnMdmcLog
    public R editSave(@RequestBody MdmcAddTaskDto updateTaskDto)
    {
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        return R.data(anMdmcTaskService.updateAnMdmcTask(updateTaskDto,loginAuthDto));
    }

    /**
     * 删除维修工单表
     */
    @ApiOperation(value = "根据id删除维修工单及其所属任务子项，可测")
    @PostMapping("remove/{id}")
    public R remove(@PathVariable("id") Long id)
    {
        return toAjax(anMdmcTaskService.deleteAnMdmcTaskById(id));
    }

    /**
     * 修改工单状态
     */
    @ApiOperation(value = "更改工单状态，可测")
    @PostMapping("modifyTaskStatusByTaskId")
    @AnMdmcLog
    public R modifyStatus(@RequestBody MdmcChangeStatusDto changeStatusDto)
    {
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        return R.data(anMdmcTaskService.modifyTaskStatus(changeStatusDto,loginAuthDto));
    }

    /**
     * 服务商拒单
     */
    @ApiOperation(value = "服务商拒单，可测")
    @PostMapping("refuseTaskByFacilitator/{taskId}")
    @AnMdmcLog
    public R refuseTaskByFacilitator(@PathVariable("taskId")Long taskId){
        LoginAuthDto loginAuthDto=getLoginAuthDto();
        MdmcChangeStatusDto changeStatusDto=new MdmcChangeStatusDto();
        changeStatusDto.setStatus(MdmcTaskStatusEnum.Reject1.getStatusNum());
        changeStatusDto.setTaskId(taskId);
        return R.data(anMdmcTaskService.modifyTaskStatus(changeStatusDto,loginAuthDto));
    }

    /**
     * 工程师拒单
     */
    @ApiOperation(value = "工程师拒单，可测")
    @PostMapping("refuseTaskByMaintainer/{taskId}")
    @AnMdmcLog
    public R refuseTaskByMaintainer(@PathVariable("taskId")Long taskId){
        LoginAuthDto loginAuthDto=getLoginAuthDto();
        MdmcChangeStatusDto changeStatusDto=new MdmcChangeStatusDto();
        changeStatusDto.setStatus(MdmcTaskStatusEnum.JieDan2.getStatusNum());
        changeStatusDto.setTaskId(taskId);
        return R.data(anMdmcTaskService.modifyTaskStatus(changeStatusDto,loginAuthDto));
    }

    /**
     * 平台管理员重新分配服务商
     */
    @ApiOperation(value = "平台管理员重新分配服务商，可测")
    @PostMapping("dispatchFacilitator")
    @AnMdmcLog
    public R dispatchFac(@RequestBody MdmcDispatchDto dispatchDto){
        LoginAuthDto loginAuthDto=getLoginAuthDto();
        MdmcChangeStatusDto changeStatusDto=new MdmcChangeStatusDto();
        changeStatusDto.setStatus(MdmcTaskStatusEnum.JieDan1.getStatusNum());
        changeStatusDto.setTaskId(dispatchDto.getId());
        anMdmcTaskService.modifyTaskStatus(changeStatusDto,loginAuthDto);
        return R.data(anMdmcTaskService.dispatch(dispatchDto));
    }

    /**
     * 服务商分配工程师
     */
    @ApiOperation(value = "服务商分配工程师，可测")
    @PostMapping("dispatchMaintainer")
    @AnMdmcLog
    public R dispatchEng(@RequestBody MdmcDispatchDto dispatchDto){
        LoginAuthDto loginAuthDto=getLoginAuthDto();
        MdmcChangeStatusDto changeStatusDto=new MdmcChangeStatusDto();
        changeStatusDto.setStatus(MdmcTaskStatusEnum.JieDan3.getStatusNum());
        changeStatusDto.setTaskId(dispatchDto.getId());
        anMdmcTaskService.modifyTaskStatus(changeStatusDto,loginAuthDto);
        return R.data(anMdmcTaskService.dispatch(dispatchDto));
    }

}
