package com.ananops.imc.service;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.common.core.service.IService;
import com.ananops.imc.domain.AnImcInspectionItem;
import com.ananops.imc.dto.ImcAddInspectionItemDto;
import com.ananops.imc.dto.ImcItemChangeStatusDto;

import java.util.List;

/**
 * 巡检任务子项Service接口
 * 
 * @author ananops
 * @date 2020-05-22
 */
public interface IAnImcInspectionItemService extends IService<AnImcInspectionItem>
{
    /**
     * 查询巡检任务子项
     * 
     * @param id 巡检任务子项ID
     * @return 巡检任务子项
     */
    public AnImcInspectionItem selectAnImcInspectionItemById(Long id);

    /**
     * 查询巡检任务子项列表
     * 
     * @param anImcInspectionItem 巡检任务子项
     * @return 巡检任务子项集合
     */
    public List<AnImcInspectionItem> selectAnImcInspectionItemList(AnImcInspectionItem anImcInspectionItem);

    /**
     * 新增巡检任务子项
     * 
     * @param imcAddInspectionItemDto 巡检任务子项
     * @return 结果
     */
    public ImcAddInspectionItemDto insertAnImcInspectionItem(ImcAddInspectionItemDto imcAddInspectionItemDto, LoginAuthDto user);

    /**
     * 修改巡检任务子项
     * 
     * @param anImcInspectionItem 巡检任务子项
     * @return 结果
     */
    public int updateAnImcInspectionItem(AnImcInspectionItem anImcInspectionItem);

    /**
     * 批量删除巡检任务子项
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnImcInspectionItemByIds(String ids);

    /**
     * 删除巡检任务子项信息
     * 
     * @param id 巡检任务子项ID
     * @return 结果
     */
    public int deleteAnImcInspectionItemById(Long id);

    /**
     * 修改巡检任务子项的状态
     * @param imcItemChangeStatusDto
     * @param user
     * @return
     */
    public ImcItemChangeStatusDto modifyImcItemStatus(ImcItemChangeStatusDto imcItemChangeStatusDto,LoginAuthDto user);
}
