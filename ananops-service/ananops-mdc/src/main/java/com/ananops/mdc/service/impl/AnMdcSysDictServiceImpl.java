package com.ananops.mdc.service.impl;

import com.ananops.common.core.text.Convert;
import com.ananops.common.utils.DateUtils;
import com.ananops.mdc.domain.AnMdcSysDict;
import com.ananops.mdc.mapper.AnMdcSysDictMapper;
import com.ananops.mdc.service.IAnMdcSysDictService;
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
public class AnMdcSysDictServiceImpl implements IAnMdcSysDictService
{
    @Autowired
    private AnMdcSysDictMapper anMdcSysDictMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public AnMdcSysDict selectAnMdcSysDictById(Long id)
    {
        return anMdcSysDictMapper.selectAnMdcSysDictById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param anMdcSysDict 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<AnMdcSysDict> selectAnMdcSysDictList(AnMdcSysDict anMdcSysDict)
    {
        return anMdcSysDictMapper.selectAnMdcSysDictList(anMdcSysDict);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param anMdcSysDict 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertAnMdcSysDict(AnMdcSysDict anMdcSysDict)
    {
        anMdcSysDict.setCreateTime(DateUtils.getNowDate());
        return anMdcSysDictMapper.insert(anMdcSysDict);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param anMdcSysDict 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateAnMdcSysDict(AnMdcSysDict anMdcSysDict)
    {
        anMdcSysDict.setUpdateTime(DateUtils.getNowDate());
        return anMdcSysDictMapper.updateAnMdcSysDict(anMdcSysDict);
    }

    /**
     * 删除【请填写功能名称】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnMdcSysDictByIds(String ids)
    {
        return anMdcSysDictMapper.deleteAnMdcSysDictByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteAnMdcSysDictById(Long id)
    {
        return anMdcSysDictMapper.deleteAnMdcSysDictById(id);
    }
}
