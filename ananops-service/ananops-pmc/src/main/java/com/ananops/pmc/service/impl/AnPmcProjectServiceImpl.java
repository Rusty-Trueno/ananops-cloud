package com.ananops.pmc.service.impl;

import java.util.List;
import com.ananops.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ananops.pmc.mapper.AnPmcProjectMapper;
import com.ananops.pmc.domain.AnPmcProject;
import com.ananops.pmc.service.IAnPmcProjectService;
import com.ananops.common.core.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ananops
 * @date 2020-05-28
 */
@Service
public class AnPmcProjectServiceImpl implements IAnPmcProjectService 
{
    @Autowired
    private AnPmcProjectMapper anPmcProjectMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public AnPmcProject selectAnPmcProjectById(Long id)
    {
        return anPmcProjectMapper.selectAnPmcProjectById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param anPmcProject 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<AnPmcProject> selectAnPmcProjectList(AnPmcProject anPmcProject)
    {
        return anPmcProjectMapper.selectAnPmcProjectList(anPmcProject);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param anPmcProject 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertAnPmcProject(AnPmcProject anPmcProject)
    {
        anPmcProject.setCreateTime(DateUtils.getNowDate());
        return anPmcProjectMapper.insert(anPmcProject);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param anPmcProject 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateAnPmcProject(AnPmcProject anPmcProject)
    {
        anPmcProject.setUpdateTime(DateUtils.getNowDate());
        return anPmcProjectMapper.updateAnPmcProject(anPmcProject);
    }

    /**
     * 删除【请填写功能名称】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnPmcProjectByIds(String ids)
    {
        return anPmcProjectMapper.deleteAnPmcProjectByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteAnPmcProjectById(Long id)
    {
        return anPmcProjectMapper.deleteAnPmcProjectById(id);
    }
}
