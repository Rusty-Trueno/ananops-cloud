package com.ananops.mdc.mapper;

import com.ananops.common.core.dao.BaseMapper;
import com.ananops.mdc.domain.AnMdcFormTemplate;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ananops
 * @date 2020-06-04
 */
@Mapper
@Component
public interface AnMdcFormTemplateMapper extends BaseMapper<AnMdcFormTemplate>
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public AnMdcFormTemplate selectAnMdcFormTemplateById(Long id);

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
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    int deleteAnMdcFormTemplateById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteAnMdcFormTemplateByIds(String[] ids);
}
