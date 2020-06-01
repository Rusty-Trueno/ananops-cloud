package com.ananops.pmc.controller.rpc;

import com.ananops.common.core.domain.R;
import com.ananops.common.utils.bean.BeanUtils;
import com.ananops.pmc.domain.AnPmcContract;
import com.ananops.pmc.dto.PmcContractDto;
import com.ananops.pmc.dto.PmcPayDto;
import com.ananops.pmc.service.impl.AnPmcContractServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangyue
 * @CreatedTime 2020/5/30 15:52
 **/
@RefreshScope
@RestController
@Api(value = "API - AnPmcContractFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AnPmcContractFeignClient {


    @Autowired
    AnPmcContractServiceImpl anPmcContractService;


    @ApiOperation(httpMethod = "POST", value = "获取甲乙双方合同的支付方式和支付金额")
    public R getContactByAB(@PathVariable(value = "partyAId") Long partyAId, @PathVariable(value = "partyBId") Long partyBId) {
        List<AnPmcContract> anPmcContractList = anPmcContractService.getContactByAB(partyAId, partyBId);
        List<PmcPayDto> pmcPayDtoList = new ArrayList<>();
        if (anPmcContractList != null) {
            for (AnPmcContract anPmcContract : anPmcContractList) {
                PmcPayDto pmcPayDto = new PmcPayDto();
                pmcPayDto.setPaymentType(anPmcContract.getPaymentType());
                pmcPayDto.setPaymentMoney(anPmcContract.getPaymentMoney());
                pmcPayDtoList.add(pmcPayDto);
            }
            return R.data(pmcPayDtoList);
        }
        return R.ok("获取失败");
    }


    @ApiOperation(httpMethod = "POST", value = " 根据合同id查询合同")
    public R getContractById(@PathVariable(value = "contractId") Long contractId) {
        AnPmcContract anPmcContract = anPmcContractService.selectAnPmcContractById(contractId);
        PmcContractDto pmcContractDto = new PmcContractDto();
        BeanUtils.copyProperties(anPmcContract, pmcContractDto);
        return R.data(pmcContractDto);
    }
}