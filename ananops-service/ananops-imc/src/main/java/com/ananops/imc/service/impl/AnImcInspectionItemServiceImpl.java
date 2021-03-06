package com.ananops.imc.service.impl;

import java.util.*;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.common.core.service.BaseService;
import com.ananops.common.enums.WsMsgType;
import com.ananops.common.exception.BusinessException;
import com.ananops.common.utils.bean.BeanUtils;
import com.ananops.common.utils.bean.UpdateInfoUtil;
import com.ananops.imc.domain.AnImcInspectionTask;
import com.ananops.imc.dto.*;
import com.ananops.imc.enums.ItemStatusEnum;
import com.ananops.imc.enums.TaskStatusEnum;
import com.ananops.imc.mapper.AnImcInspectionItemLogMapper;
import com.ananops.imc.mapper.AnImcInspectionTaskMapper;
import com.ananops.imc.service.IAnImcInspectionTaskService;
import com.ananops.system.domain.SysUser;
import com.ananops.system.feign.RemoteUserService;
import com.ananops.websocket.dto.MsgDto;
import com.ananops.websocket.feign.RemoteWebSocketService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ananops.imc.mapper.AnImcInspectionItemMapper;
import com.ananops.imc.domain.AnImcInspectionItem;
import com.ananops.imc.service.IAnImcInspectionItemService;
import com.ananops.common.core.text.Convert;
import tk.mybatis.mapper.entity.Example;

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

    @Autowired
    private AnImcInspectionItemLogMapper anImcInspectionItemLogMapper;

    @Autowired
    private RemoteUserService remoteUserService;

    @Autowired
    private RemoteWebSocketService remoteWebSocketService;

    /**
     * 查询巡检任务子项
     * 
     * @param id 巡检任务子项ID
     * @return 巡检任务子项
     */
    @Override
    public ImcInspectionItemDto selectAnImcInspectionItemById(Long id)
    {
        AnImcInspectionItem anImcInspectionItem = anImcInspectionItemMapper.selectAnImcInspectionItemById(id);
        if(null == anImcInspectionItem){
            return new ImcInspectionItemDto();
        }
        List<AnImcInspectionItem> imcInspectionItemDtos = new ArrayList<>();
        imcInspectionItemDtos.add(anImcInspectionItem);
        return transform(imcInspectionItemDtos).get(0);
    }

    /**
     * 查询巡检任务子项列表
     * 
     * @param itemQueryDto 巡检任务子项
     * @return 巡检任务子项
     */
    @Override
    public PageInfo selectAnImcInspectionItemList(ItemQueryDto itemQueryDto)
    {
        AnImcInspectionItem imcInspectionItem = new AnImcInspectionItem();
        imcInspectionItem.setInspectionTaskId(itemQueryDto.getTaskId());
        imcInspectionItem.setStatus(itemQueryDto.getStatus());
        imcInspectionItem.setItemName(itemQueryDto.getItemName());
        imcInspectionItem.setMaintainerId(itemQueryDto.getMaintainerId());
        Page page = PageHelper.startPage(itemQueryDto.getPageNum(),itemQueryDto.getPageSize());
        List<AnImcInspectionItem> imcInspectionItems = anImcInspectionItemMapper.selectAnImcInspectionItemList(imcInspectionItem);
        PageInfo pageInfo = new PageInfo<>(transform(imcInspectionItems));
        pageInfo.setTotal(page.getTotal());
        pageInfo.setPages(page.getPages());
        pageInfo.setPageNum(page.getPageNum());
        return pageInfo;
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

        this.handleWsMsgSend(anImcInspectionItem,user);

        return imcAddInspectionItemDto;
    }

    /**
     * 修改巡检任务子项
     * 
     * @param anImcInspectionItem 巡检任务子项
     * @return 结果
     */
    @Override
    public int updateAnImcInspectionItem(AnImcInspectionItem anImcInspectionItem,LoginAuthDto user)
    {
        UpdateInfoUtil.setModifyInfo(anImcInspectionItem,user);
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
        imcItemChangeStatusDto.setLoginAuthDto(user);
        Long itemId = imcItemChangeStatusDto.getItemId();
        int status = imcItemChangeStatusDto.getStatus();
        AnImcInspectionItem anImcInspectionItem = new AnImcInspectionItem();
        UpdateInfoUtil.setModifyInfo(anImcInspectionItem,user);
        anImcInspectionItem.setId(itemId);
        anImcInspectionItem.setStatus(status);
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

        AnImcInspectionItem item = anImcInspectionItemMapper.selectByPrimaryKey(itemId);
        this.handleWsMsgSend(item,user);

        return imcItemChangeStatusDto;
    }

    /**
     * 提交巡检结果相关信息
     * @param itemResultDto
     * @param user
     * @return
     */
    public ImcItemChangeStatusDto putResultByItemId(ItemResultDto itemResultDto, LoginAuthDto user){
        //增量更新子项实际工作起始时间
        AnImcInspectionItem imcInspectionItem = new AnImcInspectionItem();
        imcInspectionItem.setId(itemResultDto.getItemId());
        imcInspectionItem.setActualStartTime(itemResultDto.getActualStartTime());
        imcInspectionItem.setActualFinishTime(itemResultDto.getActualFinishTime());
        anImcInspectionItemMapper.updateByPrimaryKeySelective(imcInspectionItem);
        //走原接口逻辑
        ImcItemChangeStatusDto imcItemChangeStatusDto = new ImcItemChangeStatusDto();
        BeanUtils.copyProperties(itemResultDto,imcItemChangeStatusDto);
        return this.modifyImcItemStatus(imcItemChangeStatusDto,user);
    }

    /**
     * 根据维修工id查全部维修工已完成的任务
     * @param itemQueryDto
     * @return
     */
    @Override
    public PageInfo getAllFinishedImcItemByMaintainerId(ItemQueryDto itemQueryDto){
        Long maintainerId = itemQueryDto.getMaintainerId();
        if(null != maintainerId){
            Page page = PageHelper.startPage(itemQueryDto.getPageNum(),itemQueryDto.getPageSize());
            List<AnImcInspectionItem> imcInspectionItems = anImcInspectionItemMapper.getAllFinishedImcItemByMaintainerId(maintainerId);
            PageInfo pageInfo = new PageInfo<>(transform(imcInspectionItems));
            pageInfo.setTotal(page.getTotal());
            pageInfo.setPages(page.getPages());
            pageInfo.setPageNum(page.getPageNum());
            return pageInfo;
        }else throw new BusinessException("参数异常");
    }

    /**
     * 查询巡检任务子项的日志
     * @param itemId
     * @return
     */
    @Override
    public List<ItemLogDto> getItemLogs(Long itemId){
        List<ItemLogDto> itemLogDtos = anImcInspectionItemLogMapper.getItemLogs(itemId);
        if(null != itemLogDtos && itemLogDtos.size() > 0){
            itemLogDtos.forEach(itemLog ->{
                itemLog.setStatusMsg(ItemStatusEnum.getStatusMsg(itemLog.getStatus()));
            });
        }
        return itemLogDtos;
    }

    /**
     * 发送websocket消息
     * @param item
     */
    private void handleWsMsgSend(AnImcInspectionItem item,LoginAuthDto user) {
        Long itemId = item.getId();
        Long taskId = item.getInspectionTaskId();
        Integer status = item.getStatus();
        Long maintainerId = item.getMaintainerId();
        AnImcInspectionTask task = anImcInspectionTaskMapper.selectByPrimaryKey(taskId);
        Long principalId = task.getPrincipalId();
        Long facilitatorId = task.getFacilitatorId();
        ItemWsDto itemWsDto = new ItemWsDto();
        MsgDto<ItemWsDto> msgDto = new MsgDto<>();
        itemWsDto.setItemId(itemId);
        itemWsDto.setStatus(status);
        itemWsDto.setStatusMsg(ItemStatusEnum.getStatusMsg(status));
        msgDto.setMsgType(WsMsgType.IMC_ITEM_STATUS.getType());
        msgDto.setMsg(itemWsDto);
        msgDto.setUser(user);
        if (status==2||status==5) {
            //给工程师发消息
            msgDto.setId(String.valueOf(maintainerId));
        } else if (status==1) {
            //给服务商发消息
            msgDto.setId(String.valueOf(facilitatorId));
        } else if (status==3||status==4) {
            //给甲方负责人发消息
            msgDto.setId(String.valueOf(principalId));
        } else {
            throw new BusinessException("查无此状态");
        }
        remoteWebSocketService.createWebsocketMsg(msgDto);
    }

    /**
     * 巡检任务子项转换方法
     * @param imcInspectionItems
     * @return
     */
    @Override
    public List<ImcInspectionItemDto> transform(List<AnImcInspectionItem> imcInspectionItems){
        List<ImcInspectionItemDto> imcInspectionItemDtos = new ArrayList<>();
        Map<Long,String> nameMap = new HashMap<>();
        for(AnImcInspectionItem imcInspectionItem : imcInspectionItems){
            ImcInspectionItemDto imcInspectionItemDto = new ImcInspectionItemDto();
            BeanUtils.copyProperties(imcInspectionItem,imcInspectionItemDto);
            Long maintainerId = imcInspectionItem.getMaintainerId();
            //转换工程师名称
            if(null != maintainerId){
                if(nameMap.containsKey(maintainerId)){
                    imcInspectionItemDto.setMaintainerName(nameMap.get(maintainerId));
                }else{
                    //调用uac查询用户名
                    SysUser user = remoteUserService.selectSysUserByUserId(maintainerId);
                    if (null != user) {
                        nameMap.put(maintainerId,user.getUserName());
                        imcInspectionItemDto.setMaintainerName(user.getUserName());
                    }
                }
            }
            imcInspectionItemDtos.add(imcInspectionItemDto);
        }
        return imcInspectionItemDtos;
    }
}
