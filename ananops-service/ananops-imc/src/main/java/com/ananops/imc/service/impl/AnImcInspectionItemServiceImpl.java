package com.ananops.imc.service.impl;

import java.util.List;

import com.ananops.common.core.domain.UpdateInfo;
import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.common.utils.DateUtils;
import com.ananops.common.utils.bean.UpdateInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ananops.imc.mapper.AnImcInspectionItemMapper;
import com.ananops.imc.domain.AnImcInspectionItem;
import com.ananops.imc.service.IAnImcInspectionItemService;
import com.ananops.common.core.text.Convert;

/**
 * 巡检任务子项Service业务层处理
 * 
 * @author ananops
 * @date 2020-05-22
 */
@Service
public class AnImcInspectionItemServiceImpl implements IAnImcInspectionItemService 
{
    @Autowired
    private AnImcInspectionItemMapper anImcInspectionItemMapper;

    /**
     * 查询巡检任务子项
     * 
     * @param id 巡检任务子项ID
     * @return 巡检任务子项
     */
    @Override
    public AnImcInspectionItem selectAnImcInspectionItemById(Long id)
    {
        return anImcInspectionItemMapper.selectAnImcInspectionItemById(id);
    }

    /**
     * 查询巡检任务子项列表
     * 
     * @param anImcInspectionItem 巡检任务子项
     * @return 巡检任务子项
     */
    @Override
    public List<AnImcInspectionItem> selectAnImcInspectionItemList(AnImcInspectionItem anImcInspectionItem)
    {
        return anImcInspectionItemMapper.selectAnImcInspectionItemList(anImcInspectionItem);
    }

    /**
     * 新增巡检任务子项
     * 
     * @param anImcInspectionItem 巡检任务子项
     * @return 结果
     */
    @Override
    public int insertAnImcInspectionItem(AnImcInspectionItem anImcInspectionItem, LoginAuthDto user)
    {
        UpdateInfoUtil.setInsertInfo(anImcInspectionItem,user);
        return anImcInspectionItemMapper.insert(anImcInspectionItem);
    }

    /**
     * 修改巡检任务子项
     * 
     * @param anImcInspectionItem 巡检任务子项
     * @return 结果
     */
    @Override
    public int updateAnImcInspectionItem(AnImcInspectionItem anImcInspectionItem)
    {
        anImcInspectionItem.setUpdateTime(DateUtils.getNowDate());
        return anImcInspectionItemMapper.updateAnImcInspectionItem(anImcInspectionItem);
    }

    /**
     * 删除巡检任务子项对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnImcInspectionItemByIds(String ids)
    {
        return anImcInspectionItemMapper.deleteAnImcInspectionItemByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除巡检任务子项信息
     * 
     * @param id 巡检任务子项ID
     * @return 结果
     */
    public int deleteAnImcInspectionItemById(Long id)
    {
        return anImcInspectionItemMapper.deleteAnImcInspectionItemById(id);
    }
}
