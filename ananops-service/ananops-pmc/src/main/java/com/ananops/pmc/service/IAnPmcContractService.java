package com.ananops.pmc.service;

import com.ananops.common.core.dto.BaseQuery;
import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.pmc.domain.AnPmcContract;
import com.ananops.pmc.dto.PmcContractDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ananops
 * @date 2020-05-28
 */
public interface IAnPmcContractService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    AnPmcContract selectAnPmcContractById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param anPmcContract 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
     List<AnPmcContract> selectAnPmcContractList(AnPmcContract anPmcContract);

    /**
     * 新增【请填写功能名称】
     * 
     * @param pmcContractDto 【请填写功能名称】
     * @return 结果
     */
     int insertAnPmcContract(PmcContractDto pmcContractDto, LoginAuthDto loginAuthDto);

    /**
     * 修改【请填写功能名称】
     * 
     * @param anPmcContract 【请填写功能名称】
     * @return 结果
     */
     int updateAnPmcContract(AnPmcContract anPmcContract, LoginAuthDto loginAuthDto);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
     int deleteAnPmcContractByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
     int deleteAnPmcContractById(Long id);

    /**
     * 根据组织id查询合同列表
     * @param companyId
     * @return 合同列表
     */
    List<AnPmcContract> getContactListByCompanyId(Long companyId);

    /**
     * 根据甲方组织名模糊查询其对应的全部合同
     * @param partyAName
     * @return
     */
    List<AnPmcContract> getContractListByLikePartyAName(String partyAName);

    /**
     * 根据乙方组织名模糊查询其对应的全部合同
     * @param partyBName
     * @return
     */
    List<AnPmcContract> getContractListByLikePartyBName(String partyBName);

    /**
     * 查询所有合同
     * @param baseQuery
     * @return
     */
    PageInfo getContractListWithPage(BaseQuery baseQuery);

    /**
     * 获取甲乙双方签订的合同
     * @param partyAId
     * @param partyBId
     * @return
     */
    List<AnPmcContract> getContactByAB(Long partyAId, Long partyBId);

    /**
     * 获取合同总数
     * @param groupId
     * @return
     */
    int getContractCount(Long groupId);
}
