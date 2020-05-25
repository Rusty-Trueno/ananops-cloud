package com.ananops.imc.service.impl;

import java.util.Date;
import java.util.List;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.common.core.service.BaseService;
import com.ananops.common.exception.BusinessException;
import com.ananops.common.utils.DateUtils;
import com.ananops.common.utils.StringUtils;
import com.ananops.common.utils.bean.BeanUtils;
import com.ananops.common.utils.bean.UpdateInfoUtil;
import com.ananops.imc.domain.AnImcInspectionItem;
import com.ananops.imc.dto.*;
import com.ananops.imc.enums.ItemStatusEnum;
import com.ananops.imc.enums.TaskStatusEnum;
import com.ananops.imc.enums.TaskTypeEnum;
import com.ananops.imc.mapper.AnImcInspectionItemMapper;
import com.ananops.imc.service.IAnImcInspectionItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ananops.imc.mapper.AnImcInspectionTaskMapper;
import com.ananops.imc.domain.AnImcInspectionTask;
import com.ananops.imc.service.IAnImcInspectionTaskService;
import com.ananops.common.core.text.Convert;
import tk.mybatis.mapper.entity.Example;

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
    private AnImcInspectionItemMapper anImcInspectionItemMapper;

    @Autowired
    private IAnImcInspectionItemService anImcInspectionItemService;

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
                BeanUtils.copyProperties(anImcInspectionItemService.insertAnImcInspectionItem(item,user),item);
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
    public ImcTaskChangeStatusDto modifyTaskStatus(ImcTaskChangeStatusDto imcTaskChangeStatusDto, LoginAuthDto user){
        imcTaskChangeStatusDto.setStatusMsg(TaskStatusEnum.getStatusMsg(imcTaskChangeStatusDto.getStatus()));
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
                    Example example = new Example(AnImcInspectionItem.class);
                    Example.Criteria criteria = example.createCriteria();
                    criteria.andEqualTo("inspectionTaskId",taskId);
                    List<AnImcInspectionItem> anImcInspectionItems = anImcInspectionItemMapper.selectByExample(example);
                    if(null != anImcInspectionItems && anImcInspectionItems.size() > 0){
                        anImcInspectionItems.forEach(item->{
                            //任务已经巡检完毕，将全部任务子项的状态修改为已完成
                            ImcItemChangeStatusDto imcItemChangeStatusDto = new ImcItemChangeStatusDto();
                            imcItemChangeStatusDto.setLoginAuthDto(user);
                            imcItemChangeStatusDto.setStatus(ItemStatusEnum.VERIFIED.getStatusNum());
                            imcItemChangeStatusDto.setItemId(item.getId());
                            anImcInspectionItemService.modifyImcItemStatus(imcItemChangeStatusDto,user);
                            //用户确认完成后需要将巡检单据中的用户确认字段填入
                            //TODO
                        });
                    }
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
                    logger.info("任务状态已被修改");
                }else{
                    throw new BusinessException("任务状态修改失败");
                }
                break;
        }
        return imcTaskChangeStatusDto;
    }

    /**
     * 判断巡检任务是否完成
     * @param taskId
     * @return
     */
    @Override
    public boolean isTaskFinish(Long taskId){
        Example example = new Example(AnImcInspectionItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("inspectionTaskId",taskId);
        List<AnImcInspectionItem> imcInspectionItemList = anImcInspectionItemMapper.selectByExample(example);
        for(int i=0;i<imcInspectionItemList.size();i++){
            if(imcInspectionItemList.get(i).getStatus()<ItemStatusEnum.INSPECTION_OVER.getStatusNum()){
                //如果还有巡检任务子项没完成
                return false;
            }
        }
        //如果巡检任务子项都完成了，则巡检任务也完成了
        return true;
    }

    /**
     * 根据项目id查询对应的巡检任务列表
     * @param taskQueryDto
     * @return
     */
    @Override
    public List<AnImcInspectionTask> getTaskByProjectId(TaskQueryDto taskQueryDto){
        Example example = new Example(AnImcInspectionTask.class);
        Example.Criteria criteria = example.createCriteria();
        Long projectId = taskQueryDto.getProjectId();
        String taskName = taskQueryDto.getTaskName();
        if(StringUtils.isNotBlank(taskName)){
            taskName = "%" + taskName + "%";
            criteria.andLike("taskName",taskName);
        }
        criteria.andEqualTo("projectId",projectId);
        example.setOrderByClause("update_time DESC");
        PageHelper.startPage(taskQueryDto.getPageNum(),taskQueryDto.getPageSize());
        return anImcInspectionTaskMapper.selectByExample(example);
    }
}
