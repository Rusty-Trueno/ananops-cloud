package com.ananops.mdmc.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.common.exception.BusinessException;
import com.ananops.common.utils.bean.BeanUtils;
import com.ananops.common.utils.bean.UpdateInfoUtil;
import com.ananops.mdmc.domain.AnMdmcTaskItem;
import com.ananops.mdmc.dto.*;
import com.ananops.mdmc.enums.MdmcTaskStatusEnum;
import com.ananops.mdmc.mapper.AnMdmcTaskItemMapper;
import com.ananops.mdmc.service.IAnMdmcTaskItemService;
import com.ananops.system.domain.SysRole;
import com.ananops.system.domain.SysUser;
import com.ananops.system.feign.RemoteRoleService;
import com.ananops.system.feign.RemoteUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import com.ananops.mdmc.mapper.AnMdmcTaskMapper;
import com.ananops.mdmc.domain.AnMdmcTask;
import com.ananops.mdmc.service.IAnMdmcTaskService;
import com.ananops.common.core.text.Convert;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * 维修工单Service业务层处理
 *
 * @author ananops
 * @date 2020-05-25
 */
@Service
public class AnMdmcTaskServiceImpl implements IAnMdmcTaskService
{
    @Resource
    private AnMdmcTaskMapper anMdmcTaskMapper;

    @Resource
    private AnMdmcTaskItemMapper anMdmcTaskItemMapper;

    @Resource
    private IAnMdmcTaskItemService itemService;

    @Resource
    private RemoteUserService userService;

    @Resource
    private RemoteRoleService roleService;

    /**
     * 查询维修工单
     *
     * @param id 维修工单ID
     * @return 维修工单
     */
    @Override
    public MdmcTaskDetailDto selectAnMdmcTaskById(Long id)
    {
        MdmcTaskDetailDto taskDetailDto=new MdmcTaskDetailDto();
        AnMdmcTask mdmcTask=anMdmcTaskMapper.selectByPrimaryKey(id);
        if(mdmcTask==null){
            throw new BusinessException("查无此工单");
        }
        taskDetailDto.setMdmcTask(mdmcTask);
        if (mdmcTask.getFacilitatorId()!=null){
            taskDetailDto.setFacInfo(userService.selectSysUserByUserId(mdmcTask.getFacilitatorId()));
        }
        if (mdmcTask.getMaintainerId()!=null){
            taskDetailDto.setEngineerInfo(userService.selectSysUserByUserId(mdmcTask.getMaintainerId()));
        }
        if (mdmcTask.getPrincipalId()!=null){
            taskDetailDto.setPrincipalInfo(userService.selectSysUserByUserId(mdmcTask.getPrincipalId()));
        }
        if (mdmcTask.getUserId()!=null){
            taskDetailDto.setUserInfoDto(userService.selectSysUserByUserId(mdmcTask.getUserId()));
        }
        //todo 调用获取项目信息

        return taskDetailDto;
    }

    /**
     * 查询维修工单列表
     *
     * @param queryDto 维修工单
     * @return 维修工单
     */
    @Override
    public PageInfo selectAnMdmcTaskList(MdmcQueryDto queryDto)
    {
        Long id=queryDto.getId();
        if (id==null){
            throw new BusinessException("用户id不能是空");
        }
        Integer status=queryDto.getStatus();
        List<AnMdmcTask> taskList=new ArrayList<>();
        Integer pageNum=queryDto.getPageNum();
        Integer pageSize=queryDto.getPageSize();
        SysUser user=userService.selectSysUserByUserId(id);
        if (user==null){
            throw new BusinessException("找不到此用户id对应的用户信息");
        }
        List<Long> roleIds=user.getRoleIds();
        if (roleIds==null||roleIds.size()==0){
            throw new BusinessException("该用户没有绑定角色权限");
        }
        Long roleId=roleIds.get(0);
        SysRole role=roleService.selectSysRoleByRoleId(roleId);
        if (role==null){
            throw new BusinessException("根据角色编码查出的角色信息是空");
        }
        String roleCode=role.getRoleKey();
        return status==null ? getListByRole(roleCode,pageNum,pageSize,id):getListByRoleAndStatus(roleCode,pageNum,pageSize,id,status);
    }

