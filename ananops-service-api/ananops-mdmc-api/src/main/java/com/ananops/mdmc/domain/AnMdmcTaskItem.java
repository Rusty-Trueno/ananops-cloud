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
 * 维修任务子项对象 an_mdmc_task_item
 *
 * @author ananops
 * @date 2020-05-25
 */
public class AnMdmcTaskItem extends BaseEntity
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

    /** 设备ID */
    @Excel(name = "设备ID")
    private Long deviceId;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String deviceName;

    /** 完成维修的时间戳 */
    @Excel(name = "完成维修的时间戳", width = 30, dateFormat = "yyyy-MM-dd")
    private Date actualFinishTime;

    /** 开始维修的时间戳 */
    @Excel(name = "开始维修的时间戳", width = 30, dateFormat = "yyyy-MM-dd")
    private Date actualStartTime;

    /** 故障描述 */
    @Excel(name = "故障描述")
    private String description;

    /** 故障等级 */
    @Excel(name = "故障等级")
    private Integer level;

    /** 故障设备位置，纬度 */
    @Excel(name = "故障设备位置，纬度")
    private Double deviceLatitude;

    /** 故障设备位置，经度 */
    @Excel(name = "故障设备位置，经度")
    private Double deviceLongitude;

    /** 维修工ID */
    @Excel(name = "维修工ID")
    private Long maintainerId;

    /** 当前维修状态 */
    @Excel(name = "当前维修状态")
    private Integer status;

    /** 维修建议（维修工填写） */
    @Excel(name = "维修建议", readConverterExp = "维=修工填写")
    private String suggestion;

    /** 维修结果（维修工填写） */
    @Excel(name = "维修结果", readConverterExp = "维=修工填写")
    private String result;

    /** $column.columnComment */
    @Excel(name = "维修结果", readConverterExp = "$column.readConverterExp()")
    private String deviceType;

    /** $column.columnComment */
    @Excel(name = "维修结果", readConverterExp = "$column.readConverterExp()")
    private String troubleType;

    /** $column.columnComment */
    @Excel(name = "维修结果", readConverterExp = "$column.readConverterExp()")
    private String troubleAddress;

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
    public void setDeviceId(Long deviceId)
    {
        this.deviceId = deviceId;
    }

    public Long getDeviceId()
    {
        return deviceId;
    }
    public void setDeviceName(String deviceName)
    {
        this.deviceName = deviceName;
    }

    public String getDeviceName()
    {
        return deviceName;
    }
    public void setActualFinishTime(Date actualFinishTime)
    {
        this.actualFinishTime = actualFinishTime;
    }

    public Date getActualFinishTime()
    {
        return actualFinishTime;
    }
    public void setActualStartTime(Date actualStartTime)
    {
        this.actualStartTime = actualStartTime;
    }

    public Date getActualStartTime()
    {
        return actualStartTime;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }
    public void setLevel(Integer level)
    {
        this.level = level;
    }

    public Integer getLevel()
    {
        return level;
    }
    public void setDeviceLatitude(Double deviceLatitude)
    {
        this.deviceLatitude = deviceLatitude;
    }

    public Double getDeviceLatitude()
    {
        return deviceLatitude;
    }
    public void setDeviceLongitude(Double deviceLongitude)
    {
        this.deviceLongitude = deviceLongitude;
    }

    public Double getDeviceLongitude()
    {
        return deviceLongitude;
    }
    public void setMaintainerId(Long maintainerId)
    {
        this.maintainerId = maintainerId;
    }

    public Long getMaintainerId()
    {
        return maintainerId;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }
    public void setSuggestion(String suggestion)
    {
        this.suggestion = suggestion;
    }

    public String getSuggestion()
    {
        return suggestion;
    }
    public void setResult(String result)
    {
        this.result = result;
    }

    public String getResult()
    {
        return result;
    }
    public void setDeviceType(String deviceType)
    {
        this.deviceType = deviceType;
    }

    public String getDeviceType()
    {
        return deviceType;
    }
    public void setTroubleType(String troubleType)
    {
        this.troubleType = troubleType;
    }

    public String getTroubleType()
    {
        return troubleType;
    }
    public void setTroubleAddress(String troubleAddress)
    {
        this.troubleAddress = troubleAddress;
    }

    public String getTroubleAddress()
    {
        return troubleAddress;
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
                .append("deviceId", getDeviceId())
                .append("deviceName", getDeviceName())
                .append("actualFinishTime", getActualFinishTime())
                .append("actualStartTime", getActualStartTime())
                .append("description", getDescription())
                .append("level", getLevel())
                .append("deviceLatitude", getDeviceLatitude())
                .append("deviceLongitude", getDeviceLongitude())
                .append("maintainerId", getMaintainerId())
                .append("status", getStatus())
                .append("suggestion", getSuggestion())
                .append("result", getResult())
                .append("deviceType", getDeviceType())
                .append("troubleType", getTroubleType())
                .append("troubleAddress", getTroubleAddress())
                .toString();
    }
}
