package com.ananops.pmc.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ananops.common.core.dto.BaseQuery;
import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.common.utils.DateUtils;
import com.ananops.common.utils.Threads;
import com.ananops.common.utils.bean.UpdateInfoUtil;
import com.ananops.pmc.domain.AnPmcContractUser;
import com.ananops.pmc.domain.AnPmcProject;
import com.ananops.pmc.dto.PmcContractDto;
import com.ananops.pmc.mapper.AnPmcContractUserMapper;
import com.ananops.pmc.mapper.AnPmcProjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ananops.pmc.mapper.AnPmcContractMapper;
import com.ananops.pmc.domain.AnPmcContract;
import com.ananops.pmc.service.IAnPmcContractService;
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
public class AnPmcContractServiceImpl implements IAnPmcContractService 
{
    private static final Logger logger = LoggerFactory.getLogger(Threads.class);

    @Resource
    private AnPmcContractMapper anPmcContractMapper;

    @Resource
    private AnPmcContractUserMapper anPmcContractUserMapper;

    @Resource
    private AnPmcProjectMapper anPmcProjectMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public AnPmcContract selectAnPmcContractById(Long id)
    {
        return anPmcContractMapper.selectAnPmcContractById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param anPmcContract 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<AnPmcContract> selectAnPmcContractList(AnPmcContract anPmcContract)
    {
        return anPmcContractMapper.selectAnPmcContractList(anPmcContract);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param pmcContractDto 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertAnPmcContract(PmcContractDto pmcContractDto, LoginAuthDto loginAuthDto)
    {
        AnPmcContract anPmcContract = new AnPmcContract();
        if(anPmcContractMapper.selectAnPmcContractById(pmcContractDto.getId()) == null){//如果没有这样的合同，则新建
            BeanUtils.copyProperties(pmcContractDto,anPmcContract);


            anPmcContract.setCreateTime(DateUtils.getNowDate());
            anPmcContract.setCreateBy(loginAuthDto.getUserName());
            anPmcContract.setCreatorId(loginAuthDto.getUserId());

            //添加进关系表
            AnPmcContractUser pmcContractUser = new AnPmcContractUser();
            pmcContractUser.setContractId(anPmcContract.getId());
            pmcContractUser.setUserId(loginAuthDto.getUserId());
            anPmcContractUserMapper.insertAnPmcContractUser(pmcContractUser);
        }else {                   //更新合同信息
            Integer result = anPmcContractMapper.updateByPrimaryKeySelective(anPmcContract);
            if (result<1){
//                throw new PmcBizException(ErrorCodeEnum.PMC10081011,pmcContract.getId());
                //TODO 业务异常处理
            }
        }

        //TODO 更新附件信息
//        List<Long> attachmentIds = new ArrayList<>();
//        if (pmcContract.getFilePath()!=null) {
//            String contractFileIds = pmcContract.getFilePath();
//            if (contractFileIds.contains(",")) {
//                String[] legalIds = contractFileIds.split(",");
//                for (String id : legalIds) {
//                    attachmentIds.add(Long.parseLong(id));
//                }
//            }else {
//                attachmentIds.add(Long.parseLong(contractFileIds));
//            }
//        }
//        OptAttachmentUpdateReqDto optAttachmentUpdateReqDto = new OptAttachmentUpdateReqDto();
//        optAttachmentUpdateReqDto.setAttachmentIds(attachmentIds);
//        optAttachmentUpdateReqDto.setLoginAuthDto(loginAuthDto);
//        optAttachmentUpdateReqDto.setRefNo(pmcContract.getId().toString());
//        opcOssFeignApi.batchUpdateAttachment(optAttachmentUpdateReqDto);
        return anPmcContractMapper.insert(anPmcContract);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param anPmcContract 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateAnPmcContract(AnPmcContract anPmcContract, LoginAuthDto loginAuthDto)
    {
        anPmcContract.setUpdateTime(DateUtils.getNowDate());
        UpdateInfoUtil.setModifyInfo(anPmcContract,loginAuthDto);
        return anPmcContractMapper.updateAnPmcContract(anPmcContract);
    }

    /**
     * 删除【请填写功能名称】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnPmcContractByIds(String ids)
    {
        List<AnPmcProject> pmcProjects = anPmcProjectMapper.getProjectByContractId(Long.parseLong(ids));
        if (pmcProjects.size() > 0) {
//            throw new PmcBizException(ErrorCodeEnum.PMC10081027, ids);
            //TODO 删除合同下的项目判断
            logger.error("请先删除项目下的所有合同！");
        }
        Integer result = anPmcContractMapper.deleteAnPmcContractById(Long.parseLong(ids));
        if (result<1){
//            throw new PmcBizException(ErrorCodeEnum.PMC10081012,id);
            //TODO 异常处理
            logger.error("删除失败！");
        }

        return anPmcContractMapper.deleteAnPmcContractByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteAnPmcContractById(Long id)
    {
        return anPmcContractMapper.deleteAnPmcContractById(id);
    }

    @Override
    public List<AnPmcContract> getContactListByCompanyId(Long companyId) {
        //TODO 等待企业管理把company做好
//        CompanyDto companyDto = uacGroupFeignApi.getCompanyInfoById(groupId).getResult();
//        //公司ID
//        groupId = companyDto.getId();
        Example example = new Example(AnPmcContract.class);
//        Example.Criteria criteria= example.createCriteria();
//        criteria.andEqualTo("partyAId",groupId);
//        Example.Criteria criteria2= example.createCriteria();
//        criteria2.andEqualTo("partyBId",groupId);
//        example.or(criteria2);
//        example.setOrderByClause("created_time desc");
        List<AnPmcContract> pmcContractList = anPmcContractMapper.selectByExample(example);
        return pmcContractList;
    }

    @Override
    public List<AnPmcContract> getContractListByLikePartyAName(String partyAName){
        partyAName = "%" + partyAName + "%";
        Example example = new Example(AnPmcContract.class);
        Example.Criteria criteria= example.createCriteria();
        criteria.andLike("partyAName",partyAName);
        List<AnPmcContract> pmcContractList = anPmcContractMapper.selectByExample(example);
        return pmcContractList;
    }

    @Override
    public List<AnPmcContract> getContractListByLikePartyBName(String partyBName){
        partyBName = "%" + partyBName + "%";
        Example example = new Example(AnPmcContract.class);
        Example.Criteria criteria= example.createCriteria();
        criteria.andLike("partyBName",partyBName);
        List<AnPmcContract> pmcContractList = anPmcContractMapper.selectByExample(example);
        return pmcContractList;
    }

    @Override
    public PageInfo getContractListWithPage(BaseQuery baseQuery) {
        //1. 设置分页
        PageHelper.startPage(baseQuery.getPageNum(),baseQuery.getPageSize());
        //2. 查询
        List<AnPmcContract> pmcContractList = anPmcContractMapper.selectAll();
        //3. 返回
        return new PageInfo<>(pmcContractList);
    }

    @Override
    public List<AnPmcContract> getContactByAB(Long partyAId, Long partyBId) {
        Example example = new Example(AnPmcContract.class);
        Example.Criteria criteria= example.createCriteria();
        criteria.andEqualTo("partyAId",partyAId);
        criteria.andEqualTo("partyBId",partyBId);
        return anPmcContractMapper.selectByExample(example);
    }

    @Override
    public int getContractCount(Long groupId) {
//        CompanyDto companyDto = uacGroupFeignApi.getCompanyInfoById(groupId).getResult();
//        groupId = companyDto.getId();
        Example example = new Example(AnPmcContract.class);
        Example.Criteria criteria= example.createCriteria();
        criteria.andEqualTo("partyAId",groupId);
        Example.Criteria criteria2= example.createCriteria();
        criteria2.andEqualTo("partyBId",groupId);
        example.or(criteria2);
        return anPmcContractMapper.selectCountByExample(example);
    }
}
