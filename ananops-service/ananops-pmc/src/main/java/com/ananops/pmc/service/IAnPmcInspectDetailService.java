package com.ananops.pmc.service;

import com.ananops.pmc.domain.AnPmcInspectDetail;
import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ananops
 * @date 2020-05-28
 */
public interface IAnPmcInspectDetailService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    AnPmcInspectDetail selectAnPmcInspectDetailById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param anPmcInspectDetail 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
     List<AnPmcInspectDetail> selectAnPmcInspectDetailList(AnPmcInspectDetail anPmcInspectDetail);

    /**
     * 新增【请填写功能名称】
     * 
     * @param anPmcInspectDetail 【请填写功能名称】
     * @return 结果
     */
     int insertAnPmcInspectDetail(AnPmcInspectDetail anPmcInspectDetail);

    /**
     * 修改【请填写功能名称】
     * 
     * @param anPmcInspectDetail 【请填写功能名称】
     * @return 结果
     */
     int updateAnPmcInspectDetail(AnPmcInspectDetail anPmcInspectDetail);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
     int deleteAnPmcInspectDetailByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
     int deleteAnPmcInspectDetailById(Long id);
}
