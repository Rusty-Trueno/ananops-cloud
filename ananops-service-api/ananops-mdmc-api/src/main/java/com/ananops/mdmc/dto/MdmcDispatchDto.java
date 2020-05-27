package com.ananops.mdmc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by huqiaoqian on 2020/5/27
 */
@Data
@ApiModel
public class MdmcDispatchDto implements Serializable {
    private static final long serialVersionUID = 8184838840828445692L;
    @ApiModelProperty("工单id")
    private Long id;

    @ApiModelProperty("服务商id，（平台分配服务商时传）")
    private Long facilitatorId;

    @ApiModelProperty("工程师id，（服务商分配工程师时候传）")
    private Long maintainerId;
}
