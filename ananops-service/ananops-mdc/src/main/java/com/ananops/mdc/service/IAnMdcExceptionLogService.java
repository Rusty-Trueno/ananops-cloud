package com.ananops.mdc.service;

import com.ananops.mdc.domain.AnMdcExceptionLog;

import java.util.List;

/**
 * 全局异常记录Service接口
 * 
 * @author ananops
 * @date 2020-06-04
 */
public interface IAnMdcExceptionLogService 
{
    /**
     * 查询全局异常记录
     * 
     * @param id 全局异常记录ID
     * @return 全局异常记录
     */
    AnMdcExceptionLog selectAnMdcExceptionLogById(Long id);

    /**
     * 查询全局异常记录列表
     * 
     * @param anMdcExceptionLog 全局异常记录
     * @return 全局异常记录集合
     */
     List<AnMdcExceptionLog> selectAnMdcExceptionLogList(AnMdcExceptionLog anMdcExceptionLog);

    /**
     * 新增全局异常记录
     * 
     * @param anMdcExceptionLog 全局异常记录
     * @return 结果
     */
     int insertAnMdcExceptionLog(AnMdcExceptionLog anMdcExceptionLog);

    /**
     * 修改全局异常记录
     * 
     * @param anMdcExceptionLog 全局异常记录
     * @return 结果
     */
     int updateAnMdcExceptionLog(AnMdcExceptionLog anMdcExceptionLog);

    /**
     * 批量删除全局异常记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
     int deleteAnMdcExceptionLogByIds(String ids);

    /**
     * 删除全局异常记录信息
     * 
     * @param id 全局异常记录ID
     * @return 结果
     */
     int deleteAnMdcExceptionLogById(Long id);
}
