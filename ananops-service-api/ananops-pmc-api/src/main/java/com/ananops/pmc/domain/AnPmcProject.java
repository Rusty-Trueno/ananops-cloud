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
 * 【请填写功能名称】对象 an_pmc_project
 * 
 * @author ananops
 * @date 2020-05-28
 */
public class AnPmcProject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID 主键*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long version;

    /** 合同id */
    @Excel(name = "合同id")
    private Long contractId;

    /** 合同名称 */
    @Excel(name = "合同名称")
    private String contractName;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 项目类型 */
    @Excel(name = "项目类型")
    private String projectType;

    /** 是否签署合同（0-没有，1-有，没有签署合同的未虚拟项目） */
    @Excel(name = "是否签署合同", readConverterExp = "0=-没有，1-有，没有签署合同的未虚拟项目")
    private Integer isContract;

    /** 甲方id */
    @Excel(name = "甲方id")
    private Long partyAId;

    /** 甲方名称 */
    @Excel(name = "甲方名称")
    private String partyAName;

    /** 乙方id */
    @Excel(name = "乙方id")
    private Long partyBId;

    /** 乙方名称 */
    @Excel(name = "乙方名称")
    private String partyBName;

    /** 甲方项目负责人id */
    @Excel(name = "甲方项目负责人id")
    private Long aLeaderId;

    /** 甲方项目负责人姓名 */
    @Excel(name = "甲方项目负责人姓名")
    private String aLeaderName;

    /** 甲方项目负责人电话 */
    @Excel(name = "甲方项目负责人电话")
    private String aLeaderTel;

    /** 甲方联系人1姓名 */
    @Excel(name = "甲方联系人1姓名")
    private String aOneName;

    /** 甲方项目负责人联系方式1 */
    @Excel(name = "甲方项目负责人联系方式1")
    private String partyAOne;

    /** 甲方联系人2姓名 */
    @Excel(name = "甲方联系人2姓名")
    private String aTwoName;

    /** 甲方项目负责人联系方式2 */
    @Excel(name = "甲方项目负责人联系方式2")
    private String partyATwo;

    /** 甲方联系人3姓名 */
    @Excel(name = "甲方联系人3姓名")
    private String aThreeName;

    /** 甲方项目负责人联系方式2 */
    @Excel(name = "甲方项目负责人联系方式2")
    private String partyAThree;

    /** 乙方项目负责人ID */
    @Excel(name = "乙方项目负责人ID")
    private Long bLeaderId;

    /** 乙方项目负责人姓名 */
    @Excel(name = "乙方项目负责人姓名")
    private String bLeaderName;

    /** 乙方项目负责人电话 */
    @Excel(name = "乙方项目负责人电话")
    private String bLeaderTel;

    /** 乙方24小时值班电话 */
    @Excel(name = "乙方24小时值班电话")
    private String partyBTel;

    /** 乙方24小时开通的移动电话 */
    @Excel(name = "乙方24小时开通的移动电话")
    private String partyBPhone;

    /** 乙方24小时开通邮箱 */
    @Excel(name = "乙方24小时开通邮箱")
    private String partyBEmail;

    /** 开始时间（虚拟项目必填） */
    @Excel(name = "开始时间", readConverterExp = "虚=拟项目必填")
    private Date startTime;

    /** 结束时间（虚拟项目必填 */
    @Excel(name = "结束时间", readConverterExp = "结束时间（虚拟项目必填")
    private Date endedTime;

    /** 项目是否作废（0-有效，1-作废） */
    @Excel(name = "项目是否作废", readConverterExp = "0=-有效，1-作废")
    private Integer isDestroy;

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
    public void setVersion(Long version) 
    {
        this.version = version;
    }

    public Long getVersion() 
    {
        return version;
    }
    public void setContractId(Long contractId) 
    {
        this.contractId = contractId;
    }

    public Long getContractId() 
    {
        return contractId;
    }
    public void setContractName(String contractName) 
    {
        this.contractName = contractName;
    }

    public String getContractName() 
    {
        return contractName;
    }
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }
    public void setProjectType(String projectType) 
    {
        this.projectType = projectType;
    }

    public String getProjectType() 
    {
        return projectType;
    }
    public void setIsContract(Integer isContract) 
    {
        this.isContract = isContract;
    }

    public Integer getIsContract() 
    {
        return isContract;
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
    public void setALeaderId(Long aLeaderId) 
    {
        this.aLeaderId = aLeaderId;
    }

    public Long getALeaderId() 
    {
        return aLeaderId;
    }
    public void setALeaderName(String aLeaderName) 
    {
        this.aLeaderName = aLeaderName;
    }

    public String getALeaderName() 
    {
        return aLeaderName;
    }
    public void setALeaderTel(String aLeaderTel) 
    {
        this.aLeaderTel = aLeaderTel;
    }

    public String getALeaderTel() 
    {
        return aLeaderTel;
    }
    public void setAOneName(String aOneName) 
    {
        this.aOneName = aOneName;
    }

    public String getAOneName() 
    {
        return aOneName;
    }
    public void setPartyAOne(String partyAOne) 
    {
        this.partyAOne = partyAOne;
    }

    public String getPartyAOne() 
    {
        return partyAOne;
    }
    public void setATwoName(String aTwoName) 
    {
        this.aTwoName = aTwoName;
    }

    public String getATwoName() 
    {
        return aTwoName;
    }
    public void setPartyATwo(String partyATwo) 
    {
        this.partyATwo = partyATwo;
    }

    public String getPartyATwo() 
    {
        return partyATwo;
    }
    public void setAThreeName(String aThreeName) 
    {
        this.aThreeName = aThreeName;
    }

    public String getAThreeName() 
    {
        return aThreeName;
    }
    public void setPartyAThree(String partyAThree) 
    {
        this.partyAThree = partyAThree;
    }

    public String getPartyAThree() 
    {
        return partyAThree;
    }
    public void setBLeaderId(Long bLeaderId) 
    {
        this.bLeaderId = bLeaderId;
    }

    public Long getBLeaderId() 
    {
        return bLeaderId;
    }
    public void setBLeaderName(String bLeaderName) 
    {
        this.bLeaderName = bLeaderName;
    }

    public String getBLeaderName() 
    {
        return bLeaderName;
    }
    public void setBLeaderTel(String bLeaderTel) 
    {
        this.bLeaderTel = bLeaderTel;
    }

    public String getBLeaderTel() 
    {
        return bLeaderTel;
    }
    public void setPartyBTel(String partyBTel) 
    {
        this.partyBTel = partyBTel;
    }

    public String getPartyBTel() 
    {
        return partyBTel;
    }
    public void setPartyBPhone(String partyBPhone) 
    {
        this.partyBPhone = partyBPhone;
    }

    public String getPartyBPhone() 
    {
        return partyBPhone;
    }
    public void setPartyBEmail(String partyBEmail) 
    {
        this.partyBEmail = partyBEmail;
    }

    public String getPartyBEmail() 
    {
        return partyBEmail;
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
    public void setIsDestroy(Integer isDestroy) 
    {
        this.isDestroy = isDestroy;
    }

    public Integer getIsDestroy() 
    {
        return isDestroy;
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
            .append("contractId", getContractId())
            .append("contractName", getContractName())
            .append("projectName", getProjectName())
            .append("projectType", getProjectType())
            .append("isContract", getIsContract())
            .append("partyAId", getPartyAId())
            .append("partyAName", getPartyAName())
            .append("partyBId", getPartyBId())
            .append("partyBName", getPartyBName())
            .append("aLeaderId", getALeaderId())
            .append("aLeaderName", getALeaderName())
            .append("aLeaderTel", getALeaderTel())
            .append("aOneName", getAOneName())
            .append("partyAOne", getPartyAOne())
            .append("aTwoName", getATwoName())
            .append("partyATwo", getPartyATwo())
            .append("aThreeName", getAThreeName())
            .append("partyAThree", getPartyAThree())
            .append("bLeaderId", getBLeaderId())
            .append("bLeaderName", getBLeaderName())
            .append("bLeaderTel", getBLeaderTel())
            .append("partyBTel", getPartyBTel())
            .append("partyBPhone", getPartyBPhone())
            .append("partyBEmail", getPartyBEmail())
            .append("startTime", getStartTime())
            .append("endedTime", getEndedTime())
            .append("isDestroy", getIsDestroy())
            .append("description", getDescription())
            .append("createBy", getCreateBy())
            .append("creatorId", getCreatorId())
            .append("createTime", getCreateTime())
            .append("lastOperator", getLastOperator())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
