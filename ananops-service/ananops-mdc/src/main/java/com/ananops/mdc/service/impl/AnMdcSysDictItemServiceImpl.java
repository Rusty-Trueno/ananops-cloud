package com.ananops.mdc.service.impl;

import com.ananops.common.core.text.Convert;
import com.ananops.common.utils.DateUtils;
import com.ananops.mdc.domain.AnMdcSysDictItem;
import com.ananops.mdc.mapper.AnMdcSysDictItemMapper;
import com.ananops.mdc.service.IAnMdcSysDictItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ananops
 * @date 2020-06-04
 */
@Service
public class AnMdcSysDictItemServiceImpl implements IAnMdcSysDictItemService
{
    @Autowired
    private AnMdcSysDictItemMapper anMdcSysDictItemMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public AnMdcSysDictItem selectAnMdcSysDictItemById(Long id)
    {
        return anMdcSysDictItemMapper.selectAnMdcSysDictItemById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param anMdcSysDictItem 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<AnMdcSysDictItem> selectAnMdcSysDictItemList(AnMdcSysDictItem anMdcSysDictItem)
    {
        return anMdcSysDictItemMapper.selectAnMdcSysDictItemList(anMdcSysDictItem);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param anMdcSysDictItem 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertAnMdcSysDictItem(AnMdcSysDictItem anMdcSysDictItem)
    {
        anMdcSysDictItem.setCreateTime(DateUtils.getNowDate());
        return anMdcSysDictItemMapper.insert(anMdcSysDictItem);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param anMdcSysDictItem 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateAnMdcSysDictItem(AnMdcSysDictItem anMdcSysDictItem)
    {
        anMdcSysDictItem.setUpdateTime(DateUtils.getNowDate());
        return anMdcSysDictItemMapper.updateAnMdcSysDictItem(anMdcSysDictItem);
    }

    /**
     * 删除【请填写功能名称】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnMdcSysDictItemByIds(String ids)
    {
        return anMdcSysDictItemMapper.deleteAnMdcSysDictItemByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteAnMdcSysDictItemById(Long id)
    {
        return anMdcSysDictItemMapper.deleteAnMdcSysDictItemById(id);
    }
}
