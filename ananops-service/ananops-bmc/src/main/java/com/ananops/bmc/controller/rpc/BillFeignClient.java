package com.ananops.bmc.controller.rpc;

import com.ananops.bmc.domain.AnBmcBill;
import com.ananops.bmc.mapper.AnBmcBillMapper;
import com.ananops.common.core.controller.BaseController;
import com.ananops.common.core.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@RestController
@Api(value = "API - BillFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BillFeignClient extends BaseController{
    @Resource
    AnBmcBillMapper bmcBillMapper;

    @ApiOperation(httpMethod = "GET", value = "根据工单ID查询金额")
    public R getAmountByWorkOrderId(Long workOrderId) {
        List<AnBmcBill> list=new ArrayList<>();
        Example example = new Example(AnBmcBill.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(String.valueOf(workOrderId));
        list= bmcBillMapper.selectByExample(example);
        AnBmcBill bmcBill=new AnBmcBill();
        if(list!=null&&list.size()>0){
            for (AnBmcBill bmcBill1:list){
                bmcBill=bmcBill1;
            }
        }
        return R.data(bmcBill.getAmount());
    }
}
