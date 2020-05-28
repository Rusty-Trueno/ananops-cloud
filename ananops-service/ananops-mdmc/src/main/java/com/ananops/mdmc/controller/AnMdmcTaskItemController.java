package com.ananops.mdmc.controller;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.mdmc.dto.MdmcAddTaskItemDto;
import com.ananops.mdmc.dto.MdmcQueryDto;
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
import com.ananops.mdmc.domain.AnMdmcTaskItem;
import com.ananops.mdmc.service.IAnMdmcTaskItemService;

/**
 * 维修任务子项 提供者
 *
 * @author ananops
 * @date 2020-05-25
 */
@RestController
@RequestMapping("mdmcTaskItem")
@Api("维修任务子项")
public class AnMdmcTaskItemController extends BaseController
{

    @Autowired
    private IAnMdmcTaskItemService anMdmcTaskItemService;

    /**
     * 查询维修任务子项
     */
    @ApiOperation(value = "根据子项id查看维修任务子项详情")
    @GetMapping("get/{id}")
    public AnMdmcTaskItem get(@PathVariable("id") Long id)
    {
        return anMdmcTaskItemService.selectAnMdmcTaskItemById(id);

    }

    /**
     * 查询维修任务子项列表
     */
    @ApiOperation(value = "根据工单id查询维修任务子项列表")
    @PostMapping("getItemList")
    public R list(@RequestBody MdmcQueryDto queryDto)
    {
        return R.data(anMdmcTaskItemService.selectAnMdmcTaskItemList(queryDto));
    }


    /**
     * 新增保存维修任务子项
     */
    @ApiOperation(value = "新增保存维修任务子项")
    @PostMapping("save")
    public R addSave(@RequestBody MdmcAddTaskItemDto itemDto)
    {
        LoginAuthDto loginAuthDto=getLoginAuthDto();
        return R.data(anMdmcTaskItemService.insertAnMdmcTaskItem(itemDto,loginAuthDto));
    }

    /**
     * 修改保存维修任务子项
     */
    @ApiOperation(value = "修改保存维修任务子项")
    @PostMapping("update")
    public R editSave(@RequestBody MdmcAddTaskItemDto itemDto)
    {
        LoginAuthDto loginAuthDto=getLoginAuthDto();
        return R.data(anMdmcTaskItemService.updateAnMdmcTaskItem(itemDto,loginAuthDto));
    }

    /**
     * 删除维修任务子项
     */
    @ApiOperation(value = "删除维修任务子项")
    @PostMapping("remove/{id}")
    public R remove(@PathVariable("id") Long id)
    {

        return toAjax(anMdmcTaskItemService.deleteAnMdmcTaskItemById(id));
    }

}
