package com.ananops.imc.domain;

import com.ananops.common.annotation.Excel;
import com.ananops.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 巡检任务对象 an_imc_inspection_task
 *
 * @author rongshuai
 * @date 2020-05-18
 */
public class AnImcInspectionTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 版本号 */
    @Excel(name = "版本号")
    private Long version;

    /** 创建人 */
    @Excel(name = "创建人")
    private String creator;

    /** 创建人ID */
    @Excel(name = "创建人ID")
    private Long creatorId;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 最近操作人 */
    @Excel(name = "最近操作人")
    private String lastOperator;

    /** 最后操作人ID */
    @Excel(name = "最后操作人ID")
    private Long lastOperatorId;

    /** 项目负责人ID */
    @Excel(name = "项目负责人ID")
    private Long principalId;

    /** 服务商ID */
    @Excel(name = "服务商ID")
    private Long facilitatorId;

    /** 项目ID */
    @Excel(name = "项目ID")
    private Long projectId;

    /** 巡检位置信息 */
    @Excel(name = "巡检位置信息")
    private String location;

    /** 当前巡检任务进度 */
    @Excel(name = "当前巡检任务进度")
    private Integer status;

    /** 本次巡检总花费 */
    @Excel(name = "本次巡检总花费")
    private Double totalCost;

    /** 巡检产生的维修维护费用 */
    @Excel(name = "巡检产生的维修维护费用")
    private Double maintenanceCost;

    /** 计划起始时间 */
    @Excel(name = "计划起始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date scheduledStartTime;

    /** 实际完成时间 */
    @Excel(name = "实际完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date actualFinishTime;

    /** 计划完成天数 */
    @Excel(name = "计划完成天数")
    private Long days;

    /** 巡检类型（1.按合同产生的巡检，2.甲方负责人主动发出的巡检） */
    @Excel(name = "巡检类型", readConverterExp = "1=.按合同产生的巡检，2.甲方负责人主动发出的巡检")
    private Integer inspectionType;

    /** 巡检任务名称 */
    @Excel(name = "巡检任务名称")
    private String taskName;

    /** 巡检周期（月） */
    @Excel(name = "巡检周期", readConverterExp = "月=")
    private Long frequency;

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
    public void setCreator(String creator)
    {
        this.creator = creator;
    }

    public String getCreator()
    {
        return creator;
    }
    public void setCreatorId(Long creatorId)
    {
        this.creatorId = creatorId;
    }

    public Long getCreatorId()
    {
        return creatorId;
    }
    public void setCreatedTime(Date createdTime)
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }
    public void setLastOperator(String lastOperator)
    {
        this.lastOperator = lastOperator;
    }

    public String getLastOperator()
    {
        return lastOperator;
    }
    public void setLastOperatorId(Long lastOperatorId)
    {
        this.lastOperatorId = lastOperatorId;
    }

    public Long getLastOperatorId()
    {
        return lastOperatorId;
    }
    public void setPrincipalId(Long principalId)
    {
        this.principalId = principalId;
    }

    public Long getPrincipalId()
    {
        return principalId;
    }
    public void setFacilitatorId(Long facilitatorId)
    {
        this.facilitatorId = facilitatorId;
    }

    public Long getFacilitatorId()
    {
        return facilitatorId;
    }
    public void setProjectId(Long projectId)
    {
        this.projectId = projectId;
    }

    public Long getProjectId()
    {
        return projectId;
    }
    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getLocation()
    {
        return location;
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
    public void setMaintenanceCost(Double maintenanceCost)
    {
        this.maintenanceCost = maintenanceCost;
    }

    public Double getMaintenanceCost()
    {
        return maintenanceCost;
    }
    public void setScheduledStartTime(Date scheduledStartTime)
    {
        this.scheduledStartTime = scheduledStartTime;
    }

    public Date getScheduledStartTime()
    {
        return scheduledStartTime;
    }
    public void setActualFinishTime(Date actualFinishTime)
    {
        this.actualFinishTime = actualFinishTime;
    }

    public Date getActualFinishTime()
    {
        return actualFinishTime;
    }
    public void setDays(Long days)
    {
        this.days = days;
    }

    public Long getDays()
    {
        return days;
    }
    public void setInspectionType(Integer inspectionType)
    {
        this.inspectionType = inspectionType;
    }

    public Integer getInspectionType()
    {
        return inspectionType;
    }
    public void setTaskName(String taskName)
    {
        this.taskName = taskName;
    }

    public String getTaskName()
    {
        return taskName;
    }
    public void setFrequency(Long frequency)
    {
        this.frequency = frequency;
    }

    public Long getFrequency()
    {
        return frequency;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("version", getVersion())
                .append("creator", getCreator())
                .append("creatorId", getCreatorId())
                .append("createdTime", getCreatedTime())
                .append("lastOperator", getLastOperator())
                .append("lastOperatorId", getLastOperatorId())
                .append("updateTime", getUpdateTime())
                .append("principalId", getPrincipalId())
                .append("facilitatorId", getFacilitatorId())
                .append("projectId", getProjectId())
                .append("location", getLocation())
                .append("status", getStatus())
                .append("totalCost", getTotalCost())
                .append("maintenanceCost", getMaintenanceCost())
                .append("scheduledStartTime", getScheduledStartTime())
                .append("actualFinishTime", getActualFinishTime())
                .append("days", getDays())
                .append("inspectionType", getInspectionType())
                .append("remark", getRemark())
                .append("taskName", getTaskName())
                .append("frequency", getFrequency())
                .toString();
    }
}