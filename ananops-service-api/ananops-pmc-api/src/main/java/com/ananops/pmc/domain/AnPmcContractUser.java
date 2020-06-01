package com.ananops.pmc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ananops.common.annotation.Excel;
import com.ananops.common.core.domain.BaseEntity;

/**
 * 合同用户中间表对象 an_pmc_contract_user
 * 
 * @author ananops
 * @date 2020-05-31
 */
public class AnPmcContractUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 合同id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractId;

    /** 用户id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    public void setContractId(Long contractId) 
    {
        this.contractId = contractId;
    }

    public Long getContractId() 
    {
        return contractId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("contractId", getContractId())
            .append("userId", getUserId())
            .toString();
    }
}
