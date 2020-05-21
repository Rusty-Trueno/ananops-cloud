package com.ananops.common.utils.bean;

import com.ananops.common.core.domain.UpdateInfo;
import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.common.exception.BusinessException;
import com.ananops.common.utils.DateUtils;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * Created by rongshuai on 2020/5/19 18:30
 */
public class UpdateInfoUtil {
    public static void setUpdateInfo(Object pojo, boolean isNew, LoginAuthDto user) {
        if (isNew) {
            setInsertInfo(pojo, user);
        } else {
            setModifyInfo(pojo, user);
        }

    }

    public static void setInsertInfo(Object pojo, LoginAuthDto user) {
        UpdateInfo updateInfo = new UpdateInfo();
        Date nowTime = DateUtils.getNowDate();
        try {
            updateInfo.setCreateBy(user.getUserName());
            updateInfo.setCreatorId(user.getUserId());
            updateInfo.setCreateTime(nowTime);
            updateInfo.setUpdateBy(user.getUserName());
            updateInfo.setLastOperatorId(user.getUserId());
            updateInfo.setUpdateTime(nowTime);
            BeanUtils.copyBeanProp(pojo, updateInfo);
        } catch (Exception e) {
            throw new BusinessException("Bean拷贝异常");
        }
    }

    public static void setModifyInfo(Object pojo, LoginAuthDto user) {
        UpdateInfo updateInfo = new UpdateInfo();
        Date nowTime = DateUtils.getNowDate();
        try {
            updateInfo.setUpdateBy(user.getUserName());
            updateInfo.setLastOperatorId(user.getUserId());
            updateInfo.setUpdateTime(nowTime);
            BeanUtils.copyBeanProp(pojo, updateInfo);

        } catch (Exception e) {
            throw new BusinessException("Bean拷贝异常");
        }
    }
}
