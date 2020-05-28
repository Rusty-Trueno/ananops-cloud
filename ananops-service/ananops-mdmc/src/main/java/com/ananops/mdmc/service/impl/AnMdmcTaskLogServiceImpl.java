package com.ananops.mdmc.service.impl;

import java.util.List;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.common.exception.BusinessException;
import com.ananops.common.utils.DateUtils;
import com.ananops.common.utils.bean.UpdateInfoUtil;
import com.ananops.mdmc.domain.AnMdmcTask;
import com.ananops.mdmc.dto.MdmcQueryDto;
import com.ananops.mdmc.enums.MdmcTaskStatusEnum;
import com.ananops.mdmc.mapper.AnMdmcTaskMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import com.ananops.mdmc.mapper.AnMdmcTaskLogMapper;
import com.ananops.mdmc.domain.AnMdmcTaskLog;
import com.ananops.mdmc.service.IAnMdmcTaskLogService;
import com.ananops.common.core.text.Convert;

import javax.annotation.Resource;

/**
 * 维修工单日志Service业务层处理
 *
 * @author ananops
 * @date 2020-05-25
 */
@Service
public class AnMdmcTaskLogServiceImpl implements IAnMdmcTaskLogService
{
    @Resource
    private AnMdmcTaskLogMapper anMdmcTaskLogMapper;

    @Resource
    private AnMdmcTaskMapper anMdmcTaskMapper;

    /**
     * 查询维修工单日志
     *
     * @param id 维修工单日志ID
     * @return 维修工单日志
     */
    @Override
    public AnMdmcTaskLog selectAnMdmcTaskLogById(Long id)
    {
        return anMdmcTaskLogMapper.selectAnMdmcTaskLogById(id);
    }

    /**
     * 根据工单id查询维修工单日志列表
     *
     * @param queryDto 维修工单id
     * @return 维修工单日志
     */
    @Override
    public PageInfo selectAnMdmcTaskLogList(MdmcQueryDto queryDto)
    {
        Long taskId=queryDto.getTaskId();
        PageHelper.startPage(queryDto.getPageNum(),queryDto.getPageSize());
        List<AnMdmcTaskLog> logs=anMdmcTaskLogMapper.selectAnMdmcTaskLogListByTaskId(taskId);
        return new PageInfo<>(logs);
    }

    /**
     * 新增维修工单日志
     *
     * @param anMdmcTaskLog 维修工单日志
     * @return 结果
     */
    @Override
    public int insertAnMdmcTaskLog(AnMdmcTaskLog anMdmcTaskLog)
    {
        Long taskId=anMdmcTaskLog.getTaskId();
        if(taskId==null){
            throw new BusinessException("工单id不能是空");
        }
        if(anMdmcTaskMapper.selectByPrimaryKey(taskId)==null){
            throw new BusinessException("找不到当前工单");
        }
        AnMdmcTask task=anMdmcTaskMapper.selectByPrimaryKey(taskId);
        Integer level=task.getLevel();
        Integer status=task.getStatus();
        String move="";
        move+= MdmcTaskStatusEnum.getStatusMsg(status)+",当前工单紧急程度是"+level;
        AnMdmcTaskLog taskLog=new AnMdmcTaskLog();
        BeanUtils.copyProperties(anMdmcTaskLog,taskLog);
        taskLog.setMovement(move);
        BeanUtils.copyProperties(task,taskLog);
        return anMdmcTaskLogMapper.insert(taskLog);
    }

    /**
     * 修改维修工单日志
     *
     * @param anMdmcTaskLog 维修工单日志
     * @return 结果
     */
    @Override
    public int updateAnMdmcTaskLog(AnMdmcTaskLog anMdmcTaskLog)
    {
        return anMdmcTaskLogMapper.updateAnMdmcTaskLog(anMdmcTaskLog);
    }

    /**
     * 删除维修工单日志对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnMdmcTaskLogByIds(String ids)
    {
        return anMdmcTaskLogMapper.deleteAnMdmcTaskLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除维修工单日志信息
     *
     * @param id 维修工单日志ID
     * @return 结果
     */
    public int deleteAnMdmcTaskLogById(Long id)
    {
        return anMdmcTaskLogMapper.deleteAnMdmcTaskLogById(id);
    }
}
