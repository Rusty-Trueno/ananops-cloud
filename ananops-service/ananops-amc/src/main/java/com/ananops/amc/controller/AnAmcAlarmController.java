package com.ananops.amc.controller;

import com.ananops.common.auth.annotation.HasPermissions;
import com.ananops.common.core.dto.LoginAuthDto;
import lombok.extern.slf4j.Slf4j;
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
import com.ananops.amc.domain.AnAmcAlarm;
import com.ananops.amc.service.IAnAmcAlarmService;

/**
 * 告警 提供者
 *
 * @author ananops
 * @date 2020-05-20
 */
@RestController
@RequestMapping("alarm")
@Api("告警")
@Slf4j
public class AnAmcAlarmController extends BaseController {

    @Autowired
    private IAnAmcAlarmService anAmcAlarmService;

    /**
     * 查询告警表
     */
    @ApiOperation(value = "查询告警表")
    @GetMapping("get/{id}")
    public AnAmcAlarm get(@PathVariable("id") Long id) {
        return anAmcAlarmService.selectAnAmcAlarmById(id);

    }

    /**
     * 查询告警列表
     */
    @ApiOperation(value = "查询告警列表")
    @GetMapping("list")
    public R list(AnAmcAlarm anAmcAlarm) {
        startPage();
        return result(anAmcAlarmService.selectAnAmcAlarmList(anAmcAlarm));
    }


    /**
     * 新增保存告警
     */
    @HasPermissions("amc:alarm:add")
    @ApiOperation(value = "新增保存告警")
    @PostMapping("save")
    public R addSave(@RequestBody AnAmcAlarm anAmcAlarm) {
        LoginAuthDto user = getLoginAuthDto();
        log.info("当前用户信息为：user={}", user);
        return toAjax(anAmcAlarmService.insertAnAmcAlarm(anAmcAlarm, user));
    }

    /**
     * 修改保存告警
     */
    @HasPermissions("amc:alarm:edit")
    @ApiOperation(value = "修改保存告警")
    @PostMapping("update")
    public R editSave(@RequestBody AnAmcAlarm anAmcAlarm) {
        LoginAuthDto user = getLoginAuthDto();
        log.info("当前用户信息为：user={}", user);
        return toAjax(anAmcAlarmService.updateAnAmcAlarm(anAmcAlarm, user));
    }

    /**
     * 删除告警表
     */
    @HasPermissions("amc:alarm:remove")
    @ApiOperation(value = "删除告警表")
    @PostMapping("remove")
    public R remove(String ids) {
        return toAjax(anAmcAlarmService.deleteAnAmcAlarmByIds(ids));
    }


    /**
     * 获取总告警数
     *
     * @return
     */
    @GetMapping("/getAllAlarmCount")
    @ApiOperation(value = "获取总告警数")
    public R getAllAlarmCount(AnAmcAlarm anAmcAlarm) {
        log.info("获取总告警数");
        int count = anAmcAlarmService.getAllAlarmCount(anAmcAlarm);
        return R.data(count);
    }

    @GetMapping("/getDealingCount")
    @ApiOperation(value = "获取待处理告警数")
    public R getDealingCount(AnAmcAlarm anAmcAlarm) {
        int count = anAmcAlarmService.getDealingCount(anAmcAlarm);
        return R.data(count);
    }

    @GetMapping("getUrgencyCount")
    @ApiOperation(value = "获取急需处理告警数")
    public R getUrgencyCount(AnAmcAlarm anAmcAlarm) {
        int count = anAmcAlarmService.getUrgencyCount(anAmcAlarm);
        return R.data(count);
    }

    @GetMapping("/getDealedCount")
    @ApiOperation(value = "获取已处理告警数")
    public R getDealedCount(AnAmcAlarm anAmcAlarm) {
        int count = anAmcAlarmService.getDealedCount(anAmcAlarm);
        return R.data(count);
    }


}
