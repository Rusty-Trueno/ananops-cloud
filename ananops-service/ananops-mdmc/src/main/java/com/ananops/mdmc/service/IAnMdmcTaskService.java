package com.ananops.mdmc.service;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.mdmc.domain.AnMdmcTask;
import com.ananops.mdmc.dto.MdmcAddTaskDto;
import com.ananops.mdmc.dto.MdmcQueryDto;

import java.util.List;

/**
 * 维修工单Service接口
 *
 * @author ananops
 * @date 2020-05-25
 */
public interface IAnMdmcTaskService
{
    /**
     * 查询维修工单
     *
     * @param id 维修工单ID
     * @return 维修工单
     */
    public AnMdmcTask selectAnMdmcTaskById(Long id);

    /**
     * 查询维修工单列表
     *
     * @param queryDto 维修工单
     * @return 维修工单集合
     */
    public List<AnMdmcTask> selectAnMdmcTaskList(MdmcQueryDto queryDto);

    /**
     * 新增维修工单
     *
     * @param mdmcAddTaskDto 维修工单
     * @return 结果
     */
    public MdmcAddTaskDto insertAnMdmcTask(MdmcAddTaskDto mdmcAddTaskDto, LoginAuthDto loginAuthDto);

    /**
     * 修改维修工单
     *
     * @param updateTaskDto 维修工单
     * @return 结果
     */
    public AnMdmcTask updateAnMdmcTask(MdmcAddTaskDto updateTaskDto,LoginAuthDto loginAuthDto);

    /**
     * 批量删除维修工单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnMdmcTaskByIds(String ids);

    /**
     * 删除维修工单信息
     *
     * @param id 维修工单ID
     * @return 结果
     */
    public int deleteAnMdmcTaskById(Long id);
}
