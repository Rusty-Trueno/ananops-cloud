package com.ananops.mdmc.dto;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.mdmc.domain.AnMdmcTask;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class MdmcChangeStatusDto implements Serializable {

    private static final long serialVersionUID = -7571315319843498438L;
    /**
     * 工单id
     */
    @ApiModelProperty(value = "工单ID")
    private Long taskId;

    /**
     * 修改后的状态
     */
    @ApiModelProperty(value = "修改后的状态")
    private Integer status;

    /**
     * 任务修改后的状态描述
     */
    @ApiModelProperty(value = "任务修改后的状态描述")
    private String statusMsg;

    /**
     * 修改后的工单
     */
    @ApiModelProperty(value = "修改后的工单，不用传")
    private AnMdmcTask task;
}
