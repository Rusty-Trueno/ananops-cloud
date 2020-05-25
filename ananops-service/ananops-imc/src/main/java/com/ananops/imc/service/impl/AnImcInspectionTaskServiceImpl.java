package com.ananops.imc.service.impl;

import java.util.Date;
import java.util.List;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.common.core.service.BaseService;
import com.ananops.common.exception.BusinessException;
import com.ananops.common.utils.DateUtils;
import com.ananops.common.utils.bean.BeanUtils;
import com.ananops.common.utils.bean.UpdateInfoUtil;
import com.ananops.imc.domain.AnImcInspectionItem;
import com.ananops.imc.dto.ImcAddInspectionItemDto;
import com.ananops.imc.dto.ImcAddInspectionTaskDto;
import com.ananops.imc.dto.ImcTaskChangeStatusDto;
import com.ananops.imc.enums.ItemStatusEnum;
import com.ananops.imc.enums.TaskStatusEnum;
import com.ananops.imc.enums.TaskTypeEnum;
import com.ananops.imc.service.IAnImcInspectionItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ananops.imc.mapper.AnImcInspectionTaskMapper;
import com.ananops.imc.domain.AnImcInspectionTask;
import com.ananops.imc.service.IAnImcInspectionTaskService;
import com.ananops.common.core.text.Convert;

/**
 * 巡检任务表Service业务层处理
 * 
 * @author ananops
 * @date 2020-05-22
 */
@Service
public class AnImcInspectionTaskServiceImpl extends BaseService<AnImcInspectionTask> implements IAnImcInspectionTaskService
{

    @Autowired
    private AnImcInspectionTaskMapper anImcInspectionTaskMapper;

    @Autowired
    private IAnImcInspectionItemService iAnImcInspectionItemService;

    /**
     * 查询巡检任务表
     * 
     * @param id 巡检任务表ID
     * @return 巡检任务表
     */
    @Override
    public AnImcInspectionTask selectAnImcInspectionTaskById(Long id)
    {
        return anImcInspectionTaskMapper.selectAnImcInspectionTaskById(id);
    }

    /**
     * 查询巡检任务表列表
     * 
     * @param anImcInspectionTask 巡检任务表
     * @return 巡检任务表
     */
    @Override
    public List<AnImcInspectionTask> selectAnImcInspectionTaskList(AnImcInspectionTask anImcInspectionTask)
    {
        return anImcInspectionTaskMapper.selectAnImcInspectionTaskList(anImcInspectionTask);
    }

    /**
     * 新增巡检任务表
     * 
     * @param imcAddInspectionTaskDto 巡检任务表
     * @return 结果
     */
    @Override
    public ImcAddInspectionTaskDto insertAnImcInspectionTask(ImcAddInspectionTaskDto imcAddInspectionTaskDto, LoginAuthDto user)
    {
        //Bean拷贝
        AnImcInspectionTask anImcInspectionTask = new AnImcInspectionTask();
        BeanUtils.copyProperties(imcAddInspectionTaskDto,anImcInspectionTask);
        UpdateInfoUtil.setInsertInfo(anImcInspectionTask,user);

        //新建巡检任务
        Integer inspectionType = imcAddInspectionTaskDto.getInspectionType();
        Date startTime = imcAddInspectionTaskDto.getScheduledStartTime();
        anImcInspectionTask.setScheduledStartTime(startTime);
        if(TaskTypeEnum.FROM_PROJECT.getStatusNum() == inspectionType){
            //如果该任务是从项目中发起的，则无需甲方负责人审核
            anImcInspectionTask.setStatus(TaskStatusEnum.WAITING_FOR_ACCEPT.getStatusNum());
        }else{
            //将巡检任务的状态设置为等待甲方负责人审核
            anImcInspectionTask.setStatus(TaskStatusEnum.WAITING_FOR_PRINCIPAL.getStatusNum());
        }
        anImcInspectionTaskMapper.insert(anImcInspectionTask);
        logger.info("新增一条巡检任务记录：{}",anImcInspectionTask);
        Long taskId = anImcInspectionTask.getId();

        //获取所有的巡检任务子，并持久化
        List<ImcAddInspectionItemDto> imcAddInspectionItemDtoList = imcAddInspectionTaskDto.getImcAddInspectionItemDtoList();
        if(null!=imcAddInspectionItemDtoList){
            //保存新创建的巡检任务子项
            imcAddInspectionItemDtoList.forEach(item->{
                item.setInspectionTaskId(taskId);//设置巡检任务子项对应的任务id
                item.setDays(anImcInspectionTask.getDays());//设置巡检任务子项对应的巡检周期
                item.setFrequency(anImcInspectionTask.getFrequency());//设置巡检任务子项对应的巡检频率
                item.setScheduledStartTime(anImcInspectionTask.getScheduledStartTime());//设置巡检任务子项的对应的计划开始时间
                item.setStatus(ItemStatusEnum.WAITING_FOR_MAINTAINER.getStatusNum());
                //创建新的巡检任务子项，并更新返回结果
                BeanUtils.copyProperties(iAnImcInspectionItemService.insertAnImcInspectionItem(item,user),item);
            });
            BeanUtils.copyProperties(imcAddInspectionItemDtoList,imcAddInspectionTaskDto);
        }
        //更新返回结果
        BeanUtils.copyProperties(anImcInspectionTask,imcAddInspectionTaskDto);

        return imcAddInspectionTaskDto;
    }

    /**
     * 修改巡检任务表
     * 
     * @param anImcInspectionTask 巡检任务表
     * @return 结果
     */
    @Override
    public int updateAnImcInspectionTask(AnImcInspectionTask anImcInspectionTask)
    {
        anImcInspectionTask.setUpdateTime(DateUtils.getNowDate());
        return anImcInspectionTaskMapper.updateAnImcInspectionTask(anImcInspectionTask);
    }

    /**
     * 删除巡检任务表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnImcInspectionTaskByIds(String ids)
    {
        return anImcInspectionTaskMapper.deleteAnImcInspectionTaskByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除巡检任务表信息
     * 
     * @param id 巡检任务表ID
     * @return 结果
     */
    @Override
    public int deleteAnImcInspectionTaskById(Long id)
    {
        return anImcInspectionTaskMapper.deleteAnImcInspectionTaskById(id);
    }

    /**
     * 更改巡检任务状态
     * @param imcTaskChangeStatusDto
     * @param user
     * @return
     */
    @Override
    public AnImcInspectionTask modifyTaskStatus(ImcTaskChangeStatusDto imcTaskChangeStatusDto, LoginAuthDto user){
        Long taskId = imcTaskChangeStatusDto.getTaskId();
        Integer status = imcTaskChangeStatusDto.getStatus();
        AnImcInspectionTask anImcInspectionTask = new AnImcInspectionTask();
        anImcInspectionTask.setId(taskId);
        anImcInspectionTask.setStatus(status);
        UpdateInfoUtil.setModifyInfo(anImcInspectionTask,user);
        switch (TaskStatusEnum.getEnum(status)){
            case WAITING_FOR_PAY:
                //如果当前任务状态修改为等待支付，意味着任务已经被确认
                if(anImcInspectionTaskMapper.modifyTaskStatus(anImcInspectionTask)>0){
                    //获取全部的任务子项
                    //TODO
                    //任务已经巡检完毕，将全部任务子项的状态修改为已完成
                    //TODO
                    //用户确认完成后需要将巡检单据中的用户确认字段填入
                    //TODO
                    logger.info("任务状态已修改为待支付");
                }else{
                    throw new BusinessException("任务状态修改失败");
                }
                break;
            case WAITING_FOR_CONFIRM:
                //如果当前状态处于巡检完成等待甲方负责人确认的阶段
                //更新巡检完成的实际时间
                anImcInspectionTask.setActualFinishTime(new Date(System.currentTimeMillis()));
                if(anImcInspectionTaskMapper.modifyTaskStatus(anImcInspectionTask)<=0) {
                    throw new BusinessException("任务状态修改失败");
                }else{
                    logger.info("任务状态已被修改为完成待确认");
                }
                break;
            case INSPECTION_OVER:
                //如果巡检结束，自动生成附件
                if(anImcInspectionTaskMapper.modifyTaskStatus(anImcInspectionTask)>0){
                    //自动生成附件
                    //TODO
                    logger.info("任务状态已被修改为完成");
                }else{
                    throw new BusinessException("任务状态修改失败");
                }
                break;
            default:
                //如果是其他状态
                if(anImcInspectionTaskMapper.modifyTaskStatus(anImcInspectionTask)>0){
                    //直接修改任务状态
                    //TODO
                    logger.info("任务状态已被修改");
                }else{
                    throw new BusinessException("任务状态修改失败");
                }
                break;
        }
        return anImcInspectionTask;
    }
}
