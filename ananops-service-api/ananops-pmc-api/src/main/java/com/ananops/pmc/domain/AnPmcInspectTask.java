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
 * 【请填写功能名称】对象 an_pmc_inspect_task
 * 
 * @author ananops
 * @date 2020-05-28
 */
public class AnPmcInspectTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID 主键*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String version;

    /** 项目id */
    @Excel(name = "项目id")
    private Long projectId;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String taskName;

    /** 任务类型 */
    @Excel(name = "任务类型")
    private String taskType;

    /** 巡检内容 */
    @Excel(name = "巡检内容")
    private String inspectionContent;

    /** 巡检情况 */
    @Excel(name = "巡检情况")
    private String inspectionCondition;

    /** 处理结果 */
    @Excel(name = "处理结果")
    private String dealResult;

    /** 巡检周期，单位天 */
    @Excel(name = "巡检周期，单位天")
    private Integer cycleTime;

    /** 预计开始时间 */
    @Excel(name = "预计开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date scheduledStartTime;

    /** 最晚开始时间 */
    @Excel(name = "最晚开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deadlineTime;

    /** 计划完成时间，单位天 */
    @Excel(name = "计划完成时间，单位天")
    private Integer scheduledFinishTime;

    /** 是否立即执行，0-否，1-是 */
    @Excel(name = "是否立即执行，0-否，1-是")
    private Integer isNow;

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
    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
    }
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }
    public void setTaskName(String taskName) 
    {
        this.taskName = taskName;
    }

    public String getTaskName() 
    {
        return taskName;
    }
    public void setTaskType(String taskType) 
    {
        this.taskType = taskType;
    }

    public String getTaskType() 
    {
        return taskType;
    }
    public void setInspectionContent(String inspectionContent) 
    {
        this.inspectionContent = inspectionContent;
    }

    public String getInspectionContent() 
    {
        return inspectionContent;
    }
    public void setInspectionCondition(String inspectionCondition) 
    {
        this.inspectionCondition = inspectionCondition;
    }

    public String getInspectionCondition() 
    {
        return inspectionCondition;
    }
    public void setDealResult(String dealResult) 
    {
        this.dealResult = dealResult;
    }

    public String getDealResult() 
    {
        return dealResult;
    }
    public void setCycleTime(Integer cycleTime) 
    {
        this.cycleTime = cycleTime;
    }

    public Integer getCycleTime() 
    {
        return cycleTime;
    }
    public void setScheduledStartTime(Date scheduledStartTime) 
    {
        this.scheduledStartTime = scheduledStartTime;
    }

    public Date getScheduledStartTime() 
    {
        return scheduledStartTime;
    }
    public void setDeadlineTime(Date deadlineTime) 
    {
        this.deadlineTime = deadlineTime;
    }

    public Date getDeadlineTime() 
    {
        return deadlineTime;
    }
    public void setScheduledFinishTime(Integer scheduledFinishTime) 
    {
        this.scheduledFinishTime = scheduledFinishTime;
    }

    public Integer getScheduledFinishTime() 
    {
        return scheduledFinishTime;
    }
    public void setIsNow(Integer isNow) 
    {
        this.isNow = isNow;
    }

    public Integer getIsNow() 
    {
        return isNow;
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
            .append("projectId", getProjectId())
            .append("projectName", getProjectName())
            .append("taskName", getTaskName())
            .append("taskType", getTaskType())
            .append("inspectionContent", getInspectionContent())
            .append("inspectionCondition", getInspectionCondition())
            .append("dealResult", getDealResult())
            .append("cycleTime", getCycleTime())
            .append("scheduledStartTime", getScheduledStartTime())
            .append("deadlineTime", getDeadlineTime())
            .append("scheduledFinishTime", getScheduledFinishTime())
            .append("isNow", getIsNow())
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
