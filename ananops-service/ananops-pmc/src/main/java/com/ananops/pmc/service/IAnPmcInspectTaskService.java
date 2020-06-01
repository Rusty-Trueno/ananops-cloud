package com.ananops.pmc.service;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.pmc.domain.AnPmcInspectTask;
import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ananops
 * @date 2020-05-28
 */
public interface IAnPmcInspectTaskService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    AnPmcInspectTask selectAnPmcInspectTaskById(Long id);

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
     int insertAnPmcInspectTask(AnPmcInspectTask anPmcInspectTask, LoginAuthDto loginAuthDto);

    /**
     * 修改【请填写功能名称】
     * 
     * @param anPmcInspectTask 【请填写功能名称】
     * @return 结果
     */
     int updateAnPmcInspectTask(AnPmcInspectTask anPmcInspectTask);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
     int deleteAnPmcInspectTaskByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
     int deleteAnPmcInspectTaskById(Long id);

    /**
     * 获取某个项目的巡检任务
     * @param projectId
     * @return
     */
    List<AnPmcInspectTask> getTasksByProjectId(Long projectId);

    /**
     *
     * @param projectId
     */
    int deleteTaskByProjectId(Long projectId);
}
