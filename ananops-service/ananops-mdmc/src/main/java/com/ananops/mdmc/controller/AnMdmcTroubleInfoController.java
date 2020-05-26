package com.ananops.mdmc.controller;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.mdmc.dto.MdmcAddTroubleInfoDto;
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
import com.ananops.mdmc.domain.AnMdmcTroubletypeGroup;
import com.ananops.mdmc.service.IAnMdmcTroubleInfoService;

import javax.annotation.Resource;

/**
 * 故障类型/故障位置和组织 提供者
 *
 * @author ananops
 * @date 2020-05-25
 */
@RestController
@RequestMapping("mdmcTask")
@Api("关联故障类型、故障位置和组织")
public class AnMdmcTroubleInfoController extends BaseController
{

    @Resource
    private IAnMdmcTroubleInfoService troubleInfoService;

    /**
     * 根据用户id返回故障类型列表和故障位置列表
     */
    @ApiOperation(value = "填写工单时根据不同用户返回故障类型列表和故障位置列表")
    @GetMapping("getTroubleTypeListAndAddressList/{id}")
    public R get(@PathVariable("id") Long id)
    {
        return R.data(troubleInfoService.selectAnMdmcTroubleInfoById(id));

    }

    /**
     * 组织录入故障类型和故障位置
     */
    @ApiOperation(value = "组织录入故障类型和故障位置")
    @PostMapping("saveTroubleTypeAndAddress")
    public R addSave(@RequestBody MdmcAddTroubleInfoDto troubleInfoDto)
    {
        LoginAuthDto loginAuthDto=new LoginAuthDto();
        return R.data(troubleInfoService.insertAnMdmcTroubletypeGroup(troubleInfoDto,loginAuthDto));
    }
    

    /**
     * 删除故障类型
     */
    @ApiOperation(value = "删除故障类型")
    @PostMapping("removeTroubleType/{id}")
    public R removeTroubleType(@PathVariable("id") Long id)
    {
        return toAjax(troubleInfoService.deleteAnMdmcTroubletypeById(id));
    }

    /**
     * 删除故障位置
     */
    @ApiOperation(value = "删除故障位置")
    @PostMapping("removeTroubleAddress/{id}")
    public R removeTroubleAddress(@PathVariable("id") Long id){
        return toAjax(troubleInfoService.deleteAnMdmcTroubleaddressById(id));
    }

}
