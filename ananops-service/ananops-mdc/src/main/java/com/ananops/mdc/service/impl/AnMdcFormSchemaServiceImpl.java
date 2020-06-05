package com.ananops.mdc.service.impl;

import com.ananops.common.core.text.Convert;
import com.ananops.common.utils.DateUtils;
import com.ananops.mdc.domain.AnMdcFormSchema;
import com.ananops.mdc.mapper.AnMdcFormSchemaMapper;
import com.ananops.mdc.service.IAnMdcFormSchemaService;
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
public class AnMdcFormSchemaServiceImpl implements IAnMdcFormSchemaService
{
    @Autowired
    private AnMdcFormSchemaMapper anMdcFormSchemaMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public AnMdcFormSchema selectAnMdcFormSchemaById(Long id)
    {
        return anMdcFormSchemaMapper.selectAnMdcFormSchemaById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param anMdcFormSchema 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<AnMdcFormSchema> selectAnMdcFormSchemaList(AnMdcFormSchema anMdcFormSchema)
    {
        return anMdcFormSchemaMapper.selectAnMdcFormSchemaList(anMdcFormSchema);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param anMdcFormSchema 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertAnMdcFormSchema(AnMdcFormSchema anMdcFormSchema)
    {
        anMdcFormSchema.setCreateTime(DateUtils.getNowDate());
        return anMdcFormSchemaMapper.insert(anMdcFormSchema);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param anMdcFormSchema 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateAnMdcFormSchema(AnMdcFormSchema anMdcFormSchema)
    {
        anMdcFormSchema.setUpdateTime(DateUtils.getNowDate());
        return anMdcFormSchemaMapper.updateAnMdcFormSchema(anMdcFormSchema);
    }

    /**
     * 删除【请填写功能名称】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnMdcFormSchemaByIds(String ids)
    {
        return anMdcFormSchemaMapper.deleteAnMdcFormSchemaByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteAnMdcFormSchemaById(Long id)
    {
        return anMdcFormSchemaMapper.deleteAnMdcFormSchemaById(id);
    }
}
