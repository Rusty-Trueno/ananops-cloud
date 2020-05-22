package com.ananops.imc.service.impl;

import java.util.List;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.common.utils.DateUtils;
import com.ananops.common.utils.bean.UpdateInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ananops.imc.mapper.AnImcItemInvoiceDescMapper;
import com.ananops.imc.domain.AnImcItemInvoiceDesc;
import com.ananops.imc.service.IAnImcItemInvoiceDescService;
import com.ananops.common.core.text.Convert;

/**
 * 巡检记录对应的设备Service业务层处理
 * 
 * @author ananops
 * @date 2020-05-22
 */
@Service
public class AnImcItemInvoiceDescServiceImpl implements IAnImcItemInvoiceDescService 
{
    @Autowired
    private AnImcItemInvoiceDescMapper anImcItemInvoiceDescMapper;

    /**
     * 查询巡检记录对应的设备
     * 
     * @param id 巡检记录对应的设备ID
     * @return 巡检记录对应的设备
     */
    @Override
    public AnImcItemInvoiceDesc selectAnImcItemInvoiceDescById(Long id)
    {
        return anImcItemInvoiceDescMapper.selectAnImcItemInvoiceDescById(id);
    }

    /**
     * 查询巡检记录对应的设备列表
     * 
     * @param anImcItemInvoiceDesc 巡检记录对应的设备
     * @return 巡检记录对应的设备
     */
    @Override
    public List<AnImcItemInvoiceDesc> selectAnImcItemInvoiceDescList(AnImcItemInvoiceDesc anImcItemInvoiceDesc)
    {
        return anImcItemInvoiceDescMapper.selectAnImcItemInvoiceDescList(anImcItemInvoiceDesc);
    }

    /**
     * 新增巡检记录对应的设备
     * 
     * @param anImcItemInvoiceDesc 巡检记录对应的设备
     * @return 结果
     */
    @Override
    public int insertAnImcItemInvoiceDesc(AnImcItemInvoiceDesc anImcItemInvoiceDesc, LoginAuthDto user)
    {
        UpdateInfoUtil.setInsertInfo(anImcItemInvoiceDesc,user);
        return anImcItemInvoiceDescMapper.insert(anImcItemInvoiceDesc);
    }

    /**
     * 修改巡检记录对应的设备
     * 
     * @param anImcItemInvoiceDesc 巡检记录对应的设备
     * @return 结果
     */
    @Override
    public int updateAnImcItemInvoiceDesc(AnImcItemInvoiceDesc anImcItemInvoiceDesc)
    {
        anImcItemInvoiceDesc.setUpdateTime(DateUtils.getNowDate());
        return anImcItemInvoiceDescMapper.updateAnImcItemInvoiceDesc(anImcItemInvoiceDesc);
    }

    /**
     * 删除巡检记录对应的设备对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnImcItemInvoiceDescByIds(String ids)
    {
        return anImcItemInvoiceDescMapper.deleteAnImcItemInvoiceDescByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除巡检记录对应的设备信息
     * 
     * @param id 巡检记录对应的设备ID
     * @return 结果
     */
    public int deleteAnImcItemInvoiceDescById(Long id)
    {
        return anImcItemInvoiceDescMapper.deleteAnImcItemInvoiceDescById(id);
    }
}
