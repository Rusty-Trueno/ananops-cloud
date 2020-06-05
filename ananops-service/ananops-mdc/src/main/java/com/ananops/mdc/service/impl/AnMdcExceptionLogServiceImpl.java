package com.ananops.mdc.service.impl;

import com.ananops.common.core.text.Convert;
import com.ananops.common.utils.DateUtils;
import com.ananops.mdc.domain.AnMdcExceptionLog;
import com.ananops.mdc.mapper.AnMdcExceptionLogMapper;
import com.ananops.mdc.service.IAnMdcExceptionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 全局异常记录Service业务层处理
 * 
 * @author ananops
 * @date 2020-06-04
 */
@Service
public class AnMdcExceptionLogServiceImpl implements IAnMdcExceptionLogService
{
    @Autowired
    private AnMdcExceptionLogMapper anMdcExceptionLogMapper;

    /**
     * 查询全局异常记录
     * 
     * @param id 全局异常记录ID
     * @return 全局异常记录
     */
    @Override
    public AnMdcExceptionLog selectAnMdcExceptionLogById(Long id)
    {
        return anMdcExceptionLogMapper.selectAnMdcExceptionLogById(id);
    }

    /**
     * 查询全局异常记录列表
     * 
     * @param anMdcExceptionLog 全局异常记录
     * @return 全局异常记录
     */
    @Override
    public List<AnMdcExceptionLog> selectAnMdcExceptionLogList(AnMdcExceptionLog anMdcExceptionLog)
    {
        return anMdcExceptionLogMapper.selectAnMdcExceptionLogList(anMdcExceptionLog);
    }

    /**
     * 新增全局异常记录
     * 
     * @param anMdcExceptionLog 全局异常记录
     * @return 结果
     */
    @Override
    public int insertAnMdcExceptionLog(AnMdcExceptionLog anMdcExceptionLog)
    {
        anMdcExceptionLog.setCreateTime(DateUtils.getNowDate());
        return anMdcExceptionLogMapper.insert(anMdcExceptionLog);
    }

    /**
     * 修改全局异常记录
     * 
     * @param anMdcExceptionLog 全局异常记录
     * @return 结果
     */
    @Override
    public int updateAnMdcExceptionLog(AnMdcExceptionLog anMdcExceptionLog)
    {
        anMdcExceptionLog.setUpdateTime(DateUtils.getNowDate());
        return anMdcExceptionLogMapper.updateAnMdcExceptionLog(anMdcExceptionLog);
    }

    /**
     * 删除全局异常记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnMdcExceptionLogByIds(String ids)
    {
        return anMdcExceptionLogMapper.deleteAnMdcExceptionLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除全局异常记录信息
     * 
     * @param id 全局异常记录ID
     * @return 结果
     */
    public int deleteAnMdcExceptionLogById(Long id)
    {
        return anMdcExceptionLogMapper.deleteAnMdcExceptionLogById(id);
    }
}
