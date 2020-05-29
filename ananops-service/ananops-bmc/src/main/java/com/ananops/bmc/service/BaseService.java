package com.ananops.bmc.service;

import com.ananops.bmc.domain.AnBmcBill;
import com.ananops.bmc.dto.BillCreateDto;
import com.ananops.bmc.dto.BillDisplayDto;

import java.math.BigDecimal;
import java.util.List;

public interface BaseService {
    /**
     * 创建账单
     * @param billCreateDto
     * @param devicePrice
     * @param servicePrice
     * @param transactionMethod
     */
    public void insert(BillCreateDto billCreateDto, BigDecimal devicePrice, BigDecimal servicePrice, String transactionMethod);

    /**
     * 通过userId获取账单信息
     * @param userId
     * @return
     */
    public List<BillDisplayDto> getAllBillByUserId(Long userId);

    /**
     * 通过工单id获取总金额
     * @param workOrderId
     * @return
     */
    public BigDecimal getAmountByWorkOrderId(Long workOrderId);

    /**
     * 通过账单id获取账单信息
     * @param id
     * @return
     */
    public AnBmcBill getBillById(Long id);

    /**
     * 修改账单总金额信息
     * @param bmcBill
     * @param modifyAmount
     */
    public void modifyAmount(AnBmcBill bmcBill, BigDecimal modifyAmount);

    /**
     * 通过userId和目前状态查询所有账单列表
     * @param userId
     * @param state
     * @return
     */
    public List<AnBmcBill> getAllUBillByState(Long userId, String state);

    /**
     * 通过工单id查询所有账单列表
     * @param workOrderId
     * @return
     */
    List<AnBmcBill> getBillByWorkOrderId(Long workOrderId);
}


