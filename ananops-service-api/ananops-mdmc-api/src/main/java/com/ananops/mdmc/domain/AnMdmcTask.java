package com.ananops.mdmc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ananops.common.annotation.Excel;
import com.ananops.common.core.domain.BaseEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 维修工单对象 an_mdmc_task
 *
 * @author ananops
 * @date 2020-05-22
 */
public class AnMdmcTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 版本号 */
    @Excel(name = "版本号")
    private Long version;


    /** 创建人ID */
    @Excel(name = "创建人ID")
    private Long creatorId;


    /** 最近操作人 */
    @Excel(name = "最近操作人")
    private String lastOperator;


    /** 维修任务（绑定）类型 */
    @Excel(name = "维修任务", readConverterExp = "绑=定")
    private Integer objectType;

    /** 维修任务（绑定）对象编号 */
    @Excel(name = "维修任务", readConverterExp = "绑=定")
    private Long objectId;

    /** 发起此次维修请求的用户ID */
    @Excel(name = "发起此次维修请求的用户ID")
    private Long userId;

    /** 用户负责人（领导）ID */
    @Excel(name = "用户负责人", readConverterExp = "领=导")
    private Long principalId;

    /** 任务对应的项目ID */
    @Excel(name = "任务对应的项目ID")
    private Long projectId;

    /** 服务商ID */
    @Excel(name = "服务商ID")
    private Long facilitatorId;

    /** 维修工ID */
    @Excel(name = "维修工ID")
    private Long maintainerId;

    /** 预计完成时间 */
    @Excel(name = "预计完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date scheduledFinishTime;

    /** 实际完成时间 */
    @Excel(name = "实际完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date actualFinishTime;

    /** 预计开始时间 */
    @Excel(name = "预计开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date scheduledStartTime;

    /** 实际开始时间 */
    @Excel(name = "实际开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date actualStartTime;

    /** 最迟完成时间 */
    @Excel(name = "最迟完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deadline;

    /** 维修超时原因 */
    @Excel(name = "维修超时原因")
    private String delayReason;

    /** 请求维修的地点，纬度 */
    @Excel(name = "请求维修的地点，纬度")
    private Double requestLatitude;

    /** 请求维修的地点，经度 */
    @Excel(name = "请求维修的地点，经度")
    private Double requestLongitude;

    /** 当前任务的进度状态 */
    @Excel(name = "当前任务的进度状态")
    private Integer status;

    /** 维修总花费 */
    @Excel(name = "维修总花费")
    private Double totalCost;

    /** 结算方式 */
    @Excel(name = "结算方式")
    private Integer clearingForm;

    /** 维修工单名称 */
    @Excel(name = "维修工单名称")
    private String title;

    /** 合同编号 */
    @Excel(name = "合同编号")
    private Long contractId;

    /** 报修地址 */
    @Excel(name = "报修地址")
    private String addressName;

    /** 报修人电话 */
    @Excel(name = "报修人电话")
    private String creatorCall;

    /** 故障原因 */
    @Excel(name = "故障原因")
    private String troubleReason;

    /** 故障等级 */
    @Excel(name = "故障等级")
    private Integer level;

    /** 预约时间 */
    @Excel(name = "预约时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date appointTime;

    /** 维修建议 */
    @Excel(name = "维修建议")
    private String suggestion;

    /** 维修结果 */
    @Excel(name = "维修结果")
    private String result;

    /** 备注信息 */
    @Excel(name = "备注信息")
    private String note;


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

    public void setObjectType(Integer objectType)
    {
        this.objectType = objectType;
    }

    public Integer getObjectType()
    {
        return objectType;
    }
    public void setObjectId(Long objectId)
    {
        this.objectId = objectId;
    }

    public Long getObjectId()
    {
        return objectId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setPrincipalId(Long principalId)
    {
        this.principalId = principalId;
    }

    public Long getPrincipalId()
    {
        return principalId;
    }
    public void setProjectId(Long projectId)
    {
        this.projectId = projectId;
    }

    public Long getProjectId()
    {
        return projectId;
    }
    public void setFacilitatorId(Long facilitatorId)
    {
        this.facilitatorId = facilitatorId;
    }

    public Long getFacilitatorId()
    {
        return facilitatorId;
    }
    public void setMaintainerId(Long maintainerId)
    {
        this.maintainerId = maintainerId;
    }

    public Long getMaintainerId()
    {
        return maintainerId;
    }
    public void setScheduledFinishTime(Date scheduledFinishTime)
    {
        this.scheduledFinishTime = scheduledFinishTime;
    }

    public Date getScheduledFinishTime()
    {
        return scheduledFinishTime;
    }
    public void setActualFinishTime(Date actualFinishTime)
    {
        this.actualFinishTime = actualFinishTime;
    }

    public Date getActualFinishTime()
    {
        return actualFinishTime;
    }
    public void setScheduledStartTime(Date scheduledStartTime)
    {
        this.scheduledStartTime = scheduledStartTime;
    }

    public Date getScheduledStartTime()
    {
        return scheduledStartTime;
    }
    public void setActualStartTime(Date actualStartTime)
    {
        this.actualStartTime = actualStartTime;
    }

    public Date getActualStartTime()
    {
        return actualStartTime;
    }
    public void setDeadline(Date deadline)
    {
        this.deadline = deadline;
    }

    public Date getDeadline()
    {
        return deadline;
    }
    public void setDelayReason(String delayReason)
    {
        this.delayReason = delayReason;
    }

    public String getDelayReason()
    {
        return delayReason;
    }
    public void setRequestLatitude(Double requestLatitude)
    {
        this.requestLatitude = requestLatitude;
    }

    public Double getRequestLatitude()
    {
        return requestLatitude;
    }
    public void setRequestLongitude(Double requestLongitude)
    {
        this.requestLongitude = requestLongitude;
    }

    public Double getRequestLongitude()
    {
        return requestLongitude;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }
    public void setTotalCost(Double totalCost)
    {
        this.totalCost = totalCost;
    }

    public Double getTotalCost()
    {
        return totalCost;
    }
    public void setClearingForm(Integer clearingForm)
    {
        this.clearingForm = clearingForm;
    }

    public Integer getClearingForm()
    {
        return clearingForm;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setContractId(Long contractId)
    {
        this.contractId = contractId;
    }

    public Long getContractId()
    {
        return contractId;
    }
    public void setAddressName(String addressName)
    {
        this.addressName = addressName;
    }

    public String getAddressName()
    {
        return addressName;
    }
    public void setCreatorCall(String creatorCall)
    {
        this.creatorCall = creatorCall;
    }

    public String getCreatorCall()
    {
        return creatorCall;
    }
    public void setTroubleReason(String troubleReason)
    {
        this.troubleReason = troubleReason;
    }

    public String getTroubleReason()
    {
        return troubleReason;
    }
    public void setLevel(Integer level)
    {
        this.level = level;
    }

    public Integer getLevel()
    {
        return level;
    }
    public void setAppointTime(Date appointTime)
    {
        this.appointTime = appointTime;
    }

    public Date getAppointTime()
    {
        return appointTime;
    }
    public void setSuggestion(String suggestion)
    {
        this.suggestion = suggestion;
    }

    public String getSuggestion()
    {
        return suggestion;
    }
    public void setResult(String result)
    {
        this.result = result;
    }

    public String getResult()
    {
        return result;
    }
    public void setNote(String note)
    {
        this.note = note;
    }

    public String getNote()
    {
        return note;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("version", getVersion())
                .append("creatorId", getCreatorId())
                .append("lastOperator", getLastOperator())
                .append("updateTime", getUpdateTime())
                .append("objectType", getObjectType())
                .append("objectId", getObjectId())
                .append("userId", getUserId())
                .append("principalId", getPrincipalId())
                .append("projectId", getProjectId())
                .append("facilitatorId", getFacilitatorId())
                .append("maintainerId", getMaintainerId())
                .append("scheduledFinishTime", getScheduledFinishTime())
                .append("actualFinishTime", getActualFinishTime())
                .append("scheduledStartTime", getScheduledStartTime())
                .append("actualStartTime", getActualStartTime())
                .append("deadline", getDeadline())
                .append("delayReason", getDelayReason())
                .append("requestLatitude", getRequestLatitude())
                .append("requestLongitude", getRequestLongitude())
                .append("status", getStatus())
                .append("totalCost", getTotalCost())
                .append("clearingForm", getClearingForm())
                .append("title", getTitle())
                .append("contractId", getContractId())
                .append("addressName", getAddressName())
                .append("creatorCall", getCreatorCall())
                .append("troubleReason", getTroubleReason())
                .append("level", getLevel())
                .append("appointTime", getAppointTime())
                .append("suggestion", getSuggestion())
                .append("result", getResult())
                .append("note", getNote())
                .toString();
    }
}
