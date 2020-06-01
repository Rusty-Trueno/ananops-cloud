package com.ananops.pmc.service;

import com.ananops.common.core.dto.BaseQuery;
import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.pmc.domain.AnPmcProject;
import com.ananops.pmc.domain.AnPmcProjectUser;
import com.ananops.pmc.dto.PmcProReqQueryDto;
import com.github.pagehelper.PageInfo;

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
     int insertAnPmcProject(AnPmcProject anPmcProject, LoginAuthDto loginAuthDto);

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

    /**
     * 获取某个组织下的项目列表
     * @param companyId
     * @return
     */
    List<AnPmcProject> getProjectListByCompanyId(Long companyId);

    /**
     * 根据项目类型+公司组织Id查询项目列表
     *
     * @param pmcProReqQueryDto
     *
     * @return
     */
    List<AnPmcProject> getProjectList(PmcProReqQueryDto pmcProReqQueryDto);

    /**
     * 分页获取所有项目列表
     * @param baseQuery
     * @return
     */
    PageInfo getProjectListWithPage(BaseQuery baseQuery);

    /**
     * 获取某个用户的项目列表
     * @param userId
     * @return
     */
    List<AnPmcProject> getProjectByUserId(Long userId);

    /**
     * 根据合同Id获取项目列表
     *
     * @param contractId 合同Id
     *
     * @return 返回项目列表
     */
    List<AnPmcProject> getProjectByContractId(Long contractId);

    /**
     * 添加项目用户信息
     * @param anPmcProjectUser
     * @return
     */
    int addProUser(AnPmcProjectUser anPmcProjectUser);

    List<AnPmcProjectUser> queryProUserByProjectId(Long projectId);

    /**
     *删除项目用户关联信息
     * @param
     * @return
     */
    int deleteProUser(Long ProjectId);

    int deleteProUser2(AnPmcProjectUser anPmcProjectUser);

    /**
     * 获取项目总数
     * @param companyId
     * @return
     */
    int getProjectCount(Long companyId);

    /**
     * 根据项目id获取工程师id列表
     * @param projectId
     * @return
     */
    List<Long> getEngineersIdByProjectId(Long projectId);
}
