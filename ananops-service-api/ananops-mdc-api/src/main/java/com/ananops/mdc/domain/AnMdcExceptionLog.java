package com.ananops.mdc.domain;

import com.ananops.common.annotation.Excel;
import com.ananops.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 全局异常记录对象 an_mdc_exception_log
 * 
 * @author ananops
 * @date 2020-06-04
 */
public class AnMdcExceptionLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 系统应用名 */
    @Excel(name = "系统应用名")
    private String applicationName;

    /** 异常类型 */
    @Excel(name = "异常类型")
    private String exceptionSimpleName;

    /** 异常信息(通过exception.getMessage()获取到的内容) */
    @Excel(name = "异常信息(通过exception.getMessage()获取到的内容)")
    private String exceptionMessage;

    /** 异常原因(通过exception.getCause()获取到的内容) */
    @Excel(name = "异常原因(通过exception.getCause()获取到的内容)")
    private String exceptionCause;

    /** 异常堆栈信息 */
    @Excel(name = "异常堆栈信息")
    private String exceptionStack;

    /** 操作者id */
    @Excel(name = "操作者id")
    private Long creatorId;

    /** 公司Id */
    @Excel(name = "公司Id")
    private Long companyId;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String companyName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setApplicationName(String applicationName) 
    {
        this.applicationName = applicationName;
    }

    public String getApplicationName() 
    {
        return applicationName;
    }
    public void setExceptionSimpleName(String exceptionSimpleName) 
    {
        this.exceptionSimpleName = exceptionSimpleName;
    }

    public String getExceptionSimpleName() 
    {
        return exceptionSimpleName;
    }
    public void setExceptionMessage(String exceptionMessage) 
    {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() 
    {
        return exceptionMessage;
    }
    public void setExceptionCause(String exceptionCause) 
    {
        this.exceptionCause = exceptionCause;
    }

    public String getExceptionCause() 
    {
        return exceptionCause;
    }
    public void setExceptionStack(String exceptionStack) 
    {
        this.exceptionStack = exceptionStack;
    }

    public String getExceptionStack() 
    {
        return exceptionStack;
    }
    public void setCreatorId(Long creatorId) 
    {
        this.creatorId = creatorId;
    }

    public Long getCreatorId() 
    {
        return creatorId;
    }
    public void setCompanyId(Long companyId) 
    {
        this.companyId = companyId;
    }

    public Long getCompanyId() 
    {
        return companyId;
    }
    public void setCompanyName(String companyName) 
    {
        this.companyName = companyName;
    }

    public String getCompanyName() 
    {
        return companyName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("applicationName", getApplicationName())
            .append("exceptionSimpleName", getExceptionSimpleName())
            .append("exceptionMessage", getExceptionMessage())
            .append("exceptionCause", getExceptionCause())
            .append("exceptionStack", getExceptionStack())
            .append("createBy", getCreateBy())
            .append("creatorId", getCreatorId())
            .append("createTime", getCreateTime())
            .append("remark", getRemark())
            .append("companyId", getCompanyId())
            .append("companyName", getCompanyName())
            .toString();
    }
}
