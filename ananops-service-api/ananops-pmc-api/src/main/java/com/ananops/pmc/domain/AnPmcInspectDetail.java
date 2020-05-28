package com.ananops.pmc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ananops.common.annotation.Excel;
import com.ananops.common.core.domain.BaseEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 【请填写功能名称】对象 an_pmc_inspect_detail
 * 
 * @author ananops
 * @date 2020-05-28
 */
public class AnPmcInspectDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID 主键*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 版本号 */
    @Excel(name = "版本号", readConverterExp = "$column.readConverterExp()")
    private String version;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 巡检任务ID */
    @Excel(name = "巡检任务ID")
    private Long inspectionTaskId;

    /** 巡检任务名称 */
    @Excel(name = "巡检任务名称")
    private String inspectionTaskName;

    /** 巡检网点 */
    @Excel(name = "巡检网点")
    private String itemName;

    /** 维修工id */
    @Excel(name = "维修工id")
    private Long maintainerId;

    /** 维修工名称 */
    @Excel(name = "维修工名称")
    private String maintainerName;

    /** 巡检内容描述 */
    @Excel(name = "巡检内容描述")
    private String description;

    /** 巡检结果 */
    @Excel(name = "巡检结果")
    private String result;

    /** 巡检状态 */
    @Excel(name = "巡检状态")
    private Integer status;

    /** $column.columnComment */
    @Excel(name = "巡检状态")
    private Long creatorId;

    /** $column.columnComment */
    @Excel(name = "巡检状态")
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
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setInspectionTaskId(Long inspectionTaskId) 
    {
        this.inspectionTaskId = inspectionTaskId;
    }

    public Long getInspectionTaskId() 
    {
        return inspectionTaskId;
    }
    public void setInspectionTaskName(String inspectionTaskName) 
    {
        this.inspectionTaskName = inspectionTaskName;
    }

    public String getInspectionTaskName() 
    {
        return inspectionTaskName;
    }
    public void setItemName(String itemName) 
    {
        this.itemName = itemName;
    }

    public String getItemName() 
    {
        return itemName;
    }
    public void setMaintainerId(Long maintainerId) 
    {
        this.maintainerId = maintainerId;
    }

    public Long getMaintainerId() 
    {
        return maintainerId;
    }
    public void setMaintainerName(String maintainerName) 
    {
        this.maintainerName = maintainerName;
    }

    public String getMaintainerName() 
    {
        return maintainerName;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setResult(String result) 
    {
        this.result = result;
    }

    public String getResult() 
    {
        return result;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
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
            .append("name", getName())
            .append("inspectionTaskId", getInspectionTaskId())
            .append("inspectionTaskName", getInspectionTaskName())
            .append("itemName", getItemName())
            .append("maintainerId", getMaintainerId())
            .append("maintainerName", getMaintainerName())
            .append("description", getDescription())
            .append("result", getResult())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("creatorId", getCreatorId())
            .append("createTime", getCreateTime())
            .append("lastOperator", getLastOperator())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
