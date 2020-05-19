package com.ananops.imc.service;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.imc.domain.ImcInspectionItem;
import java.util.List;

/**
 * 巡检任务子项Service接口
 * 
 * @author ananops
 * @date 2020-05-19
 */
public interface IImcInspectionItemService 
{
    /**
     * 查询巡检任务子项
     * 
     * @param id 巡检任务子项ID
     * @return 巡检任务子项
     */
    public ImcInspectionItem selectImcInspectionItemById(Long id);

    /**
     * 查询巡检任务子项列表
     * 
     * @param imcInspectionItem 巡检任务子项
     * @return 巡检任务子项集合
     */
    public List<ImcInspectionItem> selectImcInspectionItemList(ImcInspectionItem imcInspectionItem);

    /**
     * 新增巡检任务子项
     * 
     * @param imcInspectionItem 巡检任务子项
     * @return 结果
     */
    public int insertImcInspectionItem(ImcInspectionItem imcInspectionItem, LoginAuthDto user);

    /**
     * 修改巡检任务子项
     * 
     * @param imcInspectionItem 巡检任务子项
     * @return 结果
     */
    public int updateImcInspectionItem(ImcInspectionItem imcInspectionItem);

    /**
     * 批量删除巡检任务子项
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteImcInspectionItemByIds(String ids);

    /**
     * 删除巡检任务子项信息
     * 
     * @param id 巡检任务子项ID
     * @return 结果
     */
    public int deleteImcInspectionItemById(Long id);
}
