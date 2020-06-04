package com.ananops.mdmc.dto;

import com.ananops.mdmc.domain.AnMdmcTask;
import com.ananops.system.domain.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class MdmcTaskDetailDto implements Serializable {
    private static final long serialVersionUID = -2161110542642870049L;
    /**
     * 工单内容
     */
    @ApiModelProperty(value = "工单内容")
    private AnMdmcTask mdmcTask;

    /**
     * 项目信息
     */
//    @ApiModelProperty(value = "项目信息")
//    private PmcProjectDto pmcProjectDto;

    /**
     * 服务商信息
     */
    @ApiModelProperty(value = "服务商信息")
    private SysUser facInfo;

    /**
     * 报修人信息
     */
    @ApiModelProperty(value = "报修人信息")
     private SysUser userInfoDto;

    /**
     * 维修工信息
     */
    @ApiModelProperty(value = "维修工信息")
    private SysUser engineerInfo;

    /**
     * 用户负责人信息
     */
    @ApiModelProperty(value = "用户负责人信息")
    private SysUser principalInfo;

}
