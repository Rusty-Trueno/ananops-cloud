package com.ananops.mdmc.service.impl;

import java.util.List;
import com.ananops.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ananops.mdmc.mapper.AnMdmcTaskItemLogMapper;
import com.ananops.mdmc.domain.AnMdmcTaskItemLog;
import com.ananops.mdmc.service.IAnMdmcTaskItemLogService;
import com.ananops.common.core.text.Convert;

/**
 * 维修任务子项日志Service业务层处理
 *
 * @author ananops
 * @date 2020-05-25
 */
@Service
public class AnMdmcTaskItemLogServiceImpl implements IAnMdmcTaskItemLogService
{
    @Autowired
    private AnMdmcTaskItemLogMapper anMdmcTaskItemLogMapper;

    /**
     * 查询维修任务子项日志
     *
     * @param id 维修任务子项日志ID
     * @return 维修任务子项日志
     */
    @Override
    public AnMdmcTaskItemLog selectAnMdmcTaskItemLogById(Long id)
    {
        return anMdmcTaskItemLogMapper.selectAnMdmcTaskItemLogById(id);
    }

    /**
     * 查询维修任务子项日志列表
     *
     * @param anMdmcTaskItemLog 维修任务子项日志
     * @return 维修任务子项日志
     */
    @Override
    public List<AnMdmcTaskItemLog> selectAnMdmcTaskItemLogList(AnMdmcTaskItemLog anMdmcTaskItemLog)
    {
        return anMdmcTaskItemLogMapper.selectAnMdmcTaskItemLogList(anMdmcTaskItemLog);
    }

    /**
     * 新增维修任务子项日志
     *
     * @param anMdmcTaskItemLog 维修任务子项日志
     * @return 结果
     */
    @Override
    public int insertAnMdmcTaskItemLog(AnMdmcTaskItemLog anMdmcTaskItemLog)
    {
        return anMdmcTaskItemLogMapper.insert(anMdmcTaskItemLog);
    }

    /**
     * 修改维修任务子项日志
     *
     * @param anMdmcTaskItemLog 维修任务子项日志
     * @return 结果
     */
    @Override
    public int updateAnMdmcTaskItemLog(AnMdmcTaskItemLog anMdmcTaskItemLog)
    {
        return anMdmcTaskItemLogMapper.updateAnMdmcTaskItemLog(anMdmcTaskItemLog);
    }

    /**
     * 删除维修任务子项日志对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnMdmcTaskItemLogByIds(String ids)
    {
        return anMdmcTaskItemLogMapper.deleteAnMdmcTaskItemLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除维修任务子项日志信息
     *
     * @param id 维修任务子项日志ID
     * @return 结果
     */
    public int deleteAnMdmcTaskItemLogById(Long id)
    {
        return anMdmcTaskItemLogMapper.deleteAnMdmcTaskItemLogById(id);
    }
}
