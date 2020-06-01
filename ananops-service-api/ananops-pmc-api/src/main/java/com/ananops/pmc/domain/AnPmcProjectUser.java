package com.ananops.pmc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ananops.common.annotation.Excel;
import com.ananops.common.core.domain.BaseEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 项目用户中间表对象 an_pmc_project_user
 * 
 * @author ananops
 * @date 2020-05-31
 */
public class AnPmcProjectUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    /** 用户id */
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
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
            .append("projectId", getProjectId())
            .append("userId", getUserId())
            .toString();
    }
}
