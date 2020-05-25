package com.ananops.mdmc.service.impl;

import java.util.List;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.common.exception.BusinessException;
import com.ananops.common.utils.bean.BeanUtils;
import com.ananops.common.utils.bean.UpdateInfoUtil;
import com.ananops.mdmc.domain.AnMdmcTaskItem;
import com.ananops.mdmc.dto.MdmcAddTaskDto;
import com.ananops.mdmc.dto.MdmcAddTaskItemDto;
import com.ananops.mdmc.dto.MdmcQueryDto;
import com.ananops.mdmc.enums.MdmcTaskStatusEnum;
import com.ananops.mdmc.service.IAnMdmcTaskItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ananops.mdmc.mapper.AnMdmcTaskMapper;
import com.ananops.mdmc.domain.AnMdmcTask;
import com.ananops.mdmc.service.IAnMdmcTaskService;
import com.ananops.common.core.text.Convert;

/**
 * 维修工单Service业务层处理
 *
 * @author ananops
 * @date 2020-05-25
 */
@Service
public class AnMdmcTaskServiceImpl implements IAnMdmcTaskService
{
    @Autowired
    private AnMdmcTaskMapper anMdmcTaskMapper;

    @Autowired
    private IAnMdmcTaskItemService itemService;

    /**
     * 查询维修工单
     *
     * @param id 维修工单ID
     * @return 维修工单
     */
    @Override
    public AnMdmcTask selectAnMdmcTaskById(Long id)
    {
        return anMdmcTaskMapper.selectAnMdmcTaskById(id);
    }

    /**
     * 查询维修工单列表
     *
     * @param queryDto 维修工单
     * @return 维修工单
     */
    @Override
    public List<AnMdmcTask> selectAnMdmcTaskList(MdmcQueryDto queryDto)
    {
        String roleCode="";
        Long id=queryDto.getId();
        Integer status=queryDto.getStatus();
        //todo 调用uac查角色
        //todo 分页
        if(status==null){
            if(roleCode!=null){
                if(roleCode.equals("fac_service")||roleCode.equals("fac_manager")||roleCode.equals("fac_leader")){
                    return anMdmcTaskMapper.selectByFacId(id);
                }
                if(roleCode.equals("engineer")){
                    return anMdmcTaskMapper.selectByMantainerId(id);
                }
                else {
                    return  anMdmcTaskMapper.selectAnMdmcTaskListByUserId(id);
                }
            }
            else {
                return anMdmcTaskMapper.selectAnMdmcTaskListByUserId(id);
            }
        }
        else {
            return anMdmcTaskMapper.selectBySomeoneIdAndStatus(status,id);
        }

    }

    /**
     * 新增维修工单
     *
     * @param mdmcAddTaskDto 维修工单
     * @return 结果
     */
    @Override
    public MdmcAddTaskDto insertAnMdmcTask(MdmcAddTaskDto mdmcAddTaskDto, LoginAuthDto loginAuthDto)
    {
        AnMdmcTask task = new AnMdmcTask();
        BeanUtils.copyProperties(mdmcAddTaskDto,task);
        UpdateInfoUtil.setInsertInfo(task,loginAuthDto);

        task.setStatus(MdmcTaskStatusEnum.ShenHeZhong1.getStatusNum());
        anMdmcTaskMapper.insert(task);
        Long taskId=task.getId();

        //todo 上传文件

        //获取所有的任务子项
        List<MdmcAddTaskItemDto> mdmcAddTaskItemDtoList = mdmcAddTaskDto.getMdmcAddTaskItemDtoList();
        if (mdmcAddTaskItemDtoList!=null){
            mdmcAddTaskItemDtoList.forEach(itemDto->{
                //设置任务子项对应的任务id
                itemDto.setTaskId(taskId);
                //创建新的任务子项，并更新返回结果
                AnMdmcTaskItem item = itemService.insertAnMdmcTaskItem(itemDto,loginAuthDto);
                BeanUtils.copyProperties(item ,itemDto);
            });
            BeanUtils.copyProperties(mdmcAddTaskItemDtoList,mdmcAddTaskDto);
        }

        //更新返回结果
        BeanUtils.copyProperties(task,mdmcAddTaskDto);

        //todo 消息
        return mdmcAddTaskDto;
    }

    /**
     * 修改维修工单
     *
     * @param updateTaskDto 维修工单
     * @return 结果
     */
    @Override
    public AnMdmcTask updateAnMdmcTask(MdmcAddTaskDto updateTaskDto,LoginAuthDto loginAuthDto)
    {
        AnMdmcTask task = new AnMdmcTask();
        BeanUtils.copyProperties(updateTaskDto,task);
        UpdateInfoUtil.setInsertInfo(task,loginAuthDto);
        Long taskId=updateTaskDto.getId();
        if(anMdmcTaskMapper.selectByPrimaryKey(taskId)==null){
            throw new BusinessException("当前工单不存在");
        }

        anMdmcTaskMapper.updateByPrimaryKeySelective(task);

        AnMdmcTask task1=anMdmcTaskMapper.selectByPrimaryKey(taskId);
        BeanUtils.copyProperties(task1,task);

        //todo 文件
        //todo 巡检
        //todo 消息
        return task;
    }

    /**
     * 删除维修工单对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnMdmcTaskByIds(String ids)
    {
        return anMdmcTaskMapper.deleteAnMdmcTaskByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除维修工单信息
     *
     * @param id 维修工单ID
     * @return 结果
     */
    public int deleteAnMdmcTaskById(Long id)
    {
        return anMdmcTaskMapper.deleteAnMdmcTaskById(id);
    }
}
