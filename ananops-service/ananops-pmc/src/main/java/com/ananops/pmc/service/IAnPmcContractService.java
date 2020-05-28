package com.ananops.pmc.service;

import com.ananops.pmc.domain.AnPmcContract;
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
     * @param anPmcContract 【请填写功能名称】
     * @return 结果
     */
     int insertAnPmcContract(AnPmcContract anPmcContract);

    /**
     * 修改【请填写功能名称】
     * 
     * @param anPmcContract 【请填写功能名称】
     * @return 结果
     */
     int updateAnPmcContract(AnPmcContract anPmcContract);

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
}