    private PageInfo getListByRole(String role,Integer pageNum,Integer pageSize,Long id){
        List<AnMdmcTask> taskList=new ArrayList<>();
        Page page=PageHelper.startPage(pageNum,pageSize);
        switch (role){
            case "fac_leader":taskList=anMdmcTaskMapper.selectByFacId(id);break;
            case "engineer": taskList=anMdmcTaskMapper.selectByMantainerId(id);break;
            case "user_watcher":taskList=anMdmcTaskMapper.selectByUserId(id);break;
            case "user_leader":taskList=anMdmcTaskMapper.selectByBossId(id);break;
            default:taskList=anMdmcTaskMapper.selectByUserId(id);
        }
        PageInfo pageInfo = new PageInfo<>(transform(taskList,id));
        pageInfo.setTotal(page.getTotal());
        pageInfo.setPages(page.getPages());
        pageInfo.setPageNum(page.getPageNum());
        return pageInfo;
    }

    private PageInfo getListByRoleAndStatus(String role,Integer pageNum,Integer pageSize,Long id,Integer status){
        List<AnMdmcTask> taskList=new ArrayList<>();
        Page page=PageHelper.startPage(pageNum,pageSize);
        switch (role){
            case "fac_leader":taskList=anMdmcTaskMapper.selectByFacIdAndStatus(status,id);break;
            case "engineer": taskList=anMdmcTaskMapper.selectByMaintainerIdAndStatus(status,id);break;
            case "user_watcher":taskList=anMdmcTaskMapper.selectByUserIdAndStatus(status,id);break;
            case "user_leader":taskList=anMdmcTaskMapper.selectByBossIdAndStatus(status,id);break;
            default:taskList=anMdmcTaskMapper.selectByUserIdAndStatus(status,id);
        }
        PageInfo pageInfo = new PageInfo<>(transform(taskList,id));
        pageInfo.setTotal(page.getTotal());
        pageInfo.setPages(page.getPages());
        pageInfo.setPageNum(page.getPageNum());
        return pageInfo;
    }

    private List<MdmcTaskDetailDto> transform(List<AnMdmcTask> taskList,Long id){
        List<MdmcTaskDetailDto> taskDetailDtos = new ArrayList<>();
        for(AnMdmcTask task:taskList){
            MdmcTaskDetailDto taskDetailDto=new MdmcTaskDetailDto();
            taskDetailDto.setMdmcTask(task);
            if (task.getFacilitatorId()!=null){
                taskDetailDto.setFacInfo(userService.selectSysUserByUserId(task.getFacilitatorId()));
            }
            if (task.getMaintainerId()!=null){
                taskDetailDto.setEngineerInfo(userService.selectSysUserByUserId(task.getMaintainerId()));
            }
            if (task.getPrincipalId()!=null){
                taskDetailDto.setPrincipalInfo(userService.selectSysUserByUserId(task.getPrincipalId()));
            }
            if (task.getUserId()!=null){
                taskDetailDto.setUserInfoDto(userService.selectSysUserByUserId(task.getUserId()));
            }
            //todo 调用其他模块获取项目信息
            taskDetailDtos.add(taskDetailDto);
        }
        return taskDetailDtos;
    }

    /**
     * 新增维修工单
     *
     * @param mdmcAddTaskDto 维修工单
     * @return 结果
     */
    @Override
    public MdmcAddTaskDto insertAnMdmcTask(MdmcAddTaskDto mdmcAddTaskDto, LoginAuthDto loginAuthDto)
    {
        AnMdmcTask task = new AnMdmcTask();
        BeanUtils.copyProperties(mdmcAddTaskDto,task);
        UpdateInfoUtil.setInsertInfo(task,loginAuthDto);

        task.setStatus(MdmcTaskStatusEnum.ShenHeZhong1.getStatusNum());
        anMdmcTaskMapper.insert(task);
        Long taskId=task.getId();

        //todo 上传文件

        //获取所有的任务子项
        List<MdmcAddTaskItemDto> mdmcAddTaskItemDtoList = mdmcAddTaskDto.getMdmcAddTaskItemDtoList();
        if (mdmcAddTaskItemDtoList!=null){
            mdmcAddTaskItemDtoList.forEach(itemDto->{
                //设置任务子项对应的任务id
                itemDto.setTaskId(taskId);
                //创建新的任务子项，并更新返回结果
                AnMdmcTaskItem item = itemService.insertAnMdmcTaskItem(itemDto,loginAuthDto);
                BeanUtils.copyProperties(item ,itemDto);
            });
            BeanUtils.copyProperties(mdmcAddTaskItemDtoList,mdmcAddTaskDto);
        }

        //更新返回结果
        BeanUtils.copyProperties(task,mdmcAddTaskDto);

        //todo 消息
        return mdmcAddTaskDto;
    }

