package com.ananops.amc.service.impl;

import java.util.List;
import com.ananops.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ananops.amc.mapper.AnAmcAlarmMapper;
import com.ananops.amc.domain.AnAmcAlarm;
import com.ananops.amc.service.IAnAmcAlarmService;
import com.ananops.common.core.text.Convert;

import javax.annotation.Resource;

/**
 * 告警Service业务层处理
 * 
 * @author ananops
 * @date 2020-05-18
 */
@Service
public class AnAmcAlarmServiceImpl implements IAnAmcAlarmService 
{
    @Resource
    private AnAmcAlarmMapper anAmcAlarmMapper;

    /**
     * 查询告警
     * 
     * @param alarmId 告警ID
     * @return 告警
     */
    @Override
    public AnAmcAlarm selectAnAmcAlarmById(Long alarmId)
    {
        return anAmcAlarmMapper.selectAnAmcAlarmById(alarmId);
    }

    /**
     * 查询告警列表
     * 
     * @param anAmcAlarm 告警
     * @return 告警
     */
    @Override
    public List<AnAmcAlarm> selectAnAmcAlarmList(AnAmcAlarm anAmcAlarm)
    {
        return anAmcAlarmMapper.selectAnAmcAlarmList(anAmcAlarm);
    }

    /**
     * 新增告警
     * 
     * @param anAmcAlarm 告警
     * @return 结果
     */
    @Override
    public int insertAnAmcAlarm(AnAmcAlarm anAmcAlarm)
    {
        anAmcAlarm.setCreateTime(DateUtils.getNowDate());
        return anAmcAlarmMapper.insertAnAmcAlarm(anAmcAlarm);
    }

    /**
     * 修改告警
     * 
     * @param anAmcAlarm 告警
     * @return 结果
     */
    @Override
    public int updateAnAmcAlarm(AnAmcAlarm anAmcAlarm)
    {
        anAmcAlarm.setUpdateTime(DateUtils.getNowDate());
        return anAmcAlarmMapper.updateAnAmcAlarm(anAmcAlarm);
    }

    /**
     * 删除告警对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnAmcAlarmByIds(String ids)
    {
        return anAmcAlarmMapper.deleteAnAmcAlarmByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除告警信息
     * 
     * @param alarmId 告警ID
     * @return 结果
     */
    public int deleteAnAmcAlarmById(Long alarmId)
    {
        return anAmcAlarmMapper.deleteAnAmcAlarmById(alarmId);
    }
}
