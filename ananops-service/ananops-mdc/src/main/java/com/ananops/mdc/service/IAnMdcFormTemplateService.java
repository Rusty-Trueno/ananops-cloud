package com.ananops.mdc.service;

import com.ananops.mdc.domain.AnMdcFormTemplate;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ananops
 * @date 2020-06-04
 */
public interface IAnMdcFormTemplateService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    AnMdcFormTemplate selectAnMdcFormTemplateById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param anMdcFormTemplate 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
     List<AnMdcFormTemplate> selectAnMdcFormTemplateList(AnMdcFormTemplate anMdcFormTemplate);

    /**
     * 新增【请填写功能名称】
     * 
     * @param anMdcFormTemplate 【请填写功能名称】
     * @return 结果
     */
     int insertAnMdcFormTemplate(AnMdcFormTemplate anMdcFormTemplate);

    /**
     * 修改【请填写功能名称】
     * 
     * @param anMdcFormTemplate 【请填写功能名称】
     * @return 结果
     */
     int updateAnMdcFormTemplate(AnMdcFormTemplate anMdcFormTemplate);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
     int deleteAnMdcFormTemplateByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
     int deleteAnMdcFormTemplateById(Long id);
}
