package com.ananops.mdmc.mapper;

import com.ananops.common.core.dao.BaseMapper;
import com.ananops.mdmc.domain.AnMdmcTask;
import com.ananops.mdmc.dto.MdmcDispatchDto;
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
     * 根据报修人id查询工单列表
     *
     * @param id 用户id
     * @return 结果
     */
    public List<AnMdmcTask> selectByUserId(Long id);

    /**
     * 根据报修人id和状态查询工单列表
     *
     * @param id 用户id
     * @return 结果
     */
    public List<AnMdmcTask> selectByUserIdAndStatus(@Param("status")Integer status,@Param("id")Long id);


    /**
     * 根据负责人id查询工单列表
     *
     * @param id 负责人id
     * @return 结果
     */
    public List<AnMdmcTask> selectByBossId(Long id);

    /**
     * 根据负责人id和状态查询工单列表
     *
     * @param id 用户id
     * @return 结果
     */
    public List<AnMdmcTask> selectByBossIdAndStatus(@Param("status")Integer status,@Param("id")Long id);

    /**
     * 服务商获取工单列表
     *
     * @param id 用户id
     * @return 结果
     */
    public List<AnMdmcTask> selectByFacId(Long id);


    /**
     * 根据服务商id和状态查询工单列表
     *
     * @param id 用户id
     * @return 结果
     */
    public List<AnMdmcTask> selectByFacIdAndStatus(@Param("status")Integer status,@Param("id")Long id);

    /**
     * 工程师获取工单列表
     *
     * @param id 用户id
     * @return 结果
     */
    public List<AnMdmcTask> selectByMantainerId(Long id);


    /**
     * 根据工程师id和状态查询工单列表
     *
     * @param id 用户id
     * @return 结果
     */
    public List<AnMdmcTask> selectByMaintainerIdAndStatus(@Param("status")Integer status,@Param("id")Long id);


    /**
     * 更改工单状态
     *
     * @param task 工单
     * @return 结果
     */
    public int updateTaskStatus(AnMdmcTask task);

    /**
     * 服务商拒单，把服务商id设为空
     *
     * @param id 工单id
     * @return 结果
     */
    public int rejectByFac(Long id);

    /**
     * 工程师拒单，把工程师id设为空
     *
     * @param id 工单id
     * @return 结果
     */
    public int rejectByMan(Long id);

    /**
     * 平台管理员重新分配服务商或者服务商分配工程师
     *
     * @param dispatchDto 工单dto
     * @return 结果
     */
    public int dispatchFacOrEng(MdmcDispatchDto dispatchDto);


}
