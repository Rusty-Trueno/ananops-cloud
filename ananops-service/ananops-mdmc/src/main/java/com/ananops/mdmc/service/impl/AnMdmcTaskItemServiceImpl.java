package com.ananops.mdmc.service.impl;

import java.util.List;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.common.exception.BusinessException;
import com.ananops.common.utils.bean.UpdateInfoUtil;
import com.ananops.mdmc.domain.AnMdmcTask;
import com.ananops.mdmc.dto.MdmcAddTaskItemDto;
import com.ananops.mdmc.dto.MdmcQueryDto;
import com.ananops.mdmc.mapper.AnMdmcTaskMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.ananops.mdmc.mapper.AnMdmcTaskItemMapper;
import com.ananops.mdmc.domain.AnMdmcTaskItem;
import com.ananops.mdmc.service.IAnMdmcTaskItemService;
import com.ananops.common.core.text.Convert;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * 维修任务子项Service业务层处理
 *
 * @author ananops
 * @date 2020-05-25
 */
@Service
public class AnMdmcTaskItemServiceImpl implements IAnMdmcTaskItemService
{
    @Resource
    private AnMdmcTaskItemMapper anMdmcTaskItemMapper;

    @Resource
    private AnMdmcTaskMapper anMdmcTaskMapper;

    /**
     * 查询维修任务子项
     *
     * @param id 维修任务子项ID
     * @return 维修任务子项
     */
    @Override
    public AnMdmcTaskItem selectAnMdmcTaskItemById(Long id)
    {
        return anMdmcTaskItemMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询维修任务子项列表
     *
     * @param queryDto 维修任务子项
     * @return 维修任务子项
     */
    @Override
    public PageInfo selectAnMdmcTaskItemList(MdmcQueryDto queryDto)
    {
        Long taskId=queryDto.getTaskId();
        if(anMdmcTaskMapper.selectByPrimaryKey(taskId)==null){
            throw new BusinessException("查无此工单");
        }
        PageHelper.startPage(queryDto.getPageNum(),queryDto.getPageSize());
        List<AnMdmcTaskItem> itemList=anMdmcTaskItemMapper.selectAnMdmcTaskItemByTaskId(taskId);
        return new PageInfo<>(itemList);
    }

    /**
     * 新增维修任务子项
     *
     * @param itemDto 维修任务子项
     * @return 结果
     */
    @Override
    public AnMdmcTaskItem insertAnMdmcTaskItem(MdmcAddTaskItemDto itemDto, LoginAuthDto loginAuthDto)
    {
        AnMdmcTaskItem taskItem=new AnMdmcTaskItem();
        BeanUtils.copyProperties(itemDto,taskItem);
        UpdateInfoUtil.setInsertInfo(taskItem,loginAuthDto);
        Long taskId = itemDto.getTaskId();
        if (taskId==null){
            throw new BusinessException("工单id不能是空");
        }
        if(anMdmcTaskMapper.selectByPrimaryKey(taskId)==null){
            throw new BusinessException("当前工单不存在");
        }

        anMdmcTaskItemMapper.insert(taskItem);
        return taskItem;
    }

    /**
     * 修改维修任务子项
     *
     * @param itemDto 维修任务子项
     * @return 结果
     */
    @Override
    public AnMdmcTaskItem updateAnMdmcTaskItem(MdmcAddTaskItemDto itemDto,LoginAuthDto loginAuthDto)
    {
        Long itemId=itemDto.getId();
        if(anMdmcTaskItemMapper.selectByPrimaryKey(itemId)==null){
            throw new BusinessException("查无此任务子项");
        }
       AnMdmcTaskItem item=new AnMdmcTaskItem();
        BeanUtils.copyProperties(itemDto,item);
        UpdateInfoUtil.setModifyInfo(item,loginAuthDto);
        anMdmcTaskItemMapper.updateByPrimaryKeySelective(item);
        AnMdmcTaskItem item1=anMdmcTaskItemMapper.selectByPrimaryKey(itemId);
        BeanUtils.copyProperties(item1,item);
        return item;
    }

    /**
     * 删除维修任务子项对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnMdmcTaskItemByIds(String ids)
    {
        return anMdmcTaskItemMapper.deleteAnMdmcTaskItemByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除维修任务子项信息
     *
     * @param id 维修任务子项ID
     * @return 结果
     */
    @Override
    public int deleteAnMdmcTaskItemById(Long id)
    {
        return anMdmcTaskItemMapper.deleteByPrimaryKey(id);
    }
}
