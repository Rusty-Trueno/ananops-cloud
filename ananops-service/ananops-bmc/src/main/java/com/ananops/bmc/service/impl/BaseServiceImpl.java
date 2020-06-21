package com.ananops.bmc.service.impl;

import com.alibaba.fastjson.JSON;
import com.ananops.bmc.domain.AnBmcBill;
import com.ananops.bmc.dto.BillCreateDto;
import com.ananops.bmc.dto.BillDisplayDto;
import com.ananops.bmc.mapper.AnBmcBillMapper;
import com.ananops.bmc.service.BaseService;
import com.ananops.system.domain.SysDept;
import com.ananops.system.domain.SysUser;
import com.ananops.system.feign.RemoteDeptService;
import com.ananops.system.feign.RemoteUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class BaseServiceImpl implements BaseService {

    @Resource
    AnBmcBillMapper bmcBillMapper;

    @Resource
    private RemoteUserService remoteUserService;

    @Resource
    private RemoteDeptService remoteDeptService;

    //TODO:等待SPC
//    @Resource
//    private SpcCompanyFeignApi spcCompanyFeignApi;

    @Override
    public void insert(BillCreateDto billCreateDto, BigDecimal devicePrice, BigDecimal servicePrice, String transactionMethod) {
        Date date=new Date();
        Long time=date.getTime();
        AnBmcBill bill = new AnBmcBill();

        int billIndex = bmcBillMapper.selectAll().size()%100000;

        String idString = String.format("%04d", date.getYear()+1900)+String.format("%02d", date.getMonth()+1)+String.format("%02d",date.getDate())+String.format("%02d", date.getHours())+String.format("%02d", date.getMinutes())+String.format("%02d", date.getSeconds())+String.format("%05d",billIndex);
        Long id = Long.valueOf(idString);
        bill.setId(id);
        bill.setPaymentMethod(billCreateDto.getPaymentMethod());
        /*
        transactionMethod（1-现结、2-账期、3-年结）
         */
        switch (transactionMethod){
            case "1":
                bill.setTransactionMethod("现结");
                break;
            case "2":
                bill.setTransactionMethod("账期");
                break;
            case "3":
                bill.setTransactionMethod("年结");
                break;
        }
        bill.setUserId(billCreateDto.getUserId());
        bill.setTime(time);
        SysUser sysUser = remoteUserService.selectSysUserByUserId(billCreateDto.getSupplier());
        Long groupId = sysUser.getDeptId();
        SysDept sysDept = remoteDeptService.selectSysDeptByDeptId(groupId);
        if (sysDept.getParentId() == 0){
        } else {
            groupId = sysDept.getParentId();
        }
//        switch (companyDto.getType()){
//            case "department":
//                groupId = companyDto.getPid();
//                break;
//            case "company":
//                break;
//            default:
//                log.info("暂不支持此类公司类型，公司类型："+companyDto.getType());
//        }
        bill.setSupplier(groupId);
        bill.setWorkOrderId(billCreateDto.getWorkOrderId());
        bill.setProjectId(billCreateDto.getProjectId());
        bill.setState(billCreateDto.getState());
        bill.setDeviceAmount(devicePrice.longValue());
        bill.setServiceAmount(servicePrice.longValue());
        bill.setAmount(bill.getDeviceAmount()+bill.getServiceAmount());
        //sysUser
//        UserInfoDto sysUser = remoteUserService.getUacUserById(bill.getUserId()).getResult();
        bill.setUserName(sysUser.getUserName());
        //TODO:等待SPC
//        CompanyVo companyVo = spcCompanyFeignApi.getCompanyDetailsById(groupId).getResult();
//        if(companyVo != null){
//            bill.setSupplierName(companyVo.getGroupName());
//        }else{
//            log.info("通过服务商ID："+bill.getSupplier()+"找到的服务商名为空");
//        }
        bill.setVersion((long) 1);
        bill.setCreateBy(bill.getUserName());
        bill.setCreatorId(bill.getUserId());
        bill.setCreateTime(new Date());
        bill.setLastOperator(bill.getUserName());
//        bill.setLastOperatorId(bill.getUserId());
        bill.setUpdateTime(new Date());
        bmcBillMapper.insertSelective(bill);
    }

    @Override
    public List<BillDisplayDto> getAllBillByUserId(Long userId) {
        //使用模板example获取BaseBill类
        Example example = new Example(AnBmcBill.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);

        //将获取到的BaseBill类添加到basebills列表中去
//        List<TaskDto> imcTaskDtos = imcTaskFeignApi.getByFacilitatorId(taskQueryDto).getResult();
        List<AnBmcBill> bmcBills = bmcBillMapper.selectByExample(example);

        //创建待返回的账单展示传输对象
        List<BillDisplayDto> billDisplayDtoList = new ArrayList<>();
        if(bmcBills != null){
            for(AnBmcBill bmcBill : bmcBills){
                BillDisplayDto billDisplayDto = new BillDisplayDto();
                try{
                    BeanUtils.copyProperties(bmcBill,billDisplayDto);
                }catch (Exception e){
                    log.error("账单BaseBill与账单展示billDisplayDto属性拷贝异常");
                    e.printStackTrace();
                }

                //没问题就添加到待返回列表中
                billDisplayDtoList.add(billDisplayDto);

            }
        }
        billDisplayDtoList.sort(BillDisplayDto.Comparators.TIME);
        return billDisplayDtoList;
    }

    public List<BillDisplayDto> getBillsByUserIdAndState(Long userId, String state){
        Example example = new Example(AnBmcBill.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);
        List<AnBmcBill> bmcBills = bmcBillMapper.selectByExample(example);
        List<BillDisplayDto> billDisplayDtoList = new ArrayList<>();
        if(bmcBills != null){
            for(AnBmcBill bmcBill : bmcBills){
                BillDisplayDto billDisplayDto = new BillDisplayDto();
                if (!bmcBill.getState().equals(state)){
                    continue;
                }
                try{
                    BeanUtils.copyProperties(bmcBill,billDisplayDto);
                }catch (Exception e){
                    log.error("账单BaseBill与账单展示billDisplayDto属性拷贝异常");
                    e.printStackTrace();
                }
                billDisplayDtoList.add(billDisplayDto);

            }
        }
        billDisplayDtoList.sort(BillDisplayDto.Comparators.TIME);
        return billDisplayDtoList;
    }

    public Object getMoneySumByUserIdYearMonthAndLength(Long userId, int year, int month, int length){
        Example example = new Example(AnBmcBill.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);
        List<AnBmcBill> bmcBills = bmcBillMapper.selectByExample(example);
        List<BigDecimal> moneySums = new ArrayList<>();
        if (bmcBills == null){
            log.error("bmcBills list is null!");
        }
        assert bmcBills != null;
        int calcYear = year;
        int calcMonth = month;
        for (int i = 0;i<length;i++){
            BigDecimal moneySum = new BigDecimal(0);
            for (AnBmcBill bmcBill:bmcBills){
                Date billDate = new Date();
                billDate.setTime(bmcBill.getTime());
                int billYear = billDate.getYear()+1900;
                int billMonth = billDate.getMonth()+1;
                if (billYear == calcYear && billMonth == calcMonth){
                    moneySum = moneySum.add(new BigDecimal(bmcBill.getAmount()));
                }
            }
            calcMonth--;
            if (calcMonth == 0){
                calcMonth = 12;
                calcYear--;
            }
            moneySums.add(moneySum);
        }
        Collections.reverse(moneySums);
        return JSON.toJSON(moneySums);
    }

    public BigDecimal getMoneySumByUserIdYearAndMonth(Long userId, int year, int month){
        Example example = new Example(AnBmcBill.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);
        List<AnBmcBill> bmcBills = bmcBillMapper.selectByExample(example);
        if (bmcBills == null){
            log.error("bmcBills list is null!");
        }
        assert bmcBills != null;
        BigDecimal moneySum = new BigDecimal(0);
        for (AnBmcBill bmcBill:bmcBills){
            Date billDate = new Date();
            billDate.setTime(bmcBill.getTime());
            int billYear = billDate.getYear()+1900;
            int billMonth = billDate.getMonth()+1;
            if (billYear == year && billMonth == month){
                moneySum = moneySum.add(new BigDecimal(bmcBill.getAmount()));
            }
        }
        return moneySum;
    }

    public BillDisplayDto getOneBillById(Long id) {
        AnBmcBill bmcBill = getBillById(id);

        //创建待返回的账单展示传输对象
        BillDisplayDto billDisplayDto = new BillDisplayDto();

        try{
            BeanUtils.copyProperties(bmcBill,billDisplayDto);
        }catch (Exception e){
            log.error("账单BaseBill与账单展示billDisplayDto属性拷贝异常");
            e.printStackTrace();
        }

        return billDisplayDto;
    }

    public int getBillNumByUserId(Long userId){
        Example example = new Example(AnBmcBill.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);

        return bmcBillMapper.selectCountByExample(example);
    }

    public int getBillNumByUserIdAndState(Long userId, String state) {
        Example example = new Example(AnBmcBill.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        List<AnBmcBill> list = bmcBillMapper.selectByExample(example);
        List<AnBmcBill> bmcBills = new ArrayList<>();
        if(list != null && list.size()>0){
            for(AnBmcBill bmcBill:list){
                if(bmcBill.getState().equals(state)){
                    bmcBills.add(bmcBill);
                }
            }
        }
        return bmcBills.size();
    }

    public int getBillNumByUserIdAndTransactionMethod(Long userId, String transactionMethod) {
        Example example = new Example(AnBmcBill.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        List<AnBmcBill> list = bmcBillMapper.selectByExample(example);
        List<AnBmcBill> bmcBills = new ArrayList<>();
        if(list != null && list.size()>0){
            for(AnBmcBill bmcBill:list){
                if(bmcBill.getTransactionMethod().equals(transactionMethod)){
                    bmcBills.add(bmcBill);
                }
            }
        }
        return bmcBills.size();
    }

    public int getBillNumByUserIdAndAmount(Long userId, BigDecimal amount) {
        Example example = new Example(AnBmcBill.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        List<AnBmcBill> list = bmcBillMapper.selectByExample(example);
        List<AnBmcBill> bmcBills = new ArrayList<>();
        if(list != null && list.size()>0){
            for(AnBmcBill bmcBill:list){
                if(bmcBill.getAmount().compareTo(amount.longValue()) > 0){
                    bmcBills.add(bmcBill);
                }
            }
        }
        return bmcBills.size();
    }

    @Override
    public BigDecimal getAmountByWorkOrderId(Long workOrderId) {
        Example example = new Example(AnBmcBill.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("workOrderId",workOrderId);
        List<AnBmcBill> list = bmcBillMapper.selectByExample(example);
        AnBmcBill bmcBill = list.get(0);
        return new BigDecimal(bmcBill.getAmount());
    }

    @Override
    public AnBmcBill getBillById(Long id) {
        return bmcBillMapper.selectByPrimaryKey(id);
    }

    @Override
    public void modifyAmount(AnBmcBill bmcBill, BigDecimal modifyAmount) {
        bmcBill.setAmount(modifyAmount.longValue());
        bmcBillMapper.updateByPrimaryKey(bmcBill);
    }

    @Override
    public List<AnBmcBill> getAllUBillByState(Long userId, String state) {
        Example example = new Example(AnBmcBill.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        List<AnBmcBill> list = bmcBillMapper.selectByExample(example);
        List<AnBmcBill> bmcBills = new ArrayList<>();
        if(list != null && list.size()>0){
            for(AnBmcBill bmcBill:list){
                if(bmcBill.getState().equals(state)){
                    bmcBills.add(bmcBill);
                }
            }
        }
        return bmcBills;
    }

    @Override
    public List<AnBmcBill> getBillByWorkOrderId(Long workOrderId) {
        Example example = new Example(AnBmcBill.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(workOrderId);
        return bmcBillMapper.selectByExample(example);
    }
}
