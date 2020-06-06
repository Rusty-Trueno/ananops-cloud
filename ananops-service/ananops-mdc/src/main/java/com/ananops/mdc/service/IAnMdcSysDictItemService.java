package com.ananops.mdc.service;

import com.ananops.mdc.domain.AnMdcSysDictItem;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ananops
 * @date 2020-06-04
 */
public interface IAnMdcSysDictItemService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    AnMdcSysDictItem selectAnMdcSysDictItemById(Long id);

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
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
     int deleteAnMdcSysDictItemByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
     int deleteAnMdcSysDictItemById(Long id);
}
