package com.ananops.mdmc.service.impl;

import java.util.List;

import com.ananops.common.core.domain.UpdateInfo;
import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.common.exception.BusinessException;
import com.ananops.common.utils.DateUtils;
import com.ananops.common.utils.StringUtils;
import com.ananops.common.utils.bean.UpdateInfoUtil;
import com.ananops.mdmc.domain.AnMdmcTask;
import com.ananops.mdmc.dto.MdmcAddReviewDto;
import com.ananops.mdmc.enums.MdmcTaskStatusEnum;
import com.ananops.mdmc.mapper.AnMdmcTaskMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ananops.mdmc.mapper.AnMdmcReviewMapper;
import com.ananops.mdmc.domain.AnMdmcReview;
import com.ananops.mdmc.service.IAnMdmcReviewService;
import com.ananops.common.core.text.Convert;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * 评价工单Service业务层处理
 *
 * @author ananops
 * @date 2020-05-25
 */
@Service
public class AnMdmcReviewServiceImpl implements IAnMdmcReviewService
{
    @Resource
    private AnMdmcReviewMapper anMdmcReviewMapper;

    @Resource
    private AnMdmcTaskMapper taskMapper;

    /**
     * 查询评价工单
     *
     * @param id 评价工单ID
     * @return 评价工单
     */
    @Override
    public AnMdmcReview selectAnMdmcReviewById(Long id)
    {
        if (taskMapper.selectByPrimaryKey(id)==null){
            throw new BusinessException("查无此工单");
        }
        return anMdmcReviewMapper.selectAnMdmcReviewByTaskId(id);
    }

    /**
     * 查询评价工单列表
     *
     * @param anMdmcReview 评价工单
     * @return 评价工单
     */
    @Override
    public List<AnMdmcReview> selectAnMdmcReviewList(AnMdmcReview anMdmcReview)
    {
        return anMdmcReviewMapper.selectAnMdmcReviewList(anMdmcReview);
    }

    /**
     * 新增评价工单
     *
     * @param reviewDto 评价工单
     * @return 结果
     */
    @Override
    public AnMdmcReview insertAnMdmcReview(MdmcAddReviewDto reviewDto, LoginAuthDto loginAuthDto)
    {
        AnMdmcReview review=new AnMdmcReview();
        BeanUtils.copyProperties(reviewDto,review);
        UpdateInfoUtil.setInsertInfo(review,loginAuthDto);

        Long taskId = review.getTaskId();
        Long userId=review.getUserId();
        if (userId==null){
            throw new BusinessException("用户id不能是空");
        }

        if(taskMapper.selectByPrimaryKey(taskId)==null){
            throw new BusinessException("当前被评价工单不存在");
        }
        if(anMdmcReviewMapper.selectAnMdmcReviewByTaskId(taskId)!=null){
            throw new BusinessException("该工单已经被评价过");
        }
        AnMdmcTask task=taskMapper.selectByPrimaryKey(taskId);
        task.setStatus(MdmcTaskStatusEnum.WanCheng.getStatusNum());
        if(taskMapper.updateTaskStatus(task)<=0){
            throw new BusinessException("工单状态更改失败");
        }
        anMdmcReviewMapper.insert(review);
        return review;
    }

    /**
     * 修改评价工单
     *
     * @param anMdmcReview 评价工单
     * @return 结果
     */
    @Override
    public int updateAnMdmcReview(AnMdmcReview anMdmcReview)
    {
        return anMdmcReviewMapper.updateAnMdmcReview(anMdmcReview);
    }

    /**
     * 删除评价工单对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnMdmcReviewByIds(String ids)
    {
        return anMdmcReviewMapper.deleteAnMdmcReviewByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除评价工单信息
     *
     * @param id 评价工单ID
     * @return 结果
     */
    public int deleteAnMdmcReviewById(Long id)
    {
        return anMdmcReviewMapper.deleteAnMdmcReviewById(id);
    }
}
