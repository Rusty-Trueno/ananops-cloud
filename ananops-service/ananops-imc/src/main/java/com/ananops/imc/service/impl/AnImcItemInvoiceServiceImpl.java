package com.ananops.imc.service.impl;

import java.util.List;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.common.utils.DateUtils;
import com.ananops.common.utils.bean.UpdateInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ananops.imc.mapper.AnImcItemInvoiceMapper;
import com.ananops.imc.domain.AnImcItemInvoice;
import com.ananops.imc.service.IAnImcItemInvoiceService;
import com.ananops.common.core.text.Convert;

/**
 * 巡检记录Service业务层处理
 * 
 * @author ananops
 * @date 2020-05-22
 */
@Service
public class AnImcItemInvoiceServiceImpl implements IAnImcItemInvoiceService 
{
    @Autowired
    private AnImcItemInvoiceMapper anImcItemInvoiceMapper;

    /**
     * 查询巡检记录
     * 
     * @param id 巡检记录ID
     * @return 巡检记录
     */
    @Override
    public AnImcItemInvoice selectAnImcItemInvoiceById(Long id)
    {
        return anImcItemInvoiceMapper.selectAnImcItemInvoiceById(id);
    }

    /**
     * 查询巡检记录列表
     * 
     * @param anImcItemInvoice 巡检记录
     * @return 巡检记录
     */
    @Override
    public List<AnImcItemInvoice> selectAnImcItemInvoiceList(AnImcItemInvoice anImcItemInvoice)
    {
        return anImcItemInvoiceMapper.selectAnImcItemInvoiceList(anImcItemInvoice);
    }

    /**
     * 新增巡检记录
     * 
     * @param anImcItemInvoice 巡检记录
     * @return 结果
     */
    @Override
    public int insertAnImcItemInvoice(AnImcItemInvoice anImcItemInvoice, LoginAuthDto user)
    {
        UpdateInfoUtil.setInsertInfo(anImcItemInvoice,user);
        return anImcItemInvoiceMapper.insert(anImcItemInvoice);
    }

    /**
     * 修改巡检记录
     * 
     * @param anImcItemInvoice 巡检记录
     * @return 结果
     */
    @Override
    public int updateAnImcItemInvoice(AnImcItemInvoice anImcItemInvoice)
    {
        anImcItemInvoice.setUpdateTime(DateUtils.getNowDate());
        return anImcItemInvoiceMapper.updateAnImcItemInvoice(anImcItemInvoice);
    }

    /**
     * 删除巡检记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnImcItemInvoiceByIds(String ids)
    {
        return anImcItemInvoiceMapper.deleteAnImcItemInvoiceByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除巡检记录信息
     * 
     * @param id 巡检记录ID
     * @return 结果
     */
    public int deleteAnImcItemInvoiceById(Long id)
    {
        return anImcItemInvoiceMapper.deleteAnImcItemInvoiceById(id);
    }
}
