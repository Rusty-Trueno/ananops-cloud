package com.ananops.imc.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ananops.imc.mapper.AnImcInspectionTaskMapper;
import com.ananops.imc.domain.AnImcInspectionTask;
import com.ananops.imc.service.IAnImcInspectionTaskService;
import com.ananops.common.core.text.Convert;

/**
 * 巡检任务Service业务层处理
 *
 * @author rongshuai
 * @date 2020-05-18
 */
@Service
public class AnImcInspectionTaskServiceImpl implements IAnImcInspectionTaskService
{
    @Autowired
    private AnImcInspectionTaskMapper anImcInspectionTaskMapper;

    /**
     * 查询巡检任务
     *
     * @param id 巡检任务ID
     * @return 巡检任务
     */
    @Override
    public AnImcInspectionTask selectAnImcInspectionTaskById(Long id)
    {
        return anImcInspectionTaskMapper.selectAnImcInspectionTaskById(id);
    }

    /**
     * 查询巡检任务列表
     *
     * @param anImcInspectionTask 巡检任务
     * @return 巡检任务
     */
    @Override
    public List<AnImcInspectionTask> selectAnImcInspectionTaskList(AnImcInspectionTask anImcInspectionTask)
    {
        return anImcInspectionTaskMapper.selectAnImcInspectionTaskList(anImcInspectionTask);
    }

    /**
     * 新增巡检任务
     *
     * @param anImcInspectionTask 巡检任务
     * @return 结果
     */
    @Override
    public int insertAnImcInspectionTask(AnImcInspectionTask anImcInspectionTask)
    {
        return anImcInspectionTaskMapper.insertAnImcInspectionTask(anImcInspectionTask);
    }

    /**
     * 修改巡检任务
     *
     * @param anImcInspectionTask 巡检任务
     * @return 结果
     */
    @Override
    public int updateAnImcInspectionTask(AnImcInspectionTask anImcInspectionTask)
    {
        return anImcInspectionTaskMapper.updateAnImcInspectionTask(anImcInspectionTask);
    }

    /**
     * 删除巡检任务对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnImcInspectionTaskByIds(String ids)
    {
        return anImcInspectionTaskMapper.deleteAnImcInspectionTaskByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除巡检任务信息
     *
     * @param id 巡检任务ID
     * @return 结果
     */
    public int deleteAnImcInspectionTaskById(Long id)
    {
        return anImcInspectionTaskMapper.deleteAnImcInspectionTaskById(id);
    }
}