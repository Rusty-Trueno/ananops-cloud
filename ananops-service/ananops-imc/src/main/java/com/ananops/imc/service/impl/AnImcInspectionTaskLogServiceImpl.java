package com.ananops.imc.service.impl;

import java.util.List;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.common.utils.DateUtils;
import com.ananops.common.utils.bean.UpdateInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ananops.imc.mapper.AnImcInspectionTaskLogMapper;
import com.ananops.imc.domain.AnImcInspectionTaskLog;
import com.ananops.imc.service.IAnImcInspectionTaskLogService;
import com.ananops.common.core.text.Convert;

/**
 * 巡检任务日志Service业务层处理
 * 
 * @author ananops
 * @date 2020-05-22
 */
@Service
public class AnImcInspectionTaskLogServiceImpl implements IAnImcInspectionTaskLogService 
{
    @Autowired
    private AnImcInspectionTaskLogMapper anImcInspectionTaskLogMapper;

    /**
     * 查询巡检任务日志
     * 
     * @param id 巡检任务日志ID
     * @return 巡检任务日志
     */
    @Override
    public AnImcInspectionTaskLog selectAnImcInspectionTaskLogById(Long id)
    {
        return anImcInspectionTaskLogMapper.selectAnImcInspectionTaskLogById(id);
    }

    /**
     * 查询巡检任务日志列表
     * 
     * @param anImcInspectionTaskLog 巡检任务日志
     * @return 巡检任务日志
     */
    @Override
    public List<AnImcInspectionTaskLog> selectAnImcInspectionTaskLogList(AnImcInspectionTaskLog anImcInspectionTaskLog)
    {
        return anImcInspectionTaskLogMapper.selectAnImcInspectionTaskLogList(anImcInspectionTaskLog);
    }

    /**
     * 新增巡检任务日志
     * 
     * @param anImcInspectionTaskLog 巡检任务日志
     * @return 结果
     */
    @Override
    public int insertAnImcInspectionTaskLog(AnImcInspectionTaskLog anImcInspectionTaskLog, LoginAuthDto user)
    {
        UpdateInfoUtil.setInsertInfo(anImcInspectionTaskLog,user);
        return anImcInspectionTaskLogMapper.insert(anImcInspectionTaskLog);
    }

    /**
     * 修改巡检任务日志
     * 
     * @param anImcInspectionTaskLog 巡检任务日志
     * @return 结果
     */
    @Override
    public int updateAnImcInspectionTaskLog(AnImcInspectionTaskLog anImcInspectionTaskLog)
    {
        anImcInspectionTaskLog.setUpdateTime(DateUtils.getNowDate());
        return anImcInspectionTaskLogMapper.updateAnImcInspectionTaskLog(anImcInspectionTaskLog);
    }

    /**
     * 删除巡检任务日志对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnImcInspectionTaskLogByIds(String ids)
    {
        return anImcInspectionTaskLogMapper.deleteAnImcInspectionTaskLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除巡检任务日志信息
     * 
     * @param id 巡检任务日志ID
     * @return 结果
     */
    public int deleteAnImcInspectionTaskLogById(Long id)
    {
        return anImcInspectionTaskLogMapper.deleteAnImcInspectionTaskLogById(id);
    }
}
