package com.ananops.mdmc.mapper;

import com.ananops.common.core.dao.BaseMapper;
import com.ananops.mdmc.domain.AnMdmcTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 维修工单Mapper接口
 *
 * @author ananops
 * @date 2020-05-25
 */
public interface AnMdmcTaskMapper extends BaseMapper<AnMdmcTask>
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
     * @param anMdmcTask 维修工单
     * @return 维修工单集合
     */
    public List<AnMdmcTask> selectAnMdmcTaskList(AnMdmcTask anMdmcTask);

    /**
     * 新增维修工单
     *
     * @param anMdmcTask 维修工单
     * @return 结果
     */
    public int insertAnMdmcTask(AnMdmcTask anMdmcTask);

    /**
     * 修改维修工单
     *
     * @param anMdmcTask 维修工单
     * @return 结果
     */
    public int updateAnMdmcTask(AnMdmcTask anMdmcTask);

    /**
     * 删除维修工单
     *
     * @param id 维修工单ID
     * @return 结果
     */
    public int deleteAnMdmcTaskById(Long id);

    /**
     * 批量删除维修工单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnMdmcTaskByIds(String[] ids);

    /**
     * 根据用户id查询工单列表
     *
     * @param id 用户id
     * @return 结果
     */
    public List<AnMdmcTask> selectAnMdmcTaskListByUserId(Long id);

    /**
     * 根据用户id和状态查询工单列表
     *
     * @param id 用户id
     * @return 结果
     */
    public List<AnMdmcTask> selectBySomeoneIdAndStatus(@Param("status")Integer status,@Param("id")Long id);

    /**
     * 服务商获取工单列表
     *
     * @param id 用户id
     * @return 结果
     */
    public List<AnMdmcTask> selectByFacId(Long id);

    /**
     * 工程师获取工单列表
     *
     * @param id 用户id
     * @return 结果
     */
    public List<AnMdmcTask> selectByMantainerId(Long id);

    /**
     * 更改工单状态
     *
     * @param task 工单
     * @return 结果
     */
    public int updateTaskStatus(AnMdmcTask task);

}
