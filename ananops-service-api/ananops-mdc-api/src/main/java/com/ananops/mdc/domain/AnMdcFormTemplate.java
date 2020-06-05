package com.ananops.mdc.domain;

import com.ananops.common.annotation.Excel;
import com.ananops.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 【请填写功能名称】对象 an_mdc_form_template
 * 
 * @author ananops
 * @date 2020-06-04
 */
public class AnMdcFormTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 表单模板ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Schema ID */
    @Excel(name = "Schema ID")
    private Long schemaId;

    /** 表单类型（system/user） */
    @Excel(name = "表单类型", readConverterExp = "s=ystem/user")
    private String type;

    /** 表单状态 */
    @Excel(name = "表单状态")
    private String status;

    /** 表单备注说明 */
    @Excel(name = "表单备注说明")
    private String mark;

    /** 公司组织ID */
    @Excel(name = "公司组织ID")
    private Long groupId;

    /** 关联的项目ID */
    @Excel(name = "关联的项目ID")
    private Long projectId;

    /** 关联的项目名称 */
    @Excel(name = "关联的项目名称")
    private String projectName;

    /** 逻辑删除 */
    @Excel(name = "逻辑删除")
    private String dr;

    /** 版本号 */
    @Excel(name = "版本号")
    private Long version;

    /** 创建人ID */
    @Excel(name = "创建人ID")
    private Long creatorId;

    /** 最后操作人 */
    @Excel(name = "最后操作人")
    private String lastOperator;

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
    public void setSchemaId(Long schemaId) 
    {
        this.schemaId = schemaId;
    }

    public Long getSchemaId() 
    {
        return schemaId;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setMark(String mark) 
    {
        this.mark = mark;
    }

    public String getMark() 
    {
        return mark;
    }
    public void setGroupId(Long groupId) 
    {
        this.groupId = groupId;
    }

    public Long getGroupId() 
    {
        return groupId;
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
    public void setDr(String dr) 
    {
        this.dr = dr;
    }

    public String getDr() 
    {
        return dr;
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
            .append("schemaId", getSchemaId())
            .append("type", getType())
            .append("status", getStatus())
            .append("mark", getMark())
            .append("groupId", getGroupId())
            .append("projectId", getProjectId())
            .append("projectName", getProjectName())
            .append("dr", getDr())
            .append("version", getVersion())
            .append("createBy", getCreateBy())
            .append("creatorId", getCreatorId())
            .append("createTime", getCreateTime())
            .append("lastOperator", getLastOperator())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("companyId", getCompanyId())
            .append("companyName", getCompanyName())
            .toString();
    }
}
