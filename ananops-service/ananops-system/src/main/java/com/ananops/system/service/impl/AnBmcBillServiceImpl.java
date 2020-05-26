package com.ananops.system.service.impl;

import java.util.List;
import com.ananops.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ananops.system.mapper.AnBmcBillMapper;
import com.ananops.system.domain.AnBmcBill;
import com.ananops.system.service.IAnBmcBillService;
import com.ananops.common.core.text.Convert;

/**
 * 账单Service业务层处理
 * 
 * @author ananops
 * @date 2020-05-26
 */
@Service
public class AnBmcBillServiceImpl implements IAnBmcBillService 
{
    @Autowired
    private AnBmcBillMapper anBmcBillMapper;

    /**
     * 查询账单
     * 
     * @param id 账单ID
     * @return 账单
     */
    @Override
    public AnBmcBill selectAnBmcBillById(Long id)
    {
        return anBmcBillMapper.selectAnBmcBillById(id);
    }

    /**
     * 查询账单列表
     * 
     * @param anBmcBill 账单
     * @return 账单
     */
    @Override
    public List<AnBmcBill> selectAnBmcBillList(AnBmcBill anBmcBill)
    {
        return anBmcBillMapper.selectAnBmcBillList(anBmcBill);
    }

    /**
     * 新增账单
     * 
     * @param anBmcBill 账单
     * @return 结果
     */
    @Override
    public int insertAnBmcBill(AnBmcBill anBmcBill)
    {
        anBmcBill.setCreateTime(DateUtils.getNowDate());
        return anBmcBillMapper.insert(anBmcBill);
    }

    /**
     * 修改账单
     * 
     * @param anBmcBill 账单
     * @return 结果
     */
    @Override
    public int updateAnBmcBill(AnBmcBill anBmcBill)
    {
        anBmcBill.setUpdateTime(DateUtils.getNowDate());
        return anBmcBillMapper.updateAnBmcBill(anBmcBill);
    }

    /**
     * 删除账单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnBmcBillByIds(String ids)
    {
        return anBmcBillMapper.deleteAnBmcBillByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除账单信息
     * 
     * @param id 账单ID
     * @return 结果
     */
    public int deleteAnBmcBillById(Long id)
    {
        return anBmcBillMapper.deleteAnBmcBillById(id);
    }
}
