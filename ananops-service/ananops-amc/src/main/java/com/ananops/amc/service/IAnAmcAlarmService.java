package com.ananops.amc.service;

import com.ananops.amc.domain.AnAmcAlarm;
import com.ananops.common.core.dto.LoginAuthDto;

import java.util.List;

/**
 * 告警Service接口
 *
 * @author ananops
 * @date 2020-05-20
 */
public interface IAnAmcAlarmService {
    /**
     * 查询告警
     *
     * @param id 告警ID
     * @return 告警
     */
    public AnAmcAlarm selectAnAmcAlarmById(Long id);

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
    public int insertAnAmcAlarm(AnAmcAlarm anAmcAlarm, LoginAuthDto user);

    /**
     * 修改告警
     *
     * @param anAmcAlarm 告警
     * @return 结果
     */
    public int updateAnAmcAlarm(AnAmcAlarm anAmcAlarm, LoginAuthDto user);

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
     * @param id 告警ID
     * @return 结果
     */
    public int deleteAnAmcAlarmById(Long id);

    /**
     * 获取总告警数
     *
     * @return
     */
    int getAllAlarmCount(AnAmcAlarm anAmcAlarm);

    /**
     * 获取待处理告警数
     *
     * @return
     */
    int getDealingCount(AnAmcAlarm anAmcAlarm);

    /**
     * 获取急需处理告警数
     *
     * @return
     */
    int getUrgencyCount(AnAmcAlarm anAmcAlarm);

    /**
     * 获取已处理告警数
     *
     * @return
     */
    int getDealedCount(AnAmcAlarm anAmcAlarm);
}
