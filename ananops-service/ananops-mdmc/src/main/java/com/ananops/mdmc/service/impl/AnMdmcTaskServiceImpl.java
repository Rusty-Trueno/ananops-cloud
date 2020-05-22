package com.ananops.mdmc.service.impl;

import java.util.List;
import com.ananops.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ananops.mdmc.mapper.AnMdmcTaskMapper;
import com.ananops.mdmc.domain.AnMdmcTask;
import com.ananops.mdmc.service.IAnMdmcTaskService;
import com.ananops.common.core.text.Convert;

/**
 * 维修工单Service业务层处理
 *
 * @author ananops
 * @date 2020-05-22
 */
@Service
public class AnMdmcTaskServiceImpl implements IAnMdmcTaskService
{
    @Autowired
    private AnMdmcTaskMapper anMdmcTaskMapper;

    /**
     * 查询维修工单
     *
     * @param id 维修工单ID
     * @return 维修工单
     */
    @Override
    public AnMdmcTask selectAnMdmcTaskById(Long id)
    {
        return anMdmcTaskMapper.selectAnMdmcTaskById(id);
    }

    /**
     * 查询维修工单列表
     *
     * @param anMdmcTask 维修工单
     * @return 维修工单
     */
    @Override
    public List<AnMdmcTask> selectAnMdmcTaskList(AnMdmcTask anMdmcTask)
    {
        return anMdmcTaskMapper.selectAnMdmcTaskList(anMdmcTask);
    }

    /**
     * 新增维修工单
     *
     * @param anMdmcTask 维修工单
     * @return 结果
     */
    @Override
    public int insertAnMdmcTask(AnMdmcTask anMdmcTask)
    {
        return anMdmcTaskMapper.insert(anMdmcTask);
    }

    /**
     * 修改维修工单
     *
     * @param anMdmcTask 维修工单
     * @return 结果
     */
    @Override
    public int updateAnMdmcTask(AnMdmcTask anMdmcTask)
    {
        return anMdmcTaskMapper.updateAnMdmcTask(anMdmcTask);
    }

    /**
     * 删除维修工单对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnMdmcTaskByIds(String ids)
    {
        return anMdmcTaskMapper.deleteAnMdmcTaskByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除维修工单信息
     *
     * @param id 维修工单ID
     * @return 结果
     */
    public int deleteAnMdmcTaskById(Long id)
    {
        return anMdmcTaskMapper.deleteAnMdmcTaskById(id);
    }
}
