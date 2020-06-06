package com.ananops.mdc.domain;

import com.ananops.common.annotation.Excel;
import com.ananops.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 【请填写功能名称】对象 an_mdc_form_schema
 * 
 * @author ananops
 * @date 2020-06-04
 */
public class AnMdcFormSchema extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 表单ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 表单名称 */
    @Excel(name = "表单名称")
    private String name;

    /** FormRender的属性结构 */
    @Excel(name = "FormRender的属性结构")
    private String propsSchema;

    /** FormRender的UI结构 */
    @Excel(name = "FormRender的UI结构")
    private String uiSchema;

    /** 表单类型（system/user） */
    @Excel(name = "表单类型", readConverterExp = "s=ystem/user")
    private String type;

    /** E(编辑)/V(查看) */
    @Excel(name = "E(编辑)/V(查看)")
    private String status;

    /** 表单备注说明 */
    @Excel(name = "表单备注说明")
    private String mark;

    /** 公司组织ID */
    @Excel(name = "公司组织ID")
    private Long deptId;

    /** 关联的项目ID */
    @Excel(name = "关联的项目ID")
    private Long projectId;

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
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setPropsSchema(String propsSchema) 
    {
        this.propsSchema = propsSchema;
    }

    public String getPropsSchema() 
    {
        return propsSchema;
    }
    public void setUiSchema(String uiSchema) 
    {
        this.uiSchema = uiSchema;
    }

    public String getUiSchema() 
    {
        return uiSchema;
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
    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getDeptId()
    {
        return deptId;
    }
    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
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
            .append("name", getName())
            .append("propsSchema", getPropsSchema())
            .append("uiSchema", getUiSchema())
            .append("type", getType())
            .append("status", getStatus())
            .append("mark", getMark())
            .append("deptId", getDeptId())
            .append("projectId", getProjectId())
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
