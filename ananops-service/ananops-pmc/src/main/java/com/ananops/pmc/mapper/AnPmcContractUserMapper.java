package com.ananops.pmc.mapper;

import com.ananops.common.core.dao.BaseMapper;
import com.ananops.pmc.domain.AnPmcContractUser;

import java.util.List;

/**
 * 合同用户中间表Mapper接口
 * 
 * @author ananops
 * @date 2020-05-31
 */
public interface AnPmcContractUserMapper extends BaseMapper<AnPmcContractUser>
{
    /**
     * 查询合同用户中间表
     * 
     * @param contractId 合同用户中间表ID
     * @return 合同用户中间表
     */
    public AnPmcContractUser selectAnPmcContractUserById(Long contractId);

    /**
     * 查询合同用户中间表列表
     * 
     * @param anPmcContractUser 合同用户中间表
     * @return 合同用户中间表集合
     */
     List<AnPmcContractUser> selectAnPmcContractUserList(AnPmcContractUser anPmcContractUser);

    /**
     * 新增合同用户中间表
     * 
     * @param anPmcContractUser 合同用户中间表
     * @return 结果
     */
     int insertAnPmcContractUser(AnPmcContractUser anPmcContractUser);

    /**
     * 修改合同用户中间表
     * 
     * @param anPmcContractUser 合同用户中间表
     * @return 结果
     */
    int updateAnPmcContractUser(AnPmcContractUser anPmcContractUser);

    /**
     * 删除合同用户中间表
     * 
     * @param contractId 合同用户中间表ID
     * @return 结果
     */
    int deleteAnPmcContractUserById(Long contractId);

    /**
     * 批量删除合同用户中间表
     * 
     * @param contractIds 需要删除的数据ID
     * @return 结果
     */
    int deleteAnPmcContractUserByIds(String[] contractIds);
}
