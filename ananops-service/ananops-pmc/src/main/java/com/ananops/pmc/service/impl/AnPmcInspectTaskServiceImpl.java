package com.ananops.pmc.service.impl;

import java.util.List;
import com.ananops.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ananops.pmc.mapper.AnPmcInspectTaskMapper;
import com.ananops.pmc.domain.AnPmcInspectTask;
import com.ananops.pmc.service.IAnPmcInspectTaskService;
import com.ananops.common.core.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ananops
 * @date 2020-05-28
 */
@Service
public class AnPmcInspectTaskServiceImpl implements IAnPmcInspectTaskService 
{
    @Autowired
    private AnPmcInspectTaskMapper anPmcInspectTaskMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public AnPmcInspectTask selectAnPmcInspectTaskById(Long id)
    {
        return anPmcInspectTaskMapper.selectAnPmcInspectTaskById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param anPmcInspectTask 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<AnPmcInspectTask> selectAnPmcInspectTaskList(AnPmcInspectTask anPmcInspectTask)
    {
        return anPmcInspectTaskMapper.selectAnPmcInspectTaskList(anPmcInspectTask);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param anPmcInspectTask 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertAnPmcInspectTask(AnPmcInspectTask anPmcInspectTask)
    {
        anPmcInspectTask.setCreateTime(DateUtils.getNowDate());
        return anPmcInspectTaskMapper.insert(anPmcInspectTask);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param anPmcInspectTask 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateAnPmcInspectTask(AnPmcInspectTask anPmcInspectTask)
    {
        anPmcInspectTask.setUpdateTime(DateUtils.getNowDate());
        return anPmcInspectTaskMapper.updateAnPmcInspectTask(anPmcInspectTask);
    }

    /**
     * 删除【请填写功能名称】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnPmcInspectTaskByIds(String ids)
    {
        return anPmcInspectTaskMapper.deleteAnPmcInspectTaskByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteAnPmcInspectTaskById(Long id)
    {
        return anPmcInspectTaskMapper.deleteAnPmcInspectTaskById(id);
    }
}
