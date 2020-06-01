package com.ananops.pmc.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * Created By zhangyue On 2020/05/28
 */
@Data
@ApiModel
public class PmcProReqQueryDto implements Serializable {

    private static final long serialVersionUID = 8336639667959028926L;

    /**
     * 公司部门Id
     */
    private Long deptId;

    /**
     * 项目类型
     */
    private String projectType;
}
