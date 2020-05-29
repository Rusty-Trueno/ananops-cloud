package com.ananops.mdmc.mapper;

import com.ananops.common.core.dao.BaseMapper;
import com.ananops.mdmc.domain.AnMdmcTaskItem;
import java.util.List;

/**
 * 维修任务子项Mapper接口
 *
 * @author ananops
 * @date 2020-05-25
 */
public interface AnMdmcTaskItemMapper extends BaseMapper<AnMdmcTaskItem>
{
    /**
     * 查询维修任务子项
     *
     * @param id 维修任务子项ID
     * @return 维修任务子项
     */
    public AnMdmcTaskItem selectAnMdmcTaskItemById(Long id);

    /**
     * 根据工单id查询维修任务子项列表
     *
     * @param id 工单id
     * @return 维修任务子项集合
     */
    public List<AnMdmcTaskItem> selectAnMdmcTaskItemByTaskId(Long id);

    /**
     * 新增维修任务子项
     *
     * @param anMdmcTaskItem 维修任务子项
     * @return 结果
     */
    public int insertAnMdmcTaskItem(AnMdmcTaskItem anMdmcTaskItem);

    /**
     * 修改维修任务子项
     *
     * @param anMdmcTaskItem 维修任务子项
     * @return 结果
     */
    public int updateAnMdmcTaskItem(AnMdmcTaskItem anMdmcTaskItem);

    /**
     * 删除维修任务子项
     *
     * @param id 维修任务子项ID
     * @return 结果
     */
    public int deleteAnMdmcTaskItemById(Long id);

    /**
     * 批量删除维修任务子项
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnMdmcTaskItemByIds(String[] ids);
}
