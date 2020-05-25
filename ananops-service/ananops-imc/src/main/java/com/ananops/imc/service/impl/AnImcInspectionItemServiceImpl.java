package com.ananops.imc.service.impl;

import java.util.Date;
import java.util.List;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.common.core.service.BaseService;
import com.ananops.common.exception.BusinessException;
import com.ananops.common.utils.DateUtils;
import com.ananops.common.utils.bean.BeanUtils;
import com.ananops.common.utils.bean.UpdateInfoUtil;
import com.ananops.imc.domain.AnImcInspectionTask;
import com.ananops.imc.dto.ImcAddInspectionItemDto;
import com.ananops.imc.dto.ImcItemChangeStatusDto;
import com.ananops.imc.dto.ImcTaskChangeStatusDto;
import com.ananops.imc.enums.ItemStatusEnum;
import com.ananops.imc.enums.TaskStatusEnum;
import com.ananops.imc.mapper.AnImcInspectionTaskMapper;
import com.ananops.imc.service.IAnImcInspectionTaskService;
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
public class AnImcInspectionItemServiceImpl extends BaseService<AnImcInspectionItem> implements IAnImcInspectionItemService
{
    @Autowired
    private AnImcInspectionItemMapper anImcInspectionItemMapper;

    @Autowired
    private AnImcInspectionTaskMapper anImcInspectionTaskMapper;

    @Autowired
    private IAnImcInspectionTaskService anImcInspectionTaskService;

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
     * @param imcAddInspectionItemDto 巡检任务子项
     * @return 结果
     */
    @Override
    public ImcAddInspectionItemDto insertAnImcInspectionItem(ImcAddInspectionItemDto imcAddInspectionItemDto, LoginAuthDto user)
    {
        //Bean拷贝
        AnImcInspectionItem anImcInspectionItem = new AnImcInspectionItem();
        BeanUtils.copyProperties(imcAddInspectionItemDto,anImcInspectionItem);
        UpdateInfoUtil.setInsertInfo(anImcInspectionItem,user);
        //获取巡检任务子项对应的巡检任务
        Long taskId = anImcInspectionItem.getInspectionTaskId();
        AnImcInspectionTask anImcInspectionTask = anImcInspectionTaskMapper.selectByPrimaryKey(taskId);
        if(null == anImcInspectionItem){
            throw new BusinessException("巡检任务不存在");
        }

        //新建巡检任务子项
        anImcInspectionItem.setStatus(ItemStatusEnum.WAITING_FOR_MAINTAINER.getStatusNum());
        //新建任务子项时根据点位数量创建需要提交的巡检单据
        Integer count = imcAddInspectionItemDto.getCount();
        if(null != count && count>0){
            // 使用子项中的Result字段来保存待填写的巡检表单数，均填写完成后写入finish字符串表示已完成
            anImcInspectionItem.setResult(String.valueOf(count));
            //TODO
        }

        //巡检单据创建完成之后，再创建巡检任务子项
        anImcInspectionItemMapper.insert(anImcInspectionItem);
        //附件上传处理
        //TODO

        //bean更新
        BeanUtils.copyProperties(anImcInspectionItem,imcAddInspectionItemDto);
        return imcAddInspectionItemDto;
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
    @Override
    public int deleteAnImcInspectionItemById(Long id)
    {
        return anImcInspectionItemMapper.deleteAnImcInspectionItemById(id);
    }

    /**
     * 修改巡检任务子项的状态
     * @param imcItemChangeStatusDto
     * @param user
     * @return
     */
    @Override
    public ImcItemChangeStatusDto modifyImcItemStatus(ImcItemChangeStatusDto imcItemChangeStatusDto, LoginAuthDto user){
        imcItemChangeStatusDto.setStatusMsg(ItemStatusEnum.getStatusMsg(imcItemChangeStatusDto.getStatus()));
        Long itemId = imcItemChangeStatusDto.getItemId();
        int status = imcItemChangeStatusDto.getStatus();
        AnImcInspectionItem anImcInspectionItem = new AnImcInspectionItem();
        anImcInspectionItem.setId(itemId);
        anImcInspectionItem.setStatus(status);
        UpdateInfoUtil.setModifyInfo(anImcInspectionItem,user);
        ItemStatusEnum itemStatusEnum = ItemStatusEnum.getEnum(status);
        switch (itemStatusEnum){
            case IN_THE_INSPECTION:
                anImcInspectionItem.setActualStartTime(new Date(System.currentTimeMillis()));
                if(anImcInspectionItemMapper.modifyItemStatus(anImcInspectionItem)>0){
                    logger.info("巡检任务子项状态更新为巡检中");
                }else {
                    throw new BusinessException("巡检任务子项状态更新失败");
                }
                break;
            case INSPECTION_OVER:
                anImcInspectionItem.setActualFinishTime(new Date(System.currentTimeMillis()));
                if(anImcInspectionItemMapper.modifyItemStatus(anImcInspectionItem)>0){
                    logger.info("巡检任务子项状态更新为巡检结束");
                    //建立附件与子项的关系
                    //TODO
                    //检查巡检任务是否已经完成
                    Long taskId = this.selectAnImcInspectionItemById(anImcInspectionItem.getId()).getInspectionTaskId();
                    if(anImcInspectionTaskService.isTaskFinish(taskId)){
                        //如果该巡检子项对应的巡检任务中全部的任务子项均已完成
                        //则修改对应的巡检任务状态为已完成
                        ImcTaskChangeStatusDto imcTaskChangeStatusDto = new ImcTaskChangeStatusDto();
                        imcTaskChangeStatusDto.setTaskId(taskId);
                        imcTaskChangeStatusDto.setStatus(TaskStatusEnum.WAITING_FOR_CONFIRM.getStatusNum());
                        anImcInspectionTaskService.modifyTaskStatus(imcTaskChangeStatusDto,user);
                    }
                }else {
                    throw new BusinessException("巡检任务子项状态更新失败");
                }
                break;
            default:
                if(anImcInspectionItemMapper.modifyItemStatus(anImcInspectionItem)>0){
                    logger.info("巡检任务子项状态更新");
                }else {
                    throw new BusinessException("巡检任务子项状态更新失败");
                }
                break;
        }
        return imcItemChangeStatusDto;
    }
}
