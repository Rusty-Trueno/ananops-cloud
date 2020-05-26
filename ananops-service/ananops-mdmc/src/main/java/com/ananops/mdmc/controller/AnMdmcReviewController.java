package com.ananops.mdmc.controller;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.mdmc.dto.MdmcAddReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.ananops.common.core.domain.R;
import com.ananops.common.core.controller.BaseController;
import com.ananops.mdmc.domain.AnMdmcReview;
import com.ananops.mdmc.service.IAnMdmcReviewService;

import javax.annotation.Resource;

/**
 * 评价工单 提供者
 *
 * @author ananops
 * @date 2020-05-25
 */
@RestController
@RequestMapping("评价工单")
@Api("评价工单")
public class AnMdmcReviewController extends BaseController
{

    @Resource
    private IAnMdmcReviewService anMdmcReviewService;

    /**
     * 查询工单评价表
     */
    @ApiOperation(value = "根据工单id查询工单评价表")
    @GetMapping("getReview/{id}")
    public AnMdmcReview get(@PathVariable("id") Long id)
    {
        return anMdmcReviewService.selectAnMdmcReviewById(id);

    }

    /**
     * 查询评价工单列表
     */
    @ApiOperation(value = "查询评价工单列表")
    @GetMapping("list")
    public R list(AnMdmcReview anMdmcReview)
    {
        startPage();
        return result(anMdmcReviewService.selectAnMdmcReviewList(anMdmcReview));
    }


    /**
     * 新增保存评价工单
     */
    @ApiOperation(value = "新增保存评价工单")
    @PostMapping("save")
    public R addSave(@RequestBody MdmcAddReviewDto reviewDto)
    {
        LoginAuthDto loginAuthDto=new LoginAuthDto();
        return R.data(anMdmcReviewService.insertAnMdmcReview(reviewDto,loginAuthDto));
    }

    /**
     * 修改保存评价工单
     */
    @ApiOperation(value = "修改保存评价工单")
    @PostMapping("update")
    public R editSave(@RequestBody AnMdmcReview anMdmcReview)
    {
        return toAjax(anMdmcReviewService.updateAnMdmcReview(anMdmcReview));
    }

    /**
     * 删除工单评价表
     */
    @ApiOperation(value = "删除工单评价表")
    @PostMapping("remove")
    public R remove(String ids)
    {
        return toAjax(anMdmcReviewService.deleteAnMdmcReviewByIds(ids));
    }

}
