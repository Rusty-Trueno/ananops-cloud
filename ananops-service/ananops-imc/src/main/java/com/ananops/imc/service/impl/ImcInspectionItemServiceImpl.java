package com.ananops.imc.service.impl;

import java.util.List;
import com.ananops.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ananops.imc.mapper.ImcInspectionItemMapper;
import com.ananops.imc.domain.ImcInspectionItem;
import com.ananops.imc.service.IImcInspectionItemService;
import com.ananops.common.core.text.Convert;

/**
 * 巡检任务子项Service业务层处理
 * 
 * @author ananops
 * @date 2020-05-19
 */
@Service
public class ImcInspectionItemServiceImpl implements IImcInspectionItemService 
{
    @Autowired
    private ImcInspectionItemMapper imcInspectionItemMapper;

    /**
     * 查询巡检任务子项
     * 
     * @param id 巡检任务子项ID
     * @return 巡检任务子项
     */
    @Override
    public ImcInspectionItem selectImcInspectionItemById(Long id)
    {
        return imcInspectionItemMapper.selectImcInspectionItemById(id);
    }

    /**
     * 查询巡检任务子项列表
     * 
     * @param imcInspectionItem 巡检任务子项
     * @return 巡检任务子项
     */
    @Override
    public List<ImcInspectionItem> selectImcInspectionItemList(ImcInspectionItem imcInspectionItem)
    {
        return imcInspectionItemMapper.selectImcInspectionItemList(imcInspectionItem);
    }

    /**
     * 新增巡检任务子项
     * 
     * @param imcInspectionItem 巡检任务子项
     * @return 结果
     */
    @Override
    public int insertImcInspectionItem(ImcInspectionItem imcInspectionItem)
    {
        imcInspectionItem.setCreateTime(DateUtils.getNowDate());
        return imcInspectionItemMapper.insert(imcInspectionItem);
    }

    /**
     * 修改巡检任务子项
     * 
     * @param imcInspectionItem 巡检任务子项
     * @return 结果
     */
    @Override
    public int updateImcInspectionItem(ImcInspectionItem imcInspectionItem)
    {
        imcInspectionItem.setUpdateTime(DateUtils.getNowDate());
        return imcInspectionItemMapper.updateImcInspectionItem(imcInspectionItem);
    }

    /**
     * 删除巡检任务子项对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImcInspectionItemByIds(String ids)
    {
        return imcInspectionItemMapper.deleteImcInspectionItemByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除巡检任务子项信息
     * 
     * @param id 巡检任务子项ID
     * @return 结果
     */
    public int deleteImcInspectionItemById(Long id)
    {
        return imcInspectionItemMapper.deleteImcInspectionItemById(id);
    }
}
