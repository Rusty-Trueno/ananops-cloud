package com.ananops.pmc.service.impl;

import java.util.List;
import com.ananops.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ananops.pmc.mapper.AnPmcInspectDetailMapper;
import com.ananops.pmc.domain.AnPmcInspectDetail;
import com.ananops.pmc.service.IAnPmcInspectDetailService;
import com.ananops.common.core.text.Convert;
import tk.mybatis.mapper.entity.Example;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ananops
 * @date 2020-05-28
 */
@Service
public class AnPmcInspectDetailServiceImpl implements IAnPmcInspectDetailService 
{
    @Autowired
    private AnPmcInspectDetailMapper anPmcInspectDetailMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public AnPmcInspectDetail selectAnPmcInspectDetailById(Long id)
    {
        return anPmcInspectDetailMapper.selectAnPmcInspectDetailById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param anPmcInspectDetail 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<AnPmcInspectDetail> selectAnPmcInspectDetailList(AnPmcInspectDetail anPmcInspectDetail)
    {
        return anPmcInspectDetailMapper.selectAnPmcInspectDetailList(anPmcInspectDetail);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param anPmcInspectDetail 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertAnPmcInspectDetail(AnPmcInspectDetail anPmcInspectDetail)
    {
        anPmcInspectDetail.setCreateTime(DateUtils.getNowDate());
        return anPmcInspectDetailMapper.insert(anPmcInspectDetail);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param anPmcInspectDetail 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateAnPmcInspectDetail(AnPmcInspectDetail anPmcInspectDetail)
    {
        anPmcInspectDetail.setUpdateTime(DateUtils.getNowDate());
        return anPmcInspectDetailMapper.updateAnPmcInspectDetail(anPmcInspectDetail);
    }

    /**
     * 删除【请填写功能名称】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnPmcInspectDetailByIds(String ids)
    {
        return anPmcInspectDetailMapper.deleteAnPmcInspectDetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteAnPmcInspectDetailById(Long id)
    {
        return anPmcInspectDetailMapper.deleteAnPmcInspectDetailById(id);
    }

    @Override
    public AnPmcInspectDetail getInspectDetailById(Long id) {
        return anPmcInspectDetailMapper.selectAnPmcInspectDetailById(id);
    }

    @Override
    public List<AnPmcInspectDetail> getInspectDetailList(Long inspectTaskId) {
        Example example = new Example(AnPmcInspectDetail.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("inspectionTaskId", inspectTaskId);
        return anPmcInspectDetailMapper.selectByExample(example);
    }

    @Override
    public int deleteDetailByTaskId(Long taskId) {
        Example example = new Example(AnPmcInspectDetail.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("inspectionTaskId", taskId);
        return anPmcInspectDetailMapper.deleteByExample(example);
    }
}
