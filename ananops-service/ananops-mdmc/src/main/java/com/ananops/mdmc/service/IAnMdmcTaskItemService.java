package com.ananops.mdmc.service;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.mdmc.domain.AnMdmcTaskItem;
import com.ananops.mdmc.dto.MdmcAddTaskDto;
import com.ananops.mdmc.dto.MdmcAddTaskItemDto;
import com.ananops.mdmc.dto.MdmcQueryDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 维修任务子项Service接口
 *
 * @author ananops
 * @date 2020-05-25
 */
public interface IAnMdmcTaskItemService
{
    /**
     * 查询维修任务子项
     *
     * @param id 维修任务子项ID
     * @return 维修任务子项
     */
    public AnMdmcTaskItem selectAnMdmcTaskItemById(Long id);

    /**
     * 查询维修任务子项列表
     *
     * @param queryDto 维修任务子项
     * @return 维修任务子项集合
     */
    public PageInfo selectAnMdmcTaskItemList(MdmcQueryDto queryDto);

    /**
     * 新增维修任务子项
     *
     * @param itemDto 维修任务子项
     * @return 结果
     */
    public AnMdmcTaskItem insertAnMdmcTaskItem(MdmcAddTaskItemDto itemDto, LoginAuthDto loginAuthDto);

    /**
     * 修改维修任务子项
     *
     * @param itemDto 维修任务子项
     * @return 结果
     */
    public AnMdmcTaskItem updateAnMdmcTaskItem(MdmcAddTaskItemDto itemDto,LoginAuthDto loginAuthDto);

    /**
     * 批量删除维修任务子项
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnMdmcTaskItemByIds(String ids);

    /**
     * 删除维修任务子项信息
     *
     * @param id 维修任务子项ID
     * @return 结果
     */
    public int deleteAnMdmcTaskItemById(Long id);
}
