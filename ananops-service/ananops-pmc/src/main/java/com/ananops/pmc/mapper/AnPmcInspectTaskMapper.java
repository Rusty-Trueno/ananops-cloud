package com.ananops.pmc.mapper;

import com.ananops.common.core.dao.BaseMapper;
import com.ananops.pmc.domain.AnPmcInspectTask;
import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ananops
 * @date 2020-05-28
 */
public interface AnPmcInspectTaskMapper extends BaseMapper<AnPmcInspectTask>
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public AnPmcInspectTask selectAnPmcInspectTaskById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param anPmcInspectTask 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
     List<AnPmcInspectTask> selectAnPmcInspectTaskList(AnPmcInspectTask anPmcInspectTask);

    /**
     * 新增【请填写功能名称】
     * 
     * @param anPmcInspectTask 【请填写功能名称】
     * @return 结果
     */
     int insertAnPmcInspectTask(AnPmcInspectTask anPmcInspectTask);

    /**
     * 修改【请填写功能名称】
     * 
     * @param anPmcInspectTask 【请填写功能名称】
     * @return 结果
     */
    int updateAnPmcInspectTask(AnPmcInspectTask anPmcInspectTask);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    int deleteAnPmcInspectTaskById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteAnPmcInspectTaskByIds(String[] ids);
}
