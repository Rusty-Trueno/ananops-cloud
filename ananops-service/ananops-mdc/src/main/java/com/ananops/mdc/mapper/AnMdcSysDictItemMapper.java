package com.ananops.mdc.mapper;

import com.ananops.common.core.dao.BaseMapper;
import com.ananops.mdc.domain.AnMdcSysDictItem;
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
public interface AnMdcSysDictItemMapper extends BaseMapper<AnMdcSysDictItem>
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public AnMdcSysDictItem selectAnMdcSysDictItemById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param anMdcSysDictItem 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
     List<AnMdcSysDictItem> selectAnMdcSysDictItemList(AnMdcSysDictItem anMdcSysDictItem);

    /**
     * 新增【请填写功能名称】
     * 
     * @param anMdcSysDictItem 【请填写功能名称】
     * @return 结果
     */
     int insertAnMdcSysDictItem(AnMdcSysDictItem anMdcSysDictItem);

    /**
     * 修改【请填写功能名称】
     * 
     * @param anMdcSysDictItem 【请填写功能名称】
     * @return 结果
     */
    int updateAnMdcSysDictItem(AnMdcSysDictItem anMdcSysDictItem);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    int deleteAnMdcSysDictItemById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteAnMdcSysDictItemByIds(String[] ids);
}
