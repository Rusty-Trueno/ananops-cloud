package com.ananops.mdc.service.impl;

import com.ananops.common.core.service.BaseService;
import com.ananops.common.core.text.Convert;
import com.ananops.common.utils.DateUtils;
import com.ananops.mdc.domain.AnMdcFormTemplate;
import com.ananops.mdc.domain.AnMdcFormTemplateItem;
import com.ananops.mdc.mapper.AnMdcFormTemplateMapper;
import com.ananops.mdc.service.IAnMdcFormTemplateService;
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
public class AnMdcFormTemplateServiceImpl implements IAnMdcFormTemplateService
{
    @Autowired
    private AnMdcFormTemplateMapper anMdcFormTemplateMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public AnMdcFormTemplate selectAnMdcFormTemplateById(Long id)
    {
        return anMdcFormTemplateMapper.selectAnMdcFormTemplateById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param anMdcFormTemplate 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<AnMdcFormTemplate> selectAnMdcFormTemplateList(AnMdcFormTemplate anMdcFormTemplate)
    {
        return anMdcFormTemplateMapper.selectAnMdcFormTemplateList(anMdcFormTemplate);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param anMdcFormTemplate 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertAnMdcFormTemplate(AnMdcFormTemplate anMdcFormTemplate)
    {
        anMdcFormTemplate.setCreateTime(DateUtils.getNowDate());
        return anMdcFormTemplateMapper.insert(anMdcFormTemplate);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param anMdcFormTemplate 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateAnMdcFormTemplate(AnMdcFormTemplate anMdcFormTemplate)
    {
        anMdcFormTemplate.setUpdateTime(DateUtils.getNowDate());
        return anMdcFormTemplateMapper.updateAnMdcFormTemplate(anMdcFormTemplate);
    }

    /**
     * 删除【请填写功能名称】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnMdcFormTemplateByIds(String ids)
    {
        return anMdcFormTemplateMapper.deleteAnMdcFormTemplateByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteAnMdcFormTemplateById(Long id)
    {
        return anMdcFormTemplateMapper.deleteAnMdcFormTemplateById(id);
    }
}
