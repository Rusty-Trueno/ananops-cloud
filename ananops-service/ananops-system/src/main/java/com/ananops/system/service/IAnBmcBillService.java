package com.ananops.system.service;

import com.ananops.system.domain.AnBmcBill;
import java.util.List;

/**
 * 账单Service接口
 * 
 * @author ananops
 * @date 2020-05-26
 */
public interface IAnBmcBillService 
{
    /**
     * 查询账单
     * 
     * @param id 账单ID
     * @return 账单
     */
    AnBmcBill selectAnBmcBillById(Long id);

    /**
     * 查询账单列表
     * 
     * @param anBmcBill 账单
     * @return 账单集合
     */
     List<AnBmcBill> selectAnBmcBillList(AnBmcBill anBmcBill);

    /**
     * 新增账单
     * 
     * @param anBmcBill 账单
     * @return 结果
     */
     int insertAnBmcBill(AnBmcBill anBmcBill);

    /**
     * 修改账单
     * 
     * @param anBmcBill 账单
     * @return 结果
     */
     int updateAnBmcBill(AnBmcBill anBmcBill);

    /**
     * 批量删除账单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
     int deleteAnBmcBillByIds(String ids);

    /**
     * 删除账单信息
     * 
     * @param id 账单ID
     * @return 结果
     */
     int deleteAnBmcBillById(Long id);
}
