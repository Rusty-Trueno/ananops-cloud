package com.ananops.mdmc.service;

import com.ananops.mdmc.domain.AnMdmcTaskLog;
import java.util.List;

/**
 * 维修工单日志Service接口
 *
 * @author ananops
 * @date 2020-05-25
 */
public interface IAnMdmcTaskLogService
{
    /**
     * 查询维修工单日志
     *
     * @param id 维修工单日志ID
     * @return 维修工单日志
     */
    public AnMdmcTaskLog selectAnMdmcTaskLogById(Long id);

    /**
     * 查询维修工单日志列表
     *
     * @param anMdmcTaskLog 维修工单日志
     * @return 维修工单日志集合
     */
    public List<AnMdmcTaskLog> selectAnMdmcTaskLogList(AnMdmcTaskLog anMdmcTaskLog);

    /**
     * 新增维修工单日志
     *
     * @param anMdmcTaskLog 维修工单日志
     * @return 结果
     */
    public int insertAnMdmcTaskLog(AnMdmcTaskLog anMdmcTaskLog);

    /**
     * 修改维修工单日志
     *
     * @param anMdmcTaskLog 维修工单日志
     * @return 结果
     */
    public int updateAnMdmcTaskLog(AnMdmcTaskLog anMdmcTaskLog);

    /**
     * 批量删除维修工单日志
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnMdmcTaskLogByIds(String ids);

    /**
     * 删除维修工单日志信息
     *
     * @param id 维修工单日志ID
     * @return 结果
     */
    public int deleteAnMdmcTaskLogById(Long id);
}
