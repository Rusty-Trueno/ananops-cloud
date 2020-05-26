package com.ananops.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ananops.common.annotation.Excel;
import com.ananops.common.core.domain.BaseEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 账单对象 an_bmc_bill
 * 
 * @author ananops
 * @date 2020-05-26
 */
public class AnBmcBill extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID，主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 交易方式 */
    @Excel(name = "交易方式")
    private String paymentMethod;

    /** 结算方式 */
    @Excel(name = "结算方式")
    private String transactionMethod;

    /** 总金额 */
    @Excel(name = "总金额")
    private Long amount;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 用户名 */
    @Excel(name = "用户名")
    private String userName;

    /** 供应商ID */
    @Excel(name = "供应商ID")
    private Long supplier;

    /** 供应商 */
    @Excel(name = "供应商")
    private String supplierName;

    /** 项目ID */
    @Excel(name = "项目ID")
    private Long projectId;

    /** 下单时间 */
    @Excel(name = "下单时间")
    private Long time;

    /** 工单ID */
    @Excel(name = "工单ID")
    private Long workOrderId;

    /** 支付状态 */
    @Excel(name = "支付状态")
    private String state;

    /** 设备金额 */
    @Excel(name = "设备金额")
    private Long deviceAmount;

    /** 服务金额 */
    @Excel(name = "服务金额")
    private Long serviceAmount;

    /** 版本号 */
    @Excel(name = "版本号")
    private Long version;

    /** 创建者ID */
    @Excel(name = "创建者ID")
    private Long creatorId;

    /** 上一个操作员 */
    @Excel(name = "上一个操作员")
    private String lastOperator;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPaymentMethod(String paymentMethod) 
    {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod() 
    {
        return paymentMethod;
    }
    public void setTransactionMethod(String transactionMethod) 
    {
        this.transactionMethod = transactionMethod;
    }

    public String getTransactionMethod() 
    {
        return transactionMethod;
    }
    public void setAmount(Long amount) 
    {
        this.amount = amount;
    }

    public Long getAmount() 
    {
        return amount;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setSupplier(Long supplier) 
    {
        this.supplier = supplier;
    }

    public Long getSupplier() 
    {
        return supplier;
    }
    public void setSupplierName(String supplierName) 
    {
        this.supplierName = supplierName;
    }

    public String getSupplierName() 
    {
        return supplierName;
    }
    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
    }
    public void setTime(Long time) 
    {
        this.time = time;
    }

    public Long getTime() 
    {
        return time;
    }
    public void setWorkOrderId(Long workOrderId) 
    {
        this.workOrderId = workOrderId;
    }

    public Long getWorkOrderId() 
    {
        return workOrderId;
    }
    public void setState(String state) 
    {
        this.state = state;
    }

    public String getState() 
    {
        return state;
    }
    public void setDeviceAmount(Long deviceAmount) 
    {
        this.deviceAmount = deviceAmount;
    }

    public Long getDeviceAmount() 
    {
        return deviceAmount;
    }
    public void setServiceAmount(Long serviceAmount) 
    {
        this.serviceAmount = serviceAmount;
    }

    public Long getServiceAmount() 
    {
        return serviceAmount;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("paymentMethod", getPaymentMethod())
            .append("transactionMethod", getTransactionMethod())
            .append("amount", getAmount())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("supplier", getSupplier())
            .append("supplierName", getSupplierName())
            .append("projectId", getProjectId())
            .append("time", getTime())
            .append("workOrderId", getWorkOrderId())
            .append("state", getState())
            .append("deviceAmount", getDeviceAmount())
            .append("serviceAmount", getServiceAmount())
            .append("version", getVersion())
            .append("createBy", getCreateBy())
            .append("creatorId", getCreatorId())
            .append("createTime", getCreateTime())
            .append("lastOperator", getLastOperator())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
