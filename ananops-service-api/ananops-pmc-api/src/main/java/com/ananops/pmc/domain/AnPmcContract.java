package com.ananops.pmc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ananops.common.annotation.Excel;
import com.ananops.common.core.domain.BaseEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 合同对象 an_pmc_contract
 * 
 * @author ananops
 * @date 2020-05-28
 */
public class AnPmcContract extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** Id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 版本号 */
    @Excel(name = "版本号")
    private String version;

    /** 合同编号 */
    @Excel(name = "合同编号")
    private String contractCode;

    /** 合同名称 */
    @Excel(name = "合同名称")
    private String contractName;

    /** 合同类型 */
    @Excel(name = "合同类型")
    private String contractType;

    /** 甲方id */
    @Excel(name = "甲方id")
    private Long partyAId;

    /** 甲方组织名称 */
    @Excel(name = "甲方组织名称")
    private String partyAName;

    /** 甲方合同签字法人 */
    @Excel(name = "甲方合同签字法人")
    private String aLegalName;

    /** 乙方id */
    @Excel(name = "乙方id")
    private Long partyBId;

    /** 乙方组织名称 */
    @Excel(name = "乙方组织名称")
    private String partyBName;

    /** 乙方合同签字法人 */
    @Excel(name = "乙方合同签字法人")
    private String bLegalName;

    /** 乙方开户银行 */
    @Excel(name = "乙方开户银行")
    private String bankName;

    /** 乙方银行账号 */
    @Excel(name = "乙方银行账号")
    private String bankAccount;

    /** 合同签订时间 */
    @Excel(name = "合同签订时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date signTime;

    /** 合同开始时间 */
    @Excel(name = "合同开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 合同结束时间 */
    @Excel(name = "合同结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endedTime;

    /** 是否自动顺延（0-未顺延，1-顺延） */
    @Excel(name = "是否自动顺延", readConverterExp = "0=-未顺延，1-顺延")
    private Integer isPostpone;

    /** 支付方式（1-现结、2-账期、3-年结） */
    @Excel(name = "支付方式", readConverterExp = "1=-现结、2-账期、3-年结")
    private Integer paymentType;

    /** 支付金额 */
    @Excel(name = "支付金额")
    private Double paymentMoney;

    /** 项目金额 */
    @Excel(name = "项目金额")
    private Double projectMoney;

    /** 付款时间 */
    @Excel(name = "付款时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date paymentTime;

    /** 维修设备数量 */
    @Excel(name = "维修设备数量")
    private Long deviceCount;

    /** 乙方代理内容 */
    @Excel(name = "乙方代理内容")
    private String agentContent;

    /** 乙方是否包备品备件（0-不包，1-包） */
    @Excel(name = "乙方是否包备品备件", readConverterExp = "0=-不包，1-包")
    private Integer isSparePart;

    /** 乙方是否提供备品备件替换服务（0-不提供，1-提供) */
    @Excel(name = "乙方是否提供备品备件替换服务", readConverterExp = "乙方是否提供备品备件替换服务（0-不提供，1-提供)")
    private Long isSpareService;

    /** 乙供辅料金额（乙方会提供一些免费辅件，超过该金额的才会另收费） */
    @Excel(name = "乙供辅料金额", readConverterExp = "乙=方会提供一些免费辅件，超过该金额的才会另收费")
    private Double assitMoney;

    /** 维修维护最迟响应时间,单位小时（配合转单功能以及平台的提醒功能，在一定时限内短信、电话或邮件提醒） */
    @Excel(name = "维修维护最迟响应时间,单位小时", readConverterExp = "配=合转单功能以及平台的提醒功能，在一定时限内短信、电话或邮件提醒")
    private Integer lastResponseTime;

    /** 维修工身份验证流程（对于有保密属性的设备需要验证维修工的身份） */
    @Excel(name = "维修工身份验证流程", readConverterExp = "对=于有保密属性的设备需要验证维修工的身份")
    private String verification;

    /** 月度记录表提交周期，单位天（也可以为每月第一天） */
    @Excel(name = "月度记录表提交周期，单位天", readConverterExp = "也=可以为每月第一天")
    private Integer recordTime;

    /** 合同是否变更（0-未变更，1-变更） */
    @Excel(name = "合同是否变更", readConverterExp = "0=-未变更，1-变更")
    private Integer isChange;

    /** 合同是否作废（0-有效，1-作废） */
    @Excel(name = "合同是否作废", readConverterExp = "0=-有效，1-作废")
    private Integer isDestroy;

    /** 附件路径 */
    @Excel(name = "附件路径")
    private String filePath;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 创建者id */
    @Excel(name = "创建者id")
    private Long creatorId;

    /** 最近操作人 */
    @Excel(name = "最近操作人")
    private String lastOperator;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setVersion(String version) 
    {
        this.version = version;
    }

    public String getVersion() 
    {
        return version;
    }
    public void setContractCode(String contractCode) 
    {
        this.contractCode = contractCode;
    }

    public String getContractCode() 
    {
        return contractCode;
    }
    public void setContractName(String contractName) 
    {
        this.contractName = contractName;
    }

    public String getContractName() 
    {
        return contractName;
    }
    public void setContractType(String contractType) 
    {
        this.contractType = contractType;
    }

    public String getContractType() 
    {
        return contractType;
    }
    public void setPartyAId(Long partyAId) 
    {
        this.partyAId = partyAId;
    }

    public Long getPartyAId() 
    {
        return partyAId;
    }
    public void setPartyAName(String partyAName) 
    {
        this.partyAName = partyAName;
    }

    public String getPartyAName() 
    {
        return partyAName;
    }
    public void setALegalName(String aLegalName) 
    {
        this.aLegalName = aLegalName;
    }

    public String getALegalName() 
    {
        return aLegalName;
    }
    public void setPartyBId(Long partyBId) 
    {
        this.partyBId = partyBId;
    }

    public Long getPartyBId() 
    {
        return partyBId;
    }
    public void setPartyBName(String partyBName) 
    {
        this.partyBName = partyBName;
    }

    public String getPartyBName() 
    {
        return partyBName;
    }
    public void setBLegalName(String bLegalName) 
    {
        this.bLegalName = bLegalName;
    }

    public String getBLegalName() 
    {
        return bLegalName;
    }
    public void setBankName(String bankName) 
    {
        this.bankName = bankName;
    }

    public String getBankName() 
    {
        return bankName;
    }
    public void setBankAccount(String bankAccount) 
    {
        this.bankAccount = bankAccount;
    }

    public String getBankAccount() 
    {
        return bankAccount;
    }
    public void setSignTime(Date signTime) 
    {
        this.signTime = signTime;
    }

    public Date getSignTime() 
    {
        return signTime;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndedTime(Date endedTime)
    {
        this.endedTime = endedTime;
    }

    public Date getEndedTime()
    {
        return endedTime;
    }
    public void setIsPostpone(Integer isPostpone) 
    {
        this.isPostpone = isPostpone;
    }

    public Integer getIsPostpone() 
    {
        return isPostpone;
    }
    public void setPaymentType(Integer paymentType) 
    {
        this.paymentType = paymentType;
    }

    public Integer getPaymentType() 
    {
        return paymentType;
    }
    public void setPaymentMoney(Double paymentMoney) 
    {
        this.paymentMoney = paymentMoney;
    }

    public Double getPaymentMoney() 
    {
        return paymentMoney;
    }
    public void setProjectMoney(Double projectMoney) 
    {
        this.projectMoney = projectMoney;
    }

    public Double getProjectMoney() 
    {
        return projectMoney;
    }
    public void setPaymentTime(Date paymentTime) 
    {
        this.paymentTime = paymentTime;
    }

    public Date getPaymentTime() 
    {
        return paymentTime;
    }
    public void setDeviceCount(Long deviceCount) 
    {
        this.deviceCount = deviceCount;
    }

    public Long getDeviceCount() 
    {
        return deviceCount;
    }
    public void setAgentContent(String agentContent) 
    {
        this.agentContent = agentContent;
    }

    public String getAgentContent() 
    {
        return agentContent;
    }
    public void setIsSparePart(Integer isSparePart) 
    {
        this.isSparePart = isSparePart;
    }

    public Integer getIsSparePart() 
    {
        return isSparePart;
    }
    public void setIsSpareService(Long isSpareService) 
    {
        this.isSpareService = isSpareService;
    }

    public Long getIsSpareService() 
    {
        return isSpareService;
    }
    public void setAssitMoney(Double assitMoney) 
    {
        this.assitMoney = assitMoney;
    }

    public Double getAssitMoney() 
    {
        return assitMoney;
    }
    public void setLastResponseTime(Integer lastResponseTime) 
    {
        this.lastResponseTime = lastResponseTime;
    }

    public Integer getLastResponseTime() 
    {
        return lastResponseTime;
    }
    public void setVerification(String verification) 
    {
        this.verification = verification;
    }

    public String getVerification() 
    {
        return verification;
    }
    public void setRecordTime(Integer recordTime) 
    {
        this.recordTime = recordTime;
    }

    public Integer getRecordTime() 
    {
        return recordTime;
    }
    public void setIsChange(Integer isChange) 
    {
        this.isChange = isChange;
    }

    public Integer getIsChange() 
    {
        return isChange;
    }
    public void setIsDestroy(Integer isDestory)
    {
        this.isDestroy = isDestory;
    }

    public Integer getIsDestroy()
    {
        return isDestroy;
    }
    public void setFilePath(String filePath) 
    {
        this.filePath = filePath;
    }

    public String getFilePath() 
    {
        return filePath;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setCreatorId(Long creatorId) 
    {
        this.creatorId = creatorId;
    }

    public Long getCreatorId() 
    {
        return creatorId;
    }
    public void setLastOperator(String lastOperator) 
    {
        this.lastOperator = lastOperator;
    }

    public String getLastOperator() 
    {
        return lastOperator;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("version", getVersion())
            .append("contractCode", getContractCode())
            .append("contractName", getContractName())
            .append("contractType", getContractType())
            .append("partyAId", getPartyAId())
            .append("partyAName", getPartyAName())
            .append("aLegalName", getALegalName())
            .append("partyBId", getPartyBId())
            .append("partyBName", getPartyBName())
            .append("bLegalName", getBLegalName())
            .append("bankName", getBankName())
            .append("bankAccount", getBankAccount())
            .append("signTime", getSignTime())
            .append("startTime", getStartTime())
            .append("endedTime", getEndedTime())
            .append("isPostpone", getIsPostpone())
            .append("paymentType", getPaymentType())
            .append("paymentMoney", getPaymentMoney())
            .append("projectMoney", getProjectMoney())
            .append("paymentTime", getPaymentTime())
            .append("deviceCount", getDeviceCount())
            .append("agentContent", getAgentContent())
            .append("isSparePart", getIsSparePart())
            .append("isSpareService", getIsSpareService())
            .append("assitMoney", getAssitMoney())
            .append("lastResponseTime", getLastResponseTime())
            .append("verification", getVerification())
            .append("recordTime", getRecordTime())
            .append("isChange", getIsChange())
            .append("isDestory", getIsDestroy())
            .append("filePath", getFilePath())
            .append("description", getDescription())
            .append("creatorId", getCreatorId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("lastOperator", getLastOperator())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
