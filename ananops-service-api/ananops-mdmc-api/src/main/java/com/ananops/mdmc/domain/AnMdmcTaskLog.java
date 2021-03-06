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
 * 维修工单日志对象 an_mdmc_task_log
 *
 * @author ananops
 * @date 2020-05-25
 */
public class AnMdmcTaskLog extends BaseEntity
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


    /** 任务ID */
    @Excel(name = "任务ID")
    private Long taskId;

    /** 当前操作对应的状态 */
    @Excel(name = "当前操作对应的状态")
    private Integer status;

    /** 当前操作的描述 */
    @Excel(name = "当前操作的描述")
    private String movement;

    /** 当前操作对应的时间戳 */
    @Excel(name = "当前操作对应的时间戳", width = 30, dateFormat = "yyyy-MM-dd")
    private Date statusTimestamp;


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

    public void setTaskId(Long taskId)
    {
        this.taskId = taskId;
    }

    public Long getTaskId()
    {
        return taskId;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }
    public void setMovement(String movement)
    {
        this.movement = movement;
    }

    public String getMovement()
    {
        return movement;
    }
    public void setStatusTimestamp(Date statusTimestamp)
    {
        this.statusTimestamp = statusTimestamp;
    }

    public Date getStatusTimestamp()
    {
        return statusTimestamp;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("version", getVersion())
                .append("creatorId", getCreatorId())
                .append("lastOperator", getLastOperator())
                .append("updateTime", getUpdateTime())
                .append("taskId", getTaskId())
                .append("status", getStatus())
                .append("movement", getMovement())
                .append("statusTimestamp", getStatusTimestamp())
                .toString();
    }
}
