package com.ananops.imc.service;

import com.ananops.imc.domain.AnImcInspectionTask;
import java.util.List;

/**
 * 巡检任务Service接口
 *
 * @author rongshuai
 * @date 2020-05-18
 */
public interface IAnImcInspectionTaskService
{
    /**
     * 查询巡检任务
     *
     * @param id 巡检任务ID
     * @return 巡检任务
     */
    public AnImcInspectionTask selectAnImcInspectionTaskById(Long id);

    /**
     * 查询巡检任务列表
     *
     * @param anImcInspectionTask 巡检任务
     * @return 巡检任务集合
     */
    public List<AnImcInspectionTask> selectAnImcInspectionTaskList(AnImcInspectionTask anImcInspectionTask);

    /**
     * 新增巡检任务
     *
     * @param anImcInspectionTask 巡检任务
     * @return 结果
     */
    public int insertAnImcInspectionTask(AnImcInspectionTask anImcInspectionTask);

    /**
     * 修改巡检任务
     *
     * @param anImcInspectionTask 巡检任务
     * @return 结果
     */
    public int updateAnImcInspectionTask(AnImcInspectionTask anImcInspectionTask);

    /**
     * 批量删除巡检任务
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnImcInspectionTaskByIds(String ids);

    /**
     * 删除巡检任务信息
     *
     * @param id 巡检任务ID
     * @return 结果
     */
    public int deleteAnImcInspectionTaskById(Long id);
}