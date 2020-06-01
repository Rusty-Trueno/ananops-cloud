package com.ananops.pmc.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ananops.common.core.dto.BaseQuery;
import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.common.utils.DateUtils;
import com.ananops.common.utils.Threads;
import com.ananops.common.utils.bean.UpdateInfoUtil;
import com.ananops.pmc.domain.AnPmcContract;
import com.ananops.pmc.domain.AnPmcProjectUser;
import com.ananops.pmc.dto.PmcProReqQueryDto;
import com.ananops.pmc.mapper.AnPmcContractMapper;
import com.ananops.pmc.mapper.AnPmcProjectUserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ananops.pmc.mapper.AnPmcProjectMapper;
import com.ananops.pmc.domain.AnPmcProject;
import com.ananops.pmc.service.IAnPmcProjectService;
import com.ananops.common.core.text.Convert;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ananops
 * @date 2020-05-28
 */
@Service
public class AnPmcProjectServiceImpl implements IAnPmcProjectService 
{
    private static final Logger logger = LoggerFactory.getLogger(Threads.class);

    @Resource
    private AnPmcProjectMapper anPmcProjectMapper;

    @Resource
    private AnPmcContractMapper anPmcContractMapper;

    @Resource
    private AnPmcProjectUserMapper anPmcProjectUserMapper;

    @Resource
    private AnPmcInspectTaskServiceImpl anPmcInspectTaskService;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public AnPmcProject selectAnPmcProjectById(Long id)
    {
        return anPmcProjectMapper.selectAnPmcProjectById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param anPmcProject 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<AnPmcProject> selectAnPmcProjectList(AnPmcProject anPmcProject)
    {
        return anPmcProjectMapper.selectAnPmcProjectList(anPmcProject);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param anPmcProject 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertAnPmcProject(AnPmcProject anPmcProject, LoginAuthDto loginAuthDto)
    {
        UpdateInfoUtil.setInsertInfo(anPmcProject,loginAuthDto);
        if (anPmcProject.getId() == null && anPmcProject.getIsContract() == 1) { //新增项目,有合同
            //TODO 生成id
//            anPmcProject.setId(super.generateId());
            AnPmcContract anPmcContract = anPmcContractMapper.selectByPrimaryKey(anPmcProject.getContractId());
            if (anPmcContract == null) {
                //TODO 待处理业务异常
//                throw new PmcBizException(ErrorCodeEnum.PMC10081003, pmcProject.getContractId());
            }
            //添加甲方、乙方信息
            anPmcProject.setPartyAId(anPmcContract.getPartyAId());
            anPmcProject.setPartyAName(anPmcContract.getPartyAName());
            anPmcProject.setPartyBId(anPmcContract.getPartyBId());
            anPmcProject.setPartyBName(anPmcContract.getPartyBName());

            //添加进关系表
            AnPmcProjectUser anPmcProjectUser = new AnPmcProjectUser();
            anPmcProjectUser.setProjectId(anPmcProject.getId());
            anPmcProjectUser.setUserId(loginAuthDto.getUserId());
            anPmcProjectUserMapper.insertSelective(anPmcProjectUser);

            //返回添加结果
            anPmcProject.setCreateTime(DateUtils.getNowDate());
//            return anPmcProjectMapper.insertSelective(anPmcProject);

        } else if (anPmcProject.getId() == null) {  //虚拟项目
            //TODO 待处理生成id
//            anPmcProject.setId(super.generateId());

            //添加进关系表
            AnPmcProjectUser anPmcProjectUser = new AnPmcProjectUser();
            anPmcProjectUser.setProjectId(anPmcProject.getId());
            anPmcProjectUser.setUserId(loginAuthDto.getUserId());
            anPmcProjectUserMapper.insertSelective(anPmcProjectUser);

            //返回添加结果
            anPmcProject.setCreateTime(DateUtils.getNowDate());
//            return anPmcProjectMapper.insertSelective(anPmcProject);
        }
        return anPmcProjectMapper.insertSelective(anPmcProject);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param anPmcProject 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateAnPmcProject(AnPmcProject anPmcProject)
    {
        anPmcProject.setUpdateTime(DateUtils.getNowDate());
        return anPmcProjectMapper.updateAnPmcProject(anPmcProject);
    }

    /**
     * 删除【请填写功能名称】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnPmcProjectByIds(String ids)
    {
        if (anPmcInspectTaskService.getTasksByProjectId(Long.parseLong(ids)) != null) {
            anPmcInspectTaskService.deleteTaskByProjectId(Long.parseLong(ids)); //删除级联的巡检任务
        }
        Example example = new Example(AnPmcProjectUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId", ids);
        if(anPmcProjectUserMapper.selectByExample(example)!=null){ //删除级联的项目用户关系表
            this.deleteProUser(Long.parseLong(ids));
        }
        Integer result = anPmcProjectMapper.deleteByPrimaryKey(Convert.toStrArray(ids));
        if (result < 1) {
            //TODO 业务异常处理
//            throw new PmcBizException(ErrorCodeEnum.PMC10081002, projectId);
        }

        return result;
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteAnPmcProjectById(Long id)
    {
        return anPmcProjectMapper.deleteAnPmcProjectById(id);
    }

    @Override
    public List<AnPmcProject> getProjectListByCompanyId(Long companyId) {
        //TODO 待企业管理处理好
//        CompanyDto companyDto = uacGroupFeignApi.getCompanyInfoById(groupId).getResult();
        //公司ID
//        groupId = companyDto.getId();
        Example example = new Example(AnPmcProject.class);
        Example.Criteria criteria = example.createCriteria();
        //TODO 待企业管理传companyId
        criteria.andEqualTo("partyAId", companyId);
        Example.Criteria criteria2 = example.createCriteria();
        criteria2.andEqualTo("partyBId", companyId);
        example.or(criteria2);
        example.setOrderByClause("create_time desc");
        List<AnPmcProject> anPmcProjectList = anPmcProjectMapper.selectByExample(example);
        return anPmcProjectList;
    }

    @Override
    public List<AnPmcProject> getProjectList(PmcProReqQueryDto pmcProReqQueryDto) {
        if (pmcProReqQueryDto.getDeptId() == null) {
            //TODO 业务异常处理
            logger.error("部门id为空！");
//            throw new PmcBizException(ErrorCodeEnum.PMC10081026);
        }

        //TODO 待企业管理完善
//        CompanyDto companyDto = uacGroupFeignApi.getCompanyInfoById(pmcProReqQueryDto.getGroupId()).getResult();
        //公司ID
//        Long deptId = companyDto.getId();
        int deptId = 103;
        Example example = new Example(AnPmcProject.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("partyAId", deptId);
        if (pmcProReqQueryDto.getProjectType() != null) {
            criteria.andEqualTo("projectType", pmcProReqQueryDto.getProjectType());
        }
        Example.Criteria criteria2 = example.createCriteria();
        criteria2.andEqualTo("partyBId", deptId);
        if (pmcProReqQueryDto.getProjectType() != null) {
            criteria2.andEqualTo("projectType", pmcProReqQueryDto.getProjectType());
        }
        example.or(criteria2);
        example.setOrderByClause("create_time desc");
        List<AnPmcProject> anPmcProjectList = anPmcProjectMapper.selectByExample(example);
        return anPmcProjectList;
    }

    @Override
    public PageInfo getProjectListWithPage(BaseQuery baseQuery) {
        PageHelper.startPage(baseQuery.getPageNum(), baseQuery.getPageSize());
        List<AnPmcProject> anPmcProjectList = anPmcProjectMapper.selectAll();
        return new PageInfo<>(anPmcProjectList);
    }

    @Override
    public List<AnPmcProject> getProjectByUserId(Long userId) {
        return anPmcProjectMapper.getProjectByUserId(userId);
    }

    @Override
    public List<AnPmcProject> getProjectByContractId(Long contractId) {
        return anPmcProjectMapper.getProjectByContractId(contractId);
    }

    @Override
    public int addProUser(AnPmcProjectUser anPmcProjectUser) {
        int result = 0;
        result = anPmcProjectUserMapper.insertSelective(anPmcProjectUser);
        return result;
    }

    @Override
    public List<AnPmcProjectUser> queryProUserByProjectId(Long projectId){
        Example example = new Example(AnPmcProjectUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId", projectId);
        return anPmcProjectUserMapper.selectByExample(example);
    }

    @Override
    public int deleteProUser(Long projectId) {
        int result = 0;
        Example example = new Example(AnPmcProjectUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId", projectId);
        result = anPmcProjectUserMapper.deleteByExample(example);
        return result;
    }

    @Override
    public int deleteProUser2(AnPmcProjectUser anPmcProjectUser) {
        int result = 0;
        Long projectId = anPmcProjectUser.getProjectId();
        Long userId = anPmcProjectUser.getUserId();
        Example example = new Example(AnPmcProjectUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId", projectId);
        criteria.andEqualTo("userId", userId);
        result = anPmcProjectUserMapper.deleteByExample(example);
        return result;
    }

    @Override
    public int getProjectCount(Long companyId) {
        //TODO 待企业管理返回
//        CompanyDto companyDto = uacGroupFeignApi.getCompanyInfoById(groupId).getResult();
        //公司ID
//        groupId = companyDto.getId();

        Example example = new Example(AnPmcProject.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("partyAId", companyId);
        Example.Criteria criteria2 = example.createCriteria();
        criteria2.andEqualTo("partyBId", companyId);
        example.or(criteria2);
        return anPmcProjectMapper.selectCountByExample(example);
    }

    @Override
    public List<Long> getEngineersIdByProjectId(Long projectId) {
        List<Long> engineersId = new ArrayList<>();
        Example example = new Example(AnPmcProjectUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId", projectId);
        List<AnPmcProjectUser> anPmcProjectUserList =  anPmcProjectUserMapper.selectByExample(example);
        for (AnPmcProjectUser pmcProjectUser : anPmcProjectUserList) {
            engineersId.add(pmcProjectUser.getUserId());
        }
        return  engineersId;
    }
}
