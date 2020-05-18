package com.ananops.amc.service;

import com.ananops.amc.domain.AnAmcAlarm;
import java.util.List;

/**
 * 告警Service接口
 * 
 * @author ananops
 * @date 2020-05-18
 */
public interface IAnAmcAlarmService 
{
    /**
     * 查询告警
     * 
     * @param alarmId 告警ID
     * @return 告警
     */
    public AnAmcAlarm selectAnAmcAlarmById(Long alarmId);

    /**
     * 查询告警列表
     * 
     * @param anAmcAlarm 告警
     * @return 告警集合
     */
    public List<AnAmcAlarm> selectAnAmcAlarmList(AnAmcAlarm anAmcAlarm);

    /**
     * 新增告警
     * 
     * @param anAmcAlarm 告警
     * @return 结果
     */
    public int insertAnAmcAlarm(AnAmcAlarm anAmcAlarm);

    /**
     * 修改告警
     * 
     * @param anAmcAlarm 告警
     * @return 结果
     */
    public int updateAnAmcAlarm(AnAmcAlarm anAmcAlarm);

    /**
     * 批量删除告警
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnAmcAlarmByIds(String ids);

    /**
     * 删除告警信息
     * 
     * @param alarmId 告警ID
     * @return 结果
     */
    public int deleteAnAmcAlarmById(Long alarmId);
}
