package com.ananops.mdmc.service;

import com.ananops.mdmc.domain.AnMdmcReview;
import java.util.List;

/**
 * 评价工单Service接口
 *
 * @author ananops
 * @date 2020-05-25
 */
public interface IAnMdmcReviewService
{
    /**
     * 查询评价工单
     *
     * @param id 评价工单ID
     * @return 评价工单
     */
    public AnMdmcReview selectAnMdmcReviewById(Long id);

    /**
     * 查询评价工单列表
     *
     * @param anMdmcReview 评价工单
     * @return 评价工单集合
     */
    public List<AnMdmcReview> selectAnMdmcReviewList(AnMdmcReview anMdmcReview);

    /**
     * 新增评价工单
     *
     * @param anMdmcReview 评价工单
     * @return 结果
     */
    public int insertAnMdmcReview(AnMdmcReview anMdmcReview);

    /**
     * 修改评价工单
     *
     * @param anMdmcReview 评价工单
     * @return 结果
     */
    public int updateAnMdmcReview(AnMdmcReview anMdmcReview);

    /**
     * 批量删除评价工单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnMdmcReviewByIds(String ids);

    /**
     * 删除评价工单信息
     *
     * @param id 评价工单ID
     * @return 结果
     */
    public int deleteAnMdmcReviewById(Long id);
}
