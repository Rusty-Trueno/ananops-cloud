package com.ananops.mdc.service;

import com.ananops.mdc.domain.AnMdcFormSchema;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ananops
 * @date 2020-06-04
 */
public interface IAnMdcFormSchemaService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    AnMdcFormSchema selectAnMdcFormSchemaById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param anMdcFormSchema 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
     List<AnMdcFormSchema> selectAnMdcFormSchemaList(AnMdcFormSchema anMdcFormSchema);

    /**
     * 新增【请填写功能名称】
     * 
     * @param anMdcFormSchema 【请填写功能名称】
     * @return 结果
     */
     int insertAnMdcFormSchema(AnMdcFormSchema anMdcFormSchema);

    /**
     * 修改【请填写功能名称】
     * 
     * @param anMdcFormSchema 【请填写功能名称】
     * @return 结果
     */
     int updateAnMdcFormSchema(AnMdcFormSchema anMdcFormSchema);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
     int deleteAnMdcFormSchemaByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
     int deleteAnMdcFormSchemaById(Long id);
}
