package com.ananops.amc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ananops.common.annotation.Excel;
import com.ananops.common.core.domain.BaseEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 告警对象 an_amc_alarm
 * 
 * @author ananops
 * @date 2020-05-20
 */
public class AnAmcAlarm extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID，主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 版本号 */
    @Excel(name = "版本号")
    private Long version;

    /** 部门ID */
    @Excel(name = "部门ID")
    private Long deptId;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private String deptName;

    /** 告警名称 */
    @Excel(name = "告警名称")
    private String alarmName;

    /** 告警类型 */
    @Excel(name = "告警类型")
    private String alarmType;

    /** 告警等级，1-紧急，2-可疑，3-提醒 */
    @Excel(name = "告警等级，1-紧急，2-可疑，3-提醒")
    private Integer alarmLevel;

    /** 受影响资产 */
    @Excel(name = "受影响资产")
    private String alarmAsset;

    /** 受影响资产类型 */
    @Excel(name = "受影响资产类型")
    private String assetType;

    /** 告警状态，1-待处理，0-已处理 */
    @Excel(name = "告警状态，1-待处理，0-已处理")
    private Integer alarmStatus;

    /** 报警位置 */
    @Excel(name = "报警位置")
    private String alarmLocation;

    /** 报警经度 */
    @Excel(name = "报警经度")
    private Long alarmLongitude;

    /** 报警维度 */
    @Excel(name = "报警维度")
    private Long alarmLatitude;

    /** 报警图片 */
    @Excel(name = "报警图片")
    private String alarmPhoto;

    /** 最近发生时间 */
    @Excel(name = "最近发生时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastOccurTime;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 创建者ID */
    @Excel(name = "创建者ID")
    private Long creatorId;

    /** 最近操作者ID */
    @Excel(name = "最近操作者ID")
    private Long lastOperatorId;

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
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setDeptName(String deptName) 
    {
        this.deptName = deptName;
    }

    public String getDeptName() 
    {
        return deptName;
    }
    public void setAlarmName(String alarmName) 
    {
        this.alarmName = alarmName;
    }

    public String getAlarmName() 
    {
        return alarmName;
    }
    public void setAlarmType(String alarmType) 
    {
        this.alarmType = alarmType;
    }

    public String getAlarmType() 
    {
        return alarmType;
    }
    public void setAlarmLevel(Integer alarmLevel) 
    {
        this.alarmLevel = alarmLevel;
    }

    public Integer getAlarmLevel() 
    {
        return alarmLevel;
    }
    public void setAlarmAsset(String alarmAsset) 
    {
        this.alarmAsset = alarmAsset;
    }

    public String getAlarmAsset() 
    {
        return alarmAsset;
    }
    public void setAssetType(String assetType) 
    {
        this.assetType = assetType;
    }

    public String getAssetType() 
    {
        return assetType;
    }
    public void setAlarmStatus(Integer alarmStatus) 
    {
        this.alarmStatus = alarmStatus;
    }

    public Integer getAlarmStatus() 
    {
        return alarmStatus;
    }
    public void setAlarmLocation(String alarmLocation) 
    {
        this.alarmLocation = alarmLocation;
    }

    public String getAlarmLocation() 
    {
        return alarmLocation;
    }
    public void setAlarmLongitude(Long alarmLongitude) 
    {
        this.alarmLongitude = alarmLongitude;
    }

    public Long getAlarmLongitude() 
    {
        return alarmLongitude;
    }
    public void setAlarmLatitude(Long alarmLatitude) 
    {
        this.alarmLatitude = alarmLatitude;
    }

    public Long getAlarmLatitude() 
    {
        return alarmLatitude;
    }
    public void setAlarmPhoto(String alarmPhoto) 
    {
        this.alarmPhoto = alarmPhoto;
    }

    public String getAlarmPhoto() 
    {
        return alarmPhoto;
    }
    public void setLastOccurTime(Date lastOccurTime) 
    {
        this.lastOccurTime = lastOccurTime;
    }

    public Date getLastOccurTime() 
    {
        return lastOccurTime;
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
    public void setLastOperatorId(Long lastOperatorId) 
    {
        this.lastOperatorId = lastOperatorId;
    }

    public Long getLastOperatorId() 
    {
        return lastOperatorId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("version", getVersion())
            .append("deptId", getDeptId())
            .append("deptName", getDeptName())
            .append("alarmName", getAlarmName())
            .append("alarmType", getAlarmType())
            .append("alarmLevel", getAlarmLevel())
            .append("alarmAsset", getAlarmAsset())
            .append("assetType", getAssetType())
            .append("alarmStatus", getAlarmStatus())
            .append("alarmLocation", getAlarmLocation())
            .append("alarmLongitude", getAlarmLongitude())
            .append("alarmLatitude", getAlarmLatitude())
            .append("alarmPhoto", getAlarmPhoto())
            .append("lastOccurTime", getLastOccurTime())
            .append("description", getDescription())
            .append("creatorId", getCreatorId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("lastOperatorId", getLastOperatorId())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
