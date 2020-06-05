package com.ananops.mdc.domain;

import com.ananops.common.annotation.Excel;
import com.ananops.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 【请填写功能名称】对象 an_mdc_sys_dict_item
 * 
 * @author ananops
 * @date 2020-06-04
 */
public class AnMdcSysDictItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long dictId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String name;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long sort;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String mark;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String code;

    /** 公司组织Id */
    @Excel(name = "公司组织Id")
    private Long groupId;

    /** 逻辑删除 */
    @Excel(name = "逻辑删除")
    private String dr;

    /** $column.columnComment */
    @Excel(name = "逻辑删除")
    private Double latitude;

    /** $column.columnComment */
    @Excel(name = "逻辑删除")
    private Double longitude;

    /** 版本号 */
    @Excel(name = "版本号")
    private Long version;

    /** 创建人ID */
    @Excel(name = "创建人ID")
    private Long creatorId;

    /** 最近操作人 */
    @Excel(name = "最近操作人")
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
    public void setDictId(Long dictId) 
    {
        this.dictId = dictId;
    }

    public Long getDictId() 
    {
        return dictId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setMark(String mark) 
    {
        this.mark = mark;
    }

    public String getMark() 
    {
        return mark;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setGroupId(Long groupId) 
    {
        this.groupId = groupId;
    }

    public Long getGroupId() 
    {
        return groupId;
    }
    public void setDr(String dr) 
    {
        this.dr = dr;
    }

    public String getDr() 
    {
        return dr;
    }
    public void setLatitude(Double latitude) 
    {
        this.latitude = latitude;
    }

    public Double getLatitude() 
    {
        return latitude;
    }
    public void setLongitude(Double longitude) 
    {
        this.longitude = longitude;
    }

    public Double getLongitude() 
    {
        return longitude;
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
            .append("dictId", getDictId())
            .append("name", getName())
            .append("sort", getSort())
            .append("mark", getMark())
            .append("code", getCode())
            .append("groupId", getGroupId())
            .append("dr", getDr())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("latitude", getLatitude())
            .append("longitude", getLongitude())
            .append("version", getVersion())
            .append("createBy", getCreateBy())
            .append("creatorId", getCreatorId())
            .append("lastOperator", getLastOperator())
            .append("updateBy", getUpdateBy())
            .append("remark", getRemark())
            .append("companyId", getCompanyId())
            .append("companyName", getCompanyName())
            .toString();
    }
}
