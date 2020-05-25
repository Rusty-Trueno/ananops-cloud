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
 * 评价工单对象 an_mdmc_review
 *
 * @author ananops
 * @date 2020-05-25
 */
public class AnMdmcReview extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    /** 任务ID */
    @Excel(name = "任务ID")
    private Long taskId;

    /** 服务商ID */
    @Excel(name = "服务商ID")
    private Long facilitatorId;

    /** 维修工程师ID */
    @Excel(name = "维修工程师ID")
    private Long maintainerId;

    /** 申请维修维护的用户的ID */
    @Excel(name = "申请维修维护的用户的ID")
    private Long userId;

    /** 服务评级 */
    @Excel(name = "服务评级")
    private Integer score;

    /** 服务评论 */
    @Excel(name = "服务评论")
    private String contents;

    /** $column.columnComment */
    @Excel(name = "服务评论")
    private Long principalId;

    /** 验收内容 */
    @Excel(name = "验收内容")
    private String checkContents;

    @Excel(name = "备注")
    private String remark;

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
    public void setTaskId(Long taskId)
    {
        this.taskId = taskId;
    }

    public Long getTaskId()
    {
        return taskId;
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
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setScore(Integer score)
    {
        this.score = score;
    }

    public Integer getScore()
    {
        return score;
    }
    public void setContents(String contents)
    {
        this.contents = contents;
    }

    public String getContents()
    {
        return contents;
    }
    public void setPrincipalId(Long principalId)
    {
        this.principalId = principalId;
    }

    public Long getPrincipalId()
    {
        return principalId;
    }
    public void setCheckContents(String checkContents)
    {
        this.checkContents = checkContents;
    }

    public String getCheckContents()
    {
        return checkContents;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("version", getVersion())
                .append("creator", getCreator())
                .append("creatorId", getCreatorId())
                .append("createdTime", getCreatedTime())
                .append("lastOperator", getLastOperator())
                .append("lastOperatorId", getLastOperatorId())
                .append("updateTime", getUpdateTime())
                .append("taskId", getTaskId())
                .append("facilitatorId", getFacilitatorId())
                .append("maintainerId", getMaintainerId())
                .append("userId", getUserId())
                .append("score", getScore())
                .append("contents", getContents())
                .append("principalId", getPrincipalId())
                .append("checkContents", getCheckContents())
                .toString();
    }
}
