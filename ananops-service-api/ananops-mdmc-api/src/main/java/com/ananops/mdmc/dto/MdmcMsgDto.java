package com.ananops.mdmc.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by huqiaoqian on 2020/6/6
 */
@Data
@ApiModel
public class MdmcMsgDto implements Serializable {
    private static final long serialVersionUID = 7798678285430338251L;
    private Long taskId;

    private Integer status;

    private String statusMsg;
}
