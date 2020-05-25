package com.ananops.mdmc.service.impl;

import java.util.List;
import com.ananops.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ananops.mdmc.mapper.AnMdmcTaskLogMapper;
import com.ananops.mdmc.domain.AnMdmcTaskLog;
import com.ananops.mdmc.service.IAnMdmcTaskLogService;
import com.ananops.common.core.text.Convert;

/**
 * 维修工单日志Service业务层处理
 *
 * @author ananops
 * @date 2020-05-25
 */
@Service
public class AnMdmcTaskLogServiceImpl implements IAnMdmcTaskLogService
{
    @Autowired
    private AnMdmcTaskLogMapper anMdmcTaskLogMapper;

    /**
     * 查询维修工单日志
     *
     * @param id 维修工单日志ID
     * @return 维修工单日志
     */
    @Override
    public AnMdmcTaskLog selectAnMdmcTaskLogById(Long id)
    {
        return anMdmcTaskLogMapper.selectAnMdmcTaskLogById(id);
    }

    /**
     * 查询维修工单日志列表
     *
     * @param anMdmcTaskLog 维修工单日志
     * @return 维修工单日志
     */
    @Override
    public List<AnMdmcTaskLog> selectAnMdmcTaskLogList(AnMdmcTaskLog anMdmcTaskLog)
    {
        return anMdmcTaskLogMapper.selectAnMdmcTaskLogList(anMdmcTaskLog);
    }

    /**
     * 新增维修工单日志
     *
     * @param anMdmcTaskLog 维修工单日志
     * @return 结果
     */
    @Override
    public int insertAnMdmcTaskLog(AnMdmcTaskLog anMdmcTaskLog)
    {
        return anMdmcTaskLogMapper.insert(anMdmcTaskLog);
    }

    /**
     * 修改维修工单日志
     *
     * @param anMdmcTaskLog 维修工单日志
     * @return 结果
     */
    @Override
    public int updateAnMdmcTaskLog(AnMdmcTaskLog anMdmcTaskLog)
    {
        return anMdmcTaskLogMapper.updateAnMdmcTaskLog(anMdmcTaskLog);
    }

    /**
     * 删除维修工单日志对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnMdmcTaskLogByIds(String ids)
    {
        return anMdmcTaskLogMapper.deleteAnMdmcTaskLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除维修工单日志信息
     *
     * @param id 维修工单日志ID
     * @return 结果
     */
    public int deleteAnMdmcTaskLogById(Long id)
    {
        return anMdmcTaskLogMapper.deleteAnMdmcTaskLogById(id);
    }
}
