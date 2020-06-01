package com.ananops.pmc.service.impl;

import java.util.List;

import com.ananops.common.core.domain.UpdateInfo;
import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.common.utils.DateUtils;
import com.ananops.common.utils.Threads;
import com.ananops.common.utils.bean.UpdateInfoUtil;
import com.ananops.pmc.domain.AnPmcInspectDetail;
import com.ananops.pmc.domain.AnPmcProject;
import com.ananops.pmc.mapper.AnPmcProjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ananops.pmc.mapper.AnPmcInspectTaskMapper;
import com.ananops.pmc.domain.AnPmcInspectTask;
import com.ananops.pmc.service.IAnPmcInspectTaskService;
import com.ananops.common.core.text.Convert;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ananops
 * @date 2020-05-28
 */
@Service
public class AnPmcInspectTaskServiceImpl implements IAnPmcInspectTaskService 
{
    private static final Logger logger = LoggerFactory.getLogger(Threads.class);

    @Resource
    private AnPmcInspectTaskMapper anPmcInspectTaskMapper;

    @Resource
    private AnPmcProjectMapper anPmcProjectMapper;

    @Resource
    private AnPmcInspectDetailServiceImpl anPmcInspectDetailService;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public AnPmcInspectTask selectAnPmcInspectTaskById(Long id)
    {
        return anPmcInspectTaskMapper.selectAnPmcInspectTaskById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param anPmcInspectTask 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<AnPmcInspectTask> selectAnPmcInspectTaskList(AnPmcInspectTask anPmcInspectTask)
    {
        return anPmcInspectTaskMapper.selectAnPmcInspectTaskList(anPmcInspectTask);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param anPmcInspectTask 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertAnPmcInspectTask(AnPmcInspectTask anPmcInspectTask, LoginAuthDto loginAuthDto)
    {
        anPmcInspectTask.setCreateTime(DateUtils.getNowDate());
        UpdateInfoUtil.setInsertInfo(anPmcInspectTask,loginAuthDto);
        if(anPmcInspectTask.getId() == null){//新增巡检任务
            AnPmcProject anPmcProject = anPmcProjectMapper.selectAnPmcProjectById(anPmcInspectTask.getProjectId());
            if(anPmcProject == null){
                //TODO 业务异常处理
                logger.error("不存在此项目！"+anPmcInspectTask.getProjectId());
            }
            anPmcInspectTask.setProjectName(anPmcProject.getProjectName());
            //TODO 生成巡检task id测试
//            anPmcInspectTask.setId(super.generateId());
        }
        return anPmcInspectTaskMapper.insert(anPmcInspectTask);
    }

//    @Override
//    public void saveDevice(PmcInspectTask pmcInspectTask, LoginAuthDto loginAuthDto) {
////        pmcInspectTask.setUpdateInfo(loginAuthDto);
//        if (pmcInspectTask.isNew()) {  //新增巡检任务
//            PmcProject pmcProject = pmcProjectMapper.selectByPrimaryKey(pmcInspectTask.getProjectId());
//            if (pmcProject == null) {
//                throw new PmcBizException(ErrorCodeEnum.PMC10081023, pmcInspectTask.getProjectId());
//            }
//            pmcInspectTask.setProjectName(pmcProject.getProjectName());
//            pmcInspectTask.setId(super.generateId());
//            pmcInspectTaskMapper.insertSelective(pmcInspectTask);
//        } else {                         //更新
//            Integer result = pmcInspectTaskMapper.updateByPrimaryKeySelective(pmcInspectTask);
//            if (result < 1) {
//                throw new PmcBizException(ErrorCodeEnum.PMC10081021, pmcInspectTask.getId());
//            }
//        }
//    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param anPmcInspectTask 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateAnPmcInspectTask(AnPmcInspectTask anPmcInspectTask)
    {
        anPmcInspectTask.setUpdateTime(DateUtils.getNowDate());
        return anPmcInspectTaskMapper.updateAnPmcInspectTask(anPmcInspectTask);
    }

    /**
     * 删除【请填写功能名称】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnPmcInspectTaskByIds(String ids)
    {
        return anPmcInspectTaskMapper.deleteAnPmcInspectTaskByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteAnPmcInspectTaskById(Long id)
    {
        return anPmcInspectTaskMapper.deleteAnPmcInspectTaskById(id);
    }

    @Override
    public List<AnPmcInspectTask> getTasksByProjectId(Long projectId) {
        Example example = new Example(AnPmcInspectTask.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId", projectId);
        return anPmcInspectTaskMapper.selectByExample(example);
    }

    @Override
    public int deleteTaskByProjectId(Long projectId) {
        List<AnPmcInspectTask> anPmcInspectTasks = this.getTasksByProjectId(projectId);
        if (anPmcInspectTasks != null) { //删除级联的任务详情
            for (AnPmcInspectTask anPmcInspectTask : anPmcInspectTasks) {
                anPmcInspectDetailService.deleteDetailByTaskId(anPmcInspectTask.getId());
            }
        }
        Example example = new Example(AnPmcInspectTask.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId", projectId);
        return anPmcInspectTaskMapper.deleteByExample(example);
    }
}
