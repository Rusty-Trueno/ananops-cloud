package com.ananops.pmc.mapper;

import com.ananops.common.core.dao.BaseMapper;
import com.ananops.pmc.domain.AnPmcContract;
import java.util.List;

/**
 * 合同管理 Mapper接口
 * 
 * @author ananops
 * @date 2020-05-28
 */
public interface AnPmcContractMapper extends BaseMapper<AnPmcContract>
{
    /**
     * 查询合同
     * 
     * @param id 合同ID
     * @return 合同
     */
    public AnPmcContract selectAnPmcContractById(Long id);

    /**
     * 查询【合同】列表
     * 
     * @param anPmcContract 合同
     * @return 合同集合
     */
     List<AnPmcContract> selectAnPmcContractList(AnPmcContract anPmcContract);

    /**
     * 新增【合同】
     * 
     * @param anPmcContract 合同
     * @return 结果
     */
     int insertAnPmcContract(AnPmcContract anPmcContract);

    /**
     * 修改【合同】
     * 
     * @param anPmcContract 合同
     * @return 结果
     */
    int updateAnPmcContract(AnPmcContract anPmcContract);

    /**
     * 删除合同
     * 
     * @param id 合同ID
     * @return 结果
     */
    int deleteAnPmcContractById(Long id);

    /**
     * 批量删除合同
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteAnPmcContractByIds(String[] ids);
}
