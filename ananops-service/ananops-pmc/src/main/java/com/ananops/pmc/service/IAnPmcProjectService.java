package com.ananops.pmc.service;

import com.ananops.pmc.domain.AnPmcProject;
import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ananops
 * @date 2020-05-28
 */
public interface IAnPmcProjectService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    AnPmcProject selectAnPmcProjectById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param anPmcProject 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
     List<AnPmcProject> selectAnPmcProjectList(AnPmcProject anPmcProject);

    /**
     * 新增【请填写功能名称】
     * 
     * @param anPmcProject 【请填写功能名称】
     * @return 结果
     */
     int insertAnPmcProject(AnPmcProject anPmcProject);

    /**
     * 修改【请填写功能名称】
     * 
     * @param anPmcProject 【请填写功能名称】
     * @return 结果
     */
     int updateAnPmcProject(AnPmcProject anPmcProject);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
     int deleteAnPmcProjectByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
     int deleteAnPmcProjectById(Long id);
}
