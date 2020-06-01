package com.ananops.pmc.mapper;

import com.ananops.common.core.dao.BaseMapper;
import com.ananops.pmc.domain.AnPmcProjectUser;
import java.util.List;

/**
 * 项目用户中间表Mapper接口
 * 
 * @author ananops
 * @date 2020-05-31
 */
public interface AnPmcProjectUserMapper extends BaseMapper<AnPmcProjectUser>
{
    /**
     * 查询项目用户中间表
     * 
     * @param projectId 项目用户中间表ID
     * @return 项目用户中间表
     */
    public AnPmcProjectUser selectAnPmcProjectUserById(Long projectId);

    /**
     * 查询项目用户中间表列表
     * 
     * @param anPmcProjectUser 项目用户中间表
     * @return 项目用户中间表集合
     */
     List<AnPmcProjectUser> selectAnPmcProjectUserList(AnPmcProjectUser anPmcProjectUser);

    /**
     * 新增项目用户中间表
     * 
     * @param anPmcProjectUser 项目用户中间表
     * @return 结果
     */
     int insertAnPmcProjectUser(AnPmcProjectUser anPmcProjectUser);

    /**
     * 修改项目用户中间表
     * 
     * @param anPmcProjectUser 项目用户中间表
     * @return 结果
     */
    int updateAnPmcProjectUser(AnPmcProjectUser anPmcProjectUser);

    /**
     * 删除项目用户中间表
     * 
     * @param projectId 项目用户中间表ID
     * @return 结果
     */
    int deleteAnPmcProjectUserById(Long projectId);

    /**
     * 批量删除项目用户中间表
     * 
     * @param projectIds 需要删除的数据ID
     * @return 结果
     */
    int deleteAnPmcProjectUserByIds(String[] projectIds);
}
