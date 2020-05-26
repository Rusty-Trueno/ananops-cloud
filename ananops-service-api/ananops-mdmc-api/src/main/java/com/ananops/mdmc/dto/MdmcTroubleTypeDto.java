package com.ananops.mdmc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by huqiaoqian on 2020/5/26
 */
@Data
@ApiModel
public class MdmcTroubleTypeDto implements Serializable {
    private static final long serialVersionUID = -2335745663971245973L;

    @ApiModelProperty(value = "id，不传")
    private Long id;

    @ApiModelProperty(value = "故障类型")
    private String troubleType;
}
