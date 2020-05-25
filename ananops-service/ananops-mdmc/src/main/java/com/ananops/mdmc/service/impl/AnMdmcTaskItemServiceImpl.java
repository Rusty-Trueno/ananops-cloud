package com.ananops.mdmc.service.impl;

import java.util.List;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.common.exception.BusinessException;
import com.ananops.common.utils.DateUtils;
import com.ananops.common.utils.bean.UpdateInfoUtil;
import com.ananops.mdmc.domain.AnMdmcTask;
import com.ananops.mdmc.dto.MdmcAddTaskItemDto;
import com.ananops.mdmc.mapper.AnMdmcTaskMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ananops.mdmc.mapper.AnMdmcTaskItemMapper;
import com.ananops.mdmc.domain.AnMdmcTaskItem;
import com.ananops.mdmc.service.IAnMdmcTaskItemService;
import com.ananops.common.core.text.Convert;
import tk.mybatis.mapper.entity.Example;

/**
 * 维修任务子项Service业务层处理
 *
 * @author ananops
 * @date 2020-05-25
 */
@Service
public class AnMdmcTaskItemServiceImpl implements IAnMdmcTaskItemService
{
    @Autowired
    private AnMdmcTaskItemMapper anMdmcTaskItemMapper;

    @Autowired
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
        return anMdmcTaskItemMapper.selectAnMdmcTaskItemById(id);
    }

    /**
     * 查询维修任务子项列表
     *
     * @param anMdmcTaskItem 维修任务子项
     * @return 维修任务子项
     */
    @Override
    public List<AnMdmcTaskItem> selectAnMdmcTaskItemList(AnMdmcTaskItem anMdmcTaskItem)
    {
        return anMdmcTaskItemMapper.selectAnMdmcTaskItemList(anMdmcTaskItem);
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
        Example example = new Example(AnMdmcTask.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",taskId);
        List<AnMdmcTask> taskList =anMdmcTaskMapper.selectByExample(example);
        if(taskList.size()==0){//如果没有此任务
            throw new BusinessException("当前工单不存在");
        }

        anMdmcTaskItemMapper.insert(taskItem);
        return taskItem;
    }

    /**
     * 修改维修任务子项
     *
     * @param anMdmcTaskItem 维修任务子项
     * @return 结果
     */
    @Override
    public int updateAnMdmcTaskItem(AnMdmcTaskItem anMdmcTaskItem)
    {
        return anMdmcTaskItemMapper.updateAnMdmcTaskItem(anMdmcTaskItem);
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
    public int deleteAnMdmcTaskItemById(Long id)
    {
        return anMdmcTaskItemMapper.deleteAnMdmcTaskItemById(id);
    }
}
