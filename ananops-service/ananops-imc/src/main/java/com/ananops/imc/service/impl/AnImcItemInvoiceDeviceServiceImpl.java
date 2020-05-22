package com.ananops.imc.service.impl;

import java.util.List;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.common.utils.DateUtils;
import com.ananops.common.utils.bean.UpdateInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ananops.imc.mapper.AnImcItemInvoiceDeviceMapper;
import com.ananops.imc.domain.AnImcItemInvoiceDevice;
import com.ananops.imc.service.IAnImcItemInvoiceDeviceService;
import com.ananops.common.core.text.Convert;

/**
 * 设备实例Service业务层处理
 * 
 * @author ananops
 * @date 2020-05-22
 */
@Service
public class AnImcItemInvoiceDeviceServiceImpl implements IAnImcItemInvoiceDeviceService 
{
    @Autowired
    private AnImcItemInvoiceDeviceMapper anImcItemInvoiceDeviceMapper;

    /**
     * 查询设备实例
     * 
     * @param id 设备实例ID
     * @return 设备实例
     */
    @Override
    public AnImcItemInvoiceDevice selectAnImcItemInvoiceDeviceById(Long id)
    {
        return anImcItemInvoiceDeviceMapper.selectAnImcItemInvoiceDeviceById(id);
    }

    /**
     * 查询设备实例列表
     * 
     * @param anImcItemInvoiceDevice 设备实例
     * @return 设备实例
     */
    @Override
    public List<AnImcItemInvoiceDevice> selectAnImcItemInvoiceDeviceList(AnImcItemInvoiceDevice anImcItemInvoiceDevice)
    {
        return anImcItemInvoiceDeviceMapper.selectAnImcItemInvoiceDeviceList(anImcItemInvoiceDevice);
    }

    /**
     * 新增设备实例
     * 
     * @param anImcItemInvoiceDevice 设备实例
     * @return 结果
     */
    @Override
    public int insertAnImcItemInvoiceDevice(AnImcItemInvoiceDevice anImcItemInvoiceDevice, LoginAuthDto user)
    {
        UpdateInfoUtil.setInsertInfo(anImcItemInvoiceDevice,user);
        return anImcItemInvoiceDeviceMapper.insert(anImcItemInvoiceDevice);
    }

    /**
     * 修改设备实例
     * 
     * @param anImcItemInvoiceDevice 设备实例
     * @return 结果
     */
    @Override
    public int updateAnImcItemInvoiceDevice(AnImcItemInvoiceDevice anImcItemInvoiceDevice)
    {
        anImcItemInvoiceDevice.setUpdateTime(DateUtils.getNowDate());
        return anImcItemInvoiceDeviceMapper.updateAnImcItemInvoiceDevice(anImcItemInvoiceDevice);
    }

    /**
     * 删除设备实例对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnImcItemInvoiceDeviceByIds(String ids)
    {
        return anImcItemInvoiceDeviceMapper.deleteAnImcItemInvoiceDeviceByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除设备实例信息
     * 
     * @param id 设备实例ID
     * @return 结果
     */
    public int deleteAnImcItemInvoiceDeviceById(Long id)
    {
        return anImcItemInvoiceDeviceMapper.deleteAnImcItemInvoiceDeviceById(id);
    }
}
