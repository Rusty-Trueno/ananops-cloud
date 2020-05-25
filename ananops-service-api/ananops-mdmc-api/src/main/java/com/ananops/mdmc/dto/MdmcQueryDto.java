package com.ananops.mdmc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class MdmcQueryDto implements Serializable {

    private static final long serialVersionUID = -2869243934754282708L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("工单状态")
    private Integer status;

}
