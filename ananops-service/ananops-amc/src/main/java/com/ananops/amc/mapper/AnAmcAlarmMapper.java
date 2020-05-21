package com.ananops.amc.mapper;

import com.ananops.common.core.dao.BaseMapper;
import com.ananops.amc.domain.AnAmcAlarm;
import java.util.List;

/**
 * 告警Mapper接口
 * 
 * @author ananops
 * @date 2020-05-20
 */
public interface AnAmcAlarmMapper extends BaseMapper<AnAmcAlarm>
{
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
    public int insertAnAmcAlarm(AnAmcAlarm anAmcAlarm);

    /**
     * 修改告警
     * 
     * @param anAmcAlarm 告警
     * @return 结果
     */
    public int updateAnAmcAlarm(AnAmcAlarm anAmcAlarm);

    /**
     * 删除告警
     * 
     * @param id 告警ID
     * @return 结果
     */
    public int deleteAnAmcAlarmById(Long id);

    /**
     * 批量删除告警
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnAmcAlarmByIds(String[] ids);

    int getAllAlarmCount(AnAmcAlarm anAmcAlarm);

    int getDealingCount(AnAmcAlarm anAmcAlarm);

    int getUrgencyCount(AnAmcAlarm anAmcAlarm);

    int getDealedCount(AnAmcAlarm anAmcAlarm);
}
