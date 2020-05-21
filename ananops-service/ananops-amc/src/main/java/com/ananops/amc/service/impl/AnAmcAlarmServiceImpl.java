package com.ananops.amc.service.impl;

import java.util.List;

import com.ananops.common.annotation.DataScope;
import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.common.utils.DateUtils;
import com.ananops.common.utils.bean.UpdateInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ananops.amc.mapper.AnAmcAlarmMapper;
import com.ananops.amc.domain.AnAmcAlarm;
import com.ananops.amc.service.IAnAmcAlarmService;
import com.ananops.common.core.text.Convert;

import javax.annotation.Resource;

/**
 * 告警Service业务层处理
 *
 * @author ananops
 * @date 2020-05-20
 */
@Service
@Slf4j
public class AnAmcAlarmServiceImpl implements IAnAmcAlarmService {
    @Resource
    private AnAmcAlarmMapper anAmcAlarmMapper;

    /**
     * 查询告警
     *
     * @param id 告警ID
     * @return 告警
     */
    @Override
    public AnAmcAlarm selectAnAmcAlarmById(Long id) {
        return anAmcAlarmMapper.selectAnAmcAlarmById(id);
    }

    /**
     * 查询告警列表
     *
     * @param anAmcAlarm 告警
     * @return 告警
     */
    @DataScope(deptAlias = "alarm", hasCreatorId = "1")
    public List<AnAmcAlarm> selectAnAmcAlarmList(AnAmcAlarm anAmcAlarm) {
        return anAmcAlarmMapper.selectAnAmcAlarmList(anAmcAlarm);
    }

    /**
     * 新增告警
     *
     * @param anAmcAlarm 告警
     * @return 结果
     */
    @Override
    public int insertAnAmcAlarm(AnAmcAlarm anAmcAlarm, LoginAuthDto user) {
        UpdateInfoUtil.setInsertInfo(anAmcAlarm, user);
        anAmcAlarm.setDeptId(user.getDeptId());
        anAmcAlarm.setDeptName(user.getDept().getDeptName());
        return anAmcAlarmMapper.insert(anAmcAlarm);
    }

    /**
     * 修改告警
     *
     * @param anAmcAlarm 告警
     * @return 结果
     */
    @Override
    public int updateAnAmcAlarm(AnAmcAlarm anAmcAlarm, LoginAuthDto user) {
        UpdateInfoUtil.setModifyInfo(anAmcAlarm, user);
        return anAmcAlarmMapper.updateAnAmcAlarm(anAmcAlarm);
    }

    /**
     * 删除告警对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnAmcAlarmByIds(String ids) {
        return anAmcAlarmMapper.deleteAnAmcAlarmByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除告警信息
     *
     * @param id 告警ID
     * @return 结果
     */
    public int deleteAnAmcAlarmById(Long id) {
        return anAmcAlarmMapper.deleteAnAmcAlarmById(id);
    }

    @Override
    @DataScope(deptAlias = "alarm", hasCreatorId = "1")
    public int getAllAlarmCount(AnAmcAlarm anAmcAlarm) {
        return anAmcAlarmMapper.getAllAlarmCount(anAmcAlarm);
    }

    @Override
    @DataScope(deptAlias = "alarm", hasCreatorId = "1")
    public int getDealingCount(AnAmcAlarm anAmcAlarm) {
        return anAmcAlarmMapper.getDealingCount(anAmcAlarm);
    }

    @Override
    @DataScope(deptAlias = "alarm", hasCreatorId = "1")
    public int getUrgencyCount(AnAmcAlarm anAmcAlarm) {
        return anAmcAlarmMapper.getUrgencyCount(anAmcAlarm);
    }

    @Override
    @DataScope(deptAlias = "alarm", hasCreatorId = "1")
    public int getDealedCount(AnAmcAlarm anAmcAlarm) {
        return anAmcAlarmMapper.getDealedCount(anAmcAlarm);
    }
}