    /**
     * 上传维修结果
     *
     * @param updateTaskDto 维修工单
     * @return 结果
     */
    @Override
    public AnMdmcTask updateAnMdmcTask(MdmcAddTaskDto updateTaskDto,LoginAuthDto loginAuthDto)
    {
        AnMdmcTask task = new AnMdmcTask();
        BeanUtils.copyProperties(updateTaskDto,task);
        UpdateInfoUtil.setInsertInfo(task,loginAuthDto);
        Long taskId=updateTaskDto.getId();
        if(anMdmcTaskMapper.selectByPrimaryKey(taskId)==null){
            throw new BusinessException("当前工单不存在");
        }
        task.setStatus(MdmcTaskStatusEnum.ShenHeZhong2.getStatusNum());
        if(anMdmcTaskMapper.updateTaskStatus(task)<=0){
            throw new BusinessException("工单更改状态失败");
        }
        anMdmcTaskMapper.updateByPrimaryKeySelective(task);

        AnMdmcTask task1=anMdmcTaskMapper.selectByPrimaryKey(taskId);
        BeanUtils.copyProperties(task1,task);

        //todo 文件
        //todo 巡检
        //todo 消息
        return task;
    }

    /**
     * 删除维修工单对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnMdmcTaskByIds(String ids)
    {
        return anMdmcTaskMapper.deleteAnMdmcTaskByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除维修工单信息
     *
     * @param id 维修工单ID
     * @return 结果
     */
    @Override
    public int deleteAnMdmcTaskById(Long id)
    {
        Example example=new Example(AnMdmcTaskItem.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("taskId",id);
        List<AnMdmcTaskItem> itemList=anMdmcTaskItemMapper.selectByExample(example);
        for (AnMdmcTaskItem item:itemList){
            Long itemId=item.getId();
            anMdmcTaskItemMapper.deleteByPrimaryKey(itemId);
        }
        return anMdmcTaskMapper.deleteByPrimaryKey(id);
    }

    @Override
    public MdmcChangeStatusDto modifyTaskStatus(MdmcChangeStatusDto changeStatusDto, LoginAuthDto loginAuthDto) {
        Integer status=changeStatusDto.getStatus();
        if(status==null){
            throw new BusinessException("状态不能是空");
        }
        changeStatusDto.setStatusMsg(MdmcTaskStatusEnum.getStatusMsg(status));
        Long taskId=changeStatusDto.getTaskId();
        if(anMdmcTaskMapper.selectByPrimaryKey(taskId)==null){
            throw new BusinessException("查无此工单");
        }
        AnMdmcTask task=new AnMdmcTask();
        task.setStatus(status);
        task.setId(changeStatusDto.getTaskId());
        UpdateInfoUtil.setModifyInfo(task,loginAuthDto);
        if(anMdmcTaskMapper.updateTaskStatus(task)<=0){
            throw new BusinessException("工单更改状态失败");
        }
        if(MdmcTaskStatusEnum.getEnum(status)==MdmcTaskStatusEnum.Reject1){
            anMdmcTaskMapper.rejectByFac(taskId);
        }
        if (MdmcTaskStatusEnum.getEnum(status)==MdmcTaskStatusEnum.JieDan2){
            anMdmcTaskMapper.rejectByMan(taskId);
        }
        changeStatusDto.setTask(anMdmcTaskMapper.selectByPrimaryKey(taskId));
        //todo 巡检引起工单状态改变
        //todo 消息
        return changeStatusDto;
    }

    @Override
    public AnMdmcTask dispatch(MdmcDispatchDto dispatchDto) {
        if (anMdmcTaskMapper.dispatchFacOrEng(dispatchDto)<=0){
            throw new BusinessException("更新失败");
        }
        return anMdmcTaskMapper.selectByPrimaryKey(dispatchDto.getId());
    }
}
