package com.ananops.mdc.mapper;

import com.ananops.common.core.dao.BaseMapper;
import com.ananops.mdc.domain.AnMdcSysDict;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ananops
 * @date 2020-06-04
 */
public interface AnMdcSysDictMapper extends BaseMapper<AnMdcSysDict>
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public AnMdcSysDict selectAnMdcSysDictById(Long id);

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
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    int deleteAnMdcSysDictById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteAnMdcSysDictByIds(String[] ids);
}
