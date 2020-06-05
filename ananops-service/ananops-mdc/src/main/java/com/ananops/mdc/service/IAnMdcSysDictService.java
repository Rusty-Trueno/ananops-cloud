package com.ananops.mdc.service;

import com.ananops.mdc.domain.AnMdcSysDict;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ananops
 * @date 2020-06-04
 */
public interface IAnMdcSysDictService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    AnMdcSysDict selectAnMdcSysDictById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param anMdcSysDict 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
     List<AnMdcSysDict> selectAnMdcSysDictList(AnMdcSysDict anMdcSysDict);

    /**
     * 新增【请填写功能名称】
     * 
     * @param anMdcSysDict 【请填写功能名称】
     * @return 结果
     */
     int insertAnMdcSysDict(AnMdcSysDict anMdcSysDict);

    /**
     * 修改【请填写功能名称】
     * 
     * @param anMdcSysDict 【请填写功能名称】
     * @return 结果
     */
     int updateAnMdcSysDict(AnMdcSysDict anMdcSysDict);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
     int deleteAnMdcSysDictByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
     int deleteAnMdcSysDictById(Long id);
}
