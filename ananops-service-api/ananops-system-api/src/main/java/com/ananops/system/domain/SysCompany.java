package com.ananops.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ananops.common.annotation.Excel;
import com.ananops.common.core.domain.BaseEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 公司对象 sys_company
 *
 * @author ananops
 * @date 2020-05-25
 */
public class SysCompany extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 公司id，主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 部门ID
     */
    @Excel(name = "部门ID")
    private Long deptId;

    /**
     * 公司编码
     */
    @Excel(name = "公司编码")
    private String companyCode;

    /**
     * 公司名称
     */
    @Excel(name = "公司名称")
    private String companyName;

    /**
     * 公司类型
     */
    @Excel(name = "公司类型")
    private String type;

    /**
     * 法人姓名
     */
    @Excel(name = "法人姓名")
    private String legalPersonName;

    /**
     * 法人联系方式
     */
    @Excel(name = "法人联系方式")
    private String legalPersonPhone;

    /**
     * 法人身份证号
     */
    @Excel(name = "法人身份证号")
    private String legalPersonNumber;

    /**
     * 主体行业
     */
    @Excel(name = "主体行业")
    private String mainWork;

    /**
     * 国家地区
     */
    @Excel(name = "国家地区")
    private String country;

    /**
     * 邮政编码
     */
    @Excel(name = "邮政编码")
    private String zipCode;

    /**
     * 基本户账户名称
     */
    @Excel(name = "基本户账户名称")
    private String accountName;

    /**
     * 基本账户账号
     */
    @Excel(name = "基本账户账号")
    private String accountNumber;

    /**
     * 营业执照类别
     */
    @Excel(name = "营业执照类别")
    private String licenseType;

    /**
     * 注册资本
     */
    @Excel(name = "注册资本")
    private String registeredCaptial;

    /**
     * 有效期
     */
    @Excel(name = "有效期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expirationDate;

    /**
     * 经营范围
     */
    @Excel(name = "经营范围")
    private String businessScope;

    /**
     * 供应产品类别
     */
    @Excel(name = "供应产品类别")
    private String productCategory;

    /**
     * 法人身份证照片url
     */
    @Excel(name = "法人身份证照片url")
    private String legalPersonidPhoto;

    /**
     * 营业执照照片url
     */
    @Excel(name = "营业执照照片url")
    private String businessLicensePhoto;

    /**
     * 账户开户许可证照片url
     */
    @Excel(name = "账户开户许可证照片url")
    private String accountOpeningLicense;

    /**
     * 组织地址
     */
    @Excel(name = "组织地址")
    private String compayAddress;

    /**
     * 省名称
     */
    @Excel(name = "省名称")
    private String provinceName;

    /**
     * 省编码
     */
    @Excel(name = "省编码")
    private Long provinceId;

    /**
     * 城市名称
     */
    @Excel(name = "城市名称")
    private String cityName;

    /**
     * 城市编码
     */
    @Excel(name = "城市编码")
    private Long cityId;

    /**
     * 区名称
     */
    @Excel(name = "区名称")
    private String areaName;

    /**
     * 区编码
     */
    @Excel(name = "区编码")
    private Long areaId;

    /**
     * 街道名称
     */
    @Excel(name = "街道名称")
    private String streetName;

    /**
     * 街道编码
     */
    @Excel(name = "街道编码")
    private Long streetId;

    /**
     * 详细地址
     */
    @Excel(name = "详细地址")
    private String detailAddress;

    /**
     * 公司状态（0正常 1停用）
     */
    @Excel(name = "公司状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    /**
     * 版本号
     */
    @Excel(name = "版本号")
    private Long version;

    /**
     * 创建人ID
     */
    @Excel(name = "创建人ID")
    private Long creatorId;

    /**
     * 最后操作人ID
     */
    @Excel(name = "最后操作人ID")
    private Long lastOperatorId;

    /**
     * 用户ID
     */
    @Excel(name = "用户ID")
    private Long userId;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName;
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public void setLegalPersonPhone(String legalPersonPhone) {
        this.legalPersonPhone = legalPersonPhone;
    }

    public String getLegalPersonPhone() {
        return legalPersonPhone;
    }

    public void setLegalPersonNumber(String legalPersonNumber) {
        this.legalPersonNumber = legalPersonNumber;
    }

    public String getLegalPersonNumber() {
        return legalPersonNumber;
    }

    public void setMainWork(String mainWork) {
        this.mainWork = mainWork;
    }

    public String getMainWork() {
        return mainWork;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setRegisteredCaptial(String registeredCaptial) {
        this.registeredCaptial = registeredCaptial;
    }

    public String getRegisteredCaptial() {
        return registeredCaptial;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setLegalPersonidPhoto(String legalPersonidPhoto) {
        this.legalPersonidPhoto = legalPersonidPhoto;
    }

    public String getLegalPersonidPhoto() {
        return legalPersonidPhoto;
    }

    public void setBusinessLicensePhoto(String businessLicensePhoto) {
        this.businessLicensePhoto = businessLicensePhoto;
    }

    public String getBusinessLicensePhoto() {
        return businessLicensePhoto;
    }

    public void setAccountOpeningLicense(String accountOpeningLicense) {
        this.accountOpeningLicense = accountOpeningLicense;
    }

    public String getAccountOpeningLicense() {
        return accountOpeningLicense;
    }

    public void setCompayAddress(String compayAddress) {
        this.compayAddress = compayAddress;
    }

    public String getCompayAddress() {
        return compayAddress;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetId(Long streetId) {
        this.streetId = streetId;
    }

    public Long getStreetId() {
        return streetId;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getVersion() {
        return version;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setLastOperatorId(Long lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }

    public Long getLastOperatorId() {
        return lastOperatorId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("deptId", getDeptId())
                .append("companyCode", getCompanyCode())
                .append("companyName", getCompanyName())
                .append("type", getType())
                .append("legalPersonName", getLegalPersonName())
                .append("legalPersonPhone", getLegalPersonPhone())
                .append("legalPersonNumber", getLegalPersonNumber())
                .append("mainWork", getMainWork())
                .append("country", getCountry())
                .append("zipCode", getZipCode())
                .append("accountName", getAccountName())
                .append("accountNumber", getAccountNumber())
                .append("licenseType", getLicenseType())
                .append("registeredCaptial", getRegisteredCaptial())
                .append("expirationDate", getExpirationDate())
                .append("businessScope", getBusinessScope())
                .append("productCategory", getProductCategory())
                .append("legalPersonidPhoto", getLegalPersonidPhoto())
                .append("businessLicensePhoto", getBusinessLicensePhoto())
                .append("accountOpeningLicense", getAccountOpeningLicense())
                .append("compayAddress", getCompayAddress())
                .append("provinceName", getProvinceName())
                .append("provinceId", getProvinceId())
                .append("cityName", getCityName())
                .append("cityId", getCityId())
                .append("areaName", getAreaName())
                .append("areaId", getAreaId())
                .append("streetName", getStreetName())
                .append("streetId", getStreetId())
                .append("detailAddress", getDetailAddress())
                .append("status", getStatus())
                .append("delFlag", getDelFlag())
                .append("version", getVersion())
                .append("createBy", getCreateBy())
                .append("creatorId", getCreatorId())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("lastOperatorId", getLastOperatorId())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("userId", getUserId())
                .toString();
    }
}
