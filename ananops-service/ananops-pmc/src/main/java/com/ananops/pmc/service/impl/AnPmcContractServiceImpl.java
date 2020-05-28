package com.ananops.pmc.service.impl;

import java.util.List;
import com.ananops.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ananops.pmc.mapper.AnPmcContractMapper;
import com.ananops.pmc.domain.AnPmcContract;
import com.ananops.pmc.service.IAnPmcContractService;
import com.ananops.common.core.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ananops
 * @date 2020-05-28
 */
@Service
public class AnPmcContractServiceImpl implements IAnPmcContractService 
{
    @Autowired
    private AnPmcContractMapper anPmcContractMapper;

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
     * @param anPmcContract 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertAnPmcContract(AnPmcContract anPmcContract)
    {
        anPmcContract.setCreateTime(DateUtils.getNowDate());
        return anPmcContractMapper.insert(anPmcContract);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param anPmcContract 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateAnPmcContract(AnPmcContract anPmcContract)
    {
        anPmcContract.setUpdateTime(DateUtils.getNowDate());
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
}
