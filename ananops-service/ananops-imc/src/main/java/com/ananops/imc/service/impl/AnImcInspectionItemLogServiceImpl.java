package com.ananops.imc.service.impl;

import java.util.List;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.common.utils.DateUtils;
import com.ananops.common.utils.bean.UpdateInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ananops.imc.mapper.AnImcInspectionItemLogMapper;
import com.ananops.imc.domain.AnImcInspectionItemLog;
import com.ananops.imc.service.IAnImcInspectionItemLogService;
import com.ananops.common.core.text.Convert;

/**
 * 巡检任务子项日志Service业务层处理
 * 
 * @author ananops
 * @date 2020-05-22
 */
@Service
public class AnImcInspectionItemLogServiceImpl implements IAnImcInspectionItemLogService 
{
    @Autowired
    private AnImcInspectionItemLogMapper anImcInspectionItemLogMapper;

    /**
     * 查询巡检任务子项日志
     * 
     * @param id 巡检任务子项日志ID
     * @return 巡检任务子项日志
     */
    @Override
    public AnImcInspectionItemLog selectAnImcInspectionItemLogById(Long id)
    {
        return anImcInspectionItemLogMapper.selectAnImcInspectionItemLogById(id);
    }

    /**
     * 查询巡检任务子项日志列表
     * 
     * @param anImcInspectionItemLog 巡检任务子项日志
     * @return 巡检任务子项日志
     */
    @Override
    public List<AnImcInspectionItemLog> selectAnImcInspectionItemLogList(AnImcInspectionItemLog anImcInspectionItemLog)
    {
        return anImcInspectionItemLogMapper.selectAnImcInspectionItemLogList(anImcInspectionItemLog);
    }

    /**
     * 新增巡检任务子项日志
     * 
     * @param anImcInspectionItemLog 巡检任务子项日志
     * @return 结果
     */
    @Override
    public int insertAnImcInspectionItemLog(AnImcInspectionItemLog anImcInspectionItemLog, LoginAuthDto user)
    {
        UpdateInfoUtil.setInsertInfo(anImcInspectionItemLog,user);
        return anImcInspectionItemLogMapper.insert(anImcInspectionItemLog);
    }

    /**
     * 修改巡检任务子项日志
     * 
     * @param anImcInspectionItemLog 巡检任务子项日志
     * @return 结果
     */
    @Override
    public int updateAnImcInspectionItemLog(AnImcInspectionItemLog anImcInspectionItemLog)
    {
        anImcInspectionItemLog.setUpdateTime(DateUtils.getNowDate());
        return anImcInspectionItemLogMapper.updateAnImcInspectionItemLog(anImcInspectionItemLog);
    }

    /**
     * 删除巡检任务子项日志对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnImcInspectionItemLogByIds(String ids)
    {
        return anImcInspectionItemLogMapper.deleteAnImcInspectionItemLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除巡检任务子项日志信息
     * 
     * @param id 巡检任务子项日志ID
     * @return 结果
     */
    public int deleteAnImcInspectionItemLogById(Long id)
    {
        return anImcInspectionItemLogMapper.deleteAnImcInspectionItemLogById(id);
    }
}
