package com.ananops.imc.service.impl;

import java.util.List;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.common.utils.DateUtils;
import com.ananops.common.utils.bean.UpdateInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ananops.imc.mapper.AnImcInspectionReviewMapper;
import com.ananops.imc.domain.AnImcInspectionReview;
import com.ananops.imc.service.IAnImcInspectionReviewService;
import com.ananops.common.core.text.Convert;

/**
 * 巡检任务评论Service业务层处理
 * 
 * @author ananops
 * @date 2020-05-22
 */
@Service
public class AnImcInspectionReviewServiceImpl implements IAnImcInspectionReviewService 
{
    @Autowired
    private AnImcInspectionReviewMapper anImcInspectionReviewMapper;

    /**
     * 查询巡检任务评论
     * 
     * @param id 巡检任务评论ID
     * @return 巡检任务评论
     */
    @Override
    public AnImcInspectionReview selectAnImcInspectionReviewById(Long id)
    {
        return anImcInspectionReviewMapper.selectAnImcInspectionReviewById(id);
    }

    /**
     * 查询巡检任务评论列表
     * 
     * @param anImcInspectionReview 巡检任务评论
     * @return 巡检任务评论
     */
    @Override
    public List<AnImcInspectionReview> selectAnImcInspectionReviewList(AnImcInspectionReview anImcInspectionReview)
    {
        return anImcInspectionReviewMapper.selectAnImcInspectionReviewList(anImcInspectionReview);
    }

    /**
     * 新增巡检任务评论
     * 
     * @param anImcInspectionReview 巡检任务评论
     * @return 结果
     */
    @Override
    public int insertAnImcInspectionReview(AnImcInspectionReview anImcInspectionReview, LoginAuthDto user)
    {
        UpdateInfoUtil.setInsertInfo(anImcInspectionReview,user);
        return anImcInspectionReviewMapper.insert(anImcInspectionReview);
    }

    /**
     * 修改巡检任务评论
     * 
     * @param anImcInspectionReview 巡检任务评论
     * @return 结果
     */
    @Override
    public int updateAnImcInspectionReview(AnImcInspectionReview anImcInspectionReview)
    {
        anImcInspectionReview.setUpdateTime(DateUtils.getNowDate());
        return anImcInspectionReviewMapper.updateAnImcInspectionReview(anImcInspectionReview);
    }

    /**
     * 删除巡检任务评论对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnImcInspectionReviewByIds(String ids)
    {
        return anImcInspectionReviewMapper.deleteAnImcInspectionReviewByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除巡检任务评论信息
     * 
     * @param id 巡检任务评论ID
     * @return 结果
     */
    public int deleteAnImcInspectionReviewById(Long id)
    {
        return anImcInspectionReviewMapper.deleteAnImcInspectionReviewById(id);
    }
}
