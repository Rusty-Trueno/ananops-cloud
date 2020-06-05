package com.ananops.mdc.service.impl;

import com.ananops.common.core.text.Convert;
import com.ananops.common.utils.DateUtils;
import com.ananops.mdc.domain.AnMdcFormTemplateItem;
import com.ananops.mdc.mapper.AnMdcFormTemplateItemMapper;
import com.ananops.mdc.service.IAnMdcFormTemplateItemService;
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
public class AnMdcFormTemplateItemServiceImpl implements IAnMdcFormTemplateItemService
{
    @Autowired
    private AnMdcFormTemplateItemMapper anMdcFormTemplateItemMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public AnMdcFormTemplateItem selectAnMdcFormTemplateItemById(Long id)
    {
        return anMdcFormTemplateItemMapper.selectAnMdcFormTemplateItemById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param anMdcFormTemplateItem 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<AnMdcFormTemplateItem> selectAnMdcFormTemplateItemList(AnMdcFormTemplateItem anMdcFormTemplateItem)
    {
        return anMdcFormTemplateItemMapper.selectAnMdcFormTemplateItemList(anMdcFormTemplateItem);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param anMdcFormTemplateItem 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertAnMdcFormTemplateItem(AnMdcFormTemplateItem anMdcFormTemplateItem)
    {
        anMdcFormTemplateItem.setCreateTime(DateUtils.getNowDate());
        return anMdcFormTemplateItemMapper.insert(anMdcFormTemplateItem);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param anMdcFormTemplateItem 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateAnMdcFormTemplateItem(AnMdcFormTemplateItem anMdcFormTemplateItem)
    {
        anMdcFormTemplateItem.setUpdateTime(DateUtils.getNowDate());
        return anMdcFormTemplateItemMapper.updateAnMdcFormTemplateItem(anMdcFormTemplateItem);
    }

    /**
     * 删除【请填写功能名称】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnMdcFormTemplateItemByIds(String ids)
    {
        return anMdcFormTemplateItemMapper.deleteAnMdcFormTemplateItemByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteAnMdcFormTemplateItemById(Long id)
    {
        return anMdcFormTemplateItemMapper.deleteAnMdcFormTemplateItemById(id);
    }
}
