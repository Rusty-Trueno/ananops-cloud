package com.ananops.system.manager;

import com.ananops.system.domain.SysUser;
import com.ananops.system.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created By ChengHao On 2020/6/3
 */
@Slf4j
@Component
@Transactional(rollbackFor = Exception.class)
public class UserManager {
    @Resource
    private SysUserMapper sysUserMapper;

    public void register(final SysUser sysUser) {
        log.info("注册用户. user={}", sysUser);
        sysUserMapper.insertUser(sysUser);
//        UacRoleUser uacRoleUser = new UacRoleUser();
//        uacRoleUser.setUserId(sysUser.getId());
//        uacRoleUser.setRoleId(GlobalConstant.Spc.SPC_MANAGER_ROLE_ID);
//        uacRoleUserMapper.insertSelective(uacRoleUser);
    }
}
