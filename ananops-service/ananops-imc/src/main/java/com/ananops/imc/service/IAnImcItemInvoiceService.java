package com.ananops.imc.service;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.imc.domain.AnImcItemInvoice;
import java.util.List;

/**
 * 巡检记录Service接口
 * 
 * @author ananops
 * @date 2020-05-22
 */
public interface IAnImcItemInvoiceService 
{
    /**
     * 查询巡检记录
     * 
     * @param id 巡检记录ID
     * @return 巡检记录
     */
    public AnImcItemInvoice selectAnImcItemInvoiceById(Long id);

    /**
     * 查询巡检记录列表
     * 
     * @param anImcItemInvoice 巡检记录
     * @return 巡检记录集合
     */
    public List<AnImcItemInvoice> selectAnImcItemInvoiceList(AnImcItemInvoice anImcItemInvoice);

    /**
     * 新增巡检记录
     * 
     * @param anImcItemInvoice 巡检记录
     * @return 结果
     */
    public int insertAnImcItemInvoice(AnImcItemInvoice anImcItemInvoice, LoginAuthDto user);

    /**
     * 修改巡检记录
     * 
     * @param anImcItemInvoice 巡检记录
     * @return 结果
     */
    public int updateAnImcItemInvoice(AnImcItemInvoice anImcItemInvoice);

    /**
     * 批量删除巡检记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnImcItemInvoiceByIds(String ids);

    /**
     * 删除巡检记录信息
     * 
     * @param id 巡检记录ID
     * @return 结果
     */
    public int deleteAnImcItemInvoiceById(Long id);
}
