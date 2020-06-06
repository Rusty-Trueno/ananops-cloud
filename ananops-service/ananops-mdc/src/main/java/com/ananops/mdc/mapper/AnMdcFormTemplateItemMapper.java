package com.ananops.mdc.mapper;

import com.ananops.common.core.dao.BaseMapper;
import com.ananops.mdc.domain.AnMdcFormTemplateItem;
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
public interface AnMdcFormTemplateItemMapper extends BaseMapper<AnMdcFormTemplateItem>
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public AnMdcFormTemplateItem selectAnMdcFormTemplateItemById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param anMdcFormTemplateItem 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
     List<AnMdcFormTemplateItem> selectAnMdcFormTemplateItemList(AnMdcFormTemplateItem anMdcFormTemplateItem);

    /**
     * 新增【请填写功能名称】
     * 
     * @param anMdcFormTemplateItem 【请填写功能名称】
     * @return 结果
     */
     int insertAnMdcFormTemplateItem(AnMdcFormTemplateItem anMdcFormTemplateItem);

    /**
     * 修改【请填写功能名称】
     * 
     * @param anMdcFormTemplateItem 【请填写功能名称】
     * @return 结果
     */
    int updateAnMdcFormTemplateItem(AnMdcFormTemplateItem anMdcFormTemplateItem);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    int deleteAnMdcFormTemplateItemById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteAnMdcFormTemplateItemByIds(String[] ids);
}
