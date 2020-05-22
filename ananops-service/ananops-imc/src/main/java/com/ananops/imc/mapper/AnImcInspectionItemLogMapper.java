package com.ananops.imc.mapper;

import com.ananops.common.core.dao.BaseMapper;
import com.ananops.imc.domain.AnImcInspectionItemLog;
import java.util.List;

/**
 * 巡检任务子项日志Mapper接口
 * 
 * @author ananops
 * @date 2020-05-22
 */
public interface AnImcInspectionItemLogMapper extends BaseMapper<AnImcInspectionItemLog>
{
    /**
     * 查询巡检任务子项日志
     * 
     * @param id 巡检任务子项日志ID
     * @return 巡检任务子项日志
     */
    public AnImcInspectionItemLog selectAnImcInspectionItemLogById(Long id);

    /**
     * 查询巡检任务子项日志列表
     * 
     * @param anImcInspectionItemLog 巡检任务子项日志
     * @return 巡检任务子项日志集合
     */
    public List<AnImcInspectionItemLog> selectAnImcInspectionItemLogList(AnImcInspectionItemLog anImcInspectionItemLog);

    /**
     * 新增巡检任务子项日志
     * 
     * @param anImcInspectionItemLog 巡检任务子项日志
     * @return 结果
     */
    public int insertAnImcInspectionItemLog(AnImcInspectionItemLog anImcInspectionItemLog);

    /**
     * 修改巡检任务子项日志
     * 
     * @param anImcInspectionItemLog 巡检任务子项日志
     * @return 结果
     */
    public int updateAnImcInspectionItemLog(AnImcInspectionItemLog anImcInspectionItemLog);

    /**
     * 删除巡检任务子项日志
     * 
     * @param id 巡检任务子项日志ID
     * @return 结果
     */
    public int deleteAnImcInspectionItemLogById(Long id);

    /**
     * 批量删除巡检任务子项日志
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnImcInspectionItemLogByIds(String[] ids);
}
