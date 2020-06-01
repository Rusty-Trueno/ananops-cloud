package com.ananops.pmc.mapper;

import com.ananops.common.core.dao.BaseMapper;
import com.ananops.pmc.domain.AnPmcInspectDetail;
import java.util.List;

/**
 * 巡检详情Mapper接口
 * 
 * @author ananops
 * @date 2020-05-28
 */
public interface AnPmcInspectDetailMapper extends BaseMapper<AnPmcInspectDetail>
{
    /**
     * 查询巡检详情
     * 
     * @param id 巡检详情ID
     * @return 【请填写功能名称】
     */
    public AnPmcInspectDetail selectAnPmcInspectDetailById(Long id);

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
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    int deleteAnPmcInspectDetailById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteAnPmcInspectDetailByIds(String[] ids);
}
