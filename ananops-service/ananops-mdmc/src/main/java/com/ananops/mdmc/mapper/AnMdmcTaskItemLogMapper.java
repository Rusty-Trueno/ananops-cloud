package com.ananops.mdmc.mapper;

import com.ananops.common.core.dao.BaseMapper;
import com.ananops.mdmc.domain.AnMdmcTaskItemLog;
import java.util.List;

/**
 * 维修任务子项日志Mapper接口
 *
 * @author ananops
 * @date 2020-05-25
 */
public interface AnMdmcTaskItemLogMapper extends BaseMapper<AnMdmcTaskItemLog>
{
    /**
     * 查询维修任务子项日志
     *
     * @param id 维修任务子项日志ID
     * @return 维修任务子项日志
     */
    public AnMdmcTaskItemLog selectAnMdmcTaskItemLogById(Long id);

    /**
     * 查询维修任务子项日志列表
     *
     * @param anMdmcTaskItemLog 维修任务子项日志
     * @return 维修任务子项日志集合
     */
    public List<AnMdmcTaskItemLog> selectAnMdmcTaskItemLogList(AnMdmcTaskItemLog anMdmcTaskItemLog);

    /**
     * 新增维修任务子项日志
     *
     * @param anMdmcTaskItemLog 维修任务子项日志
     * @return 结果
     */
    public int insertAnMdmcTaskItemLog(AnMdmcTaskItemLog anMdmcTaskItemLog);

    /**
     * 修改维修任务子项日志
     *
     * @param anMdmcTaskItemLog 维修任务子项日志
     * @return 结果
     */
    public int updateAnMdmcTaskItemLog(AnMdmcTaskItemLog anMdmcTaskItemLog);

    /**
     * 删除维修任务子项日志
     *
     * @param id 维修任务子项日志ID
     * @return 结果
     */
    public int deleteAnMdmcTaskItemLogById(Long id);

    /**
     * 批量删除维修任务子项日志
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnMdmcTaskItemLogByIds(String[] ids);
}
