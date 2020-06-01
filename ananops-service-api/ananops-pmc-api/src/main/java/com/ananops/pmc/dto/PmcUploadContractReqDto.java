package com.ananops.pmc.dto;


import com.ananops.system.domain.SysOss;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created By zhangyue On 2020/05/28
 */
@Data
@ApiModel(value = "PmcUploadContractReqDto")
public class PmcUploadContractReqDto implements Serializable {
    private static final long serialVersionUID = -1009897798005094602L;

    /**
     * 合同 id
     */
    @ApiModelProperty(value = "contractId")
    private Long contractId;

    /**
     * 文件上传Dto
     */
    //TODO 暂时先用的system里的文件上传功能的实体类，之后再考虑别的
    @ApiModelProperty(value = "sys_oss文件上传")
    private SysOss uploadFileReqDto;
}
