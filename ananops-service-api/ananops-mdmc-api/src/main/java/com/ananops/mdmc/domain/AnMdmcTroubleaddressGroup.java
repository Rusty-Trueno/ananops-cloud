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
 * 关联故障地址与组织对象 an_mdmc_troubleaddress_group
 *
 * @author ananops
 * @date 2020-05-25
 */
public class AnMdmcTroubleaddressGroup extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long version;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String creator;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long creatorId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date createdTime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String lastOperator;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long lastOperatorId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String troubleAddress;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long troubleLatitude;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long troubleLongitude;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long groupId;

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
    public void setTroubleAddress(String troubleAddress)
    {
        this.troubleAddress = troubleAddress;
    }

    public String getTroubleAddress()
    {
        return troubleAddress;
    }
    public void setTroubleLatitude(Long troubleLatitude)
    {
        this.troubleLatitude = troubleLatitude;
    }

    public Long getTroubleLatitude()
    {
        return troubleLatitude;
    }
    public void setTroubleLongitude(Long troubleLongitude)
    {
        this.troubleLongitude = troubleLongitude;
    }

    public Long getTroubleLongitude()
    {
        return troubleLongitude;
    }
    public void setGroupId(Long groupId)
    {
        this.groupId = groupId;
    }

    public Long getGroupId()
    {
        return groupId;
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
                .append("troubleAddress", getTroubleAddress())
                .append("troubleLatitude", getTroubleLatitude())
                .append("troubleLongitude", getTroubleLongitude())
                .append("groupId", getGroupId())
                .toString();
    }
}
