package com.ananops.mdmc.service.impl;

import java.util.List;
import com.ananops.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ananops.mdmc.mapper.AnMdmcReviewMapper;
import com.ananops.mdmc.domain.AnMdmcReview;
import com.ananops.mdmc.service.IAnMdmcReviewService;
import com.ananops.common.core.text.Convert;

/**
 * 评价工单Service业务层处理
 *
 * @author ananops
 * @date 2020-05-25
 */
@Service
public class AnMdmcReviewServiceImpl implements IAnMdmcReviewService
{
    @Autowired
    private AnMdmcReviewMapper anMdmcReviewMapper;

    /**
     * 查询评价工单
     *
     * @param id 评价工单ID
     * @return 评价工单
     */
    @Override
    public AnMdmcReview selectAnMdmcReviewById(Long id)
    {
        return anMdmcReviewMapper.selectAnMdmcReviewById(id);
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
     * @param anMdmcReview 评价工单
     * @return 结果
     */
    @Override
    public int insertAnMdmcReview(AnMdmcReview anMdmcReview)
    {
        return anMdmcReviewMapper.insert(anMdmcReview);
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
