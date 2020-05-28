package com.ananops.mdmc.dto;

import com.ananops.common.core.dto.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class MdmcQueryDto extends BaseQuery implements Serializable {

    private static final long serialVersionUID = -2869243934754282708L;

    @ApiModelProperty("用户id，查工单列表时候传的")
    private Long id;

    @ApiModelProperty("工单id，查任务子项用或者是工单的日志列表时候传的")
    private Long taskId;

    @ApiModelProperty("工单状态,查工单列表用的")
    private Integer status;

}
