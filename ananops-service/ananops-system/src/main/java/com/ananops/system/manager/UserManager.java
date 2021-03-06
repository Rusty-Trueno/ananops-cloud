package com.ananops.system.manager;

import com.ananops.common.exception.BusinessException;
import com.ananops.system.domain.MailConstants;
import com.ananops.system.domain.SysUser;
import com.ananops.system.domain.MailSendLog;
import com.ananops.system.domain.SysUserRole;
import com.ananops.system.domain.ActiveUserDto;
import com.ananops.system.mapper.SysUserMapper;
import com.ananops.system.mapper.SysUserRoleMapper;
import com.ananops.system.service.IMailSendLogService;
import com.ananops.system.service.ISysUserService;
import com.ananops.system.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created By ChengHao On 2020/6/3
 */
@Slf4j
@Component
@Transactional(rollbackFor = Exception.class)
public class UserManager {
    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private IMailSendLogService mailSendLogService;

    @Resource
    RabbitTemplate rabbitTemplate;

    @Resource
    ISysUserService sysUserService;

    @Resource
    SysUserRoleMapper sysUserRoleMapper;

    @Resource
    RedisService redisService;

    public void register(final SysUser sysUser,final String para) {
        log.info("注册用户. user={}", sysUser);
        int result = sysUserMapper.insertUser(sysUser);

        if(result == 1){
            SysUser user = sysUserService.selectUserByEmail(sysUser.getEmail());
            //新注册用户角色为服务商管理员
            List<SysUserRole> userRoleList = new ArrayList<>();
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(user.getUserId());
            sysUserRole.setRoleId((long)16);
            userRoleList.add(sysUserRole);
            sysUserRoleMapper.batchUserRole(userRoleList);

            String msgId = UUID.randomUUID().toString();
            MailSendLog mailSendLog = new MailSendLog();
            mailSendLog.setMsgId(msgId);
            mailSendLog.setCreateTime(new Date());
            mailSendLog.setExchange(MailConstants.MAIL_EXCHANGE_NAME);
            mailSendLog.setRouteKey(MailConstants.MAIL_ROUTING_KEY_NAME);
            mailSendLog.setEmpId(user.getUserId());
            //第一次重试时间设置为一分钟后
            mailSendLog.setTryTime(new Date(System.currentTimeMillis() + 1000 * 60 * MailConstants.MSG_TIMEOUT));
            mailSendLogService.insert(mailSendLog);
            ActiveUserDto activeUserDto = new ActiveUserDto();
            activeUserDto.setActiveUrl(para);
            activeUserDto.setSysUser(user);
            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME, MailConstants.MAIL_ROUTING_KEY_NAME, activeUserDto, new CorrelationData(msgId));
        }
    }

    public void activeUser(SysUser sysUser, String activeUserKey) {
        log.info("激活用户. user={}", sysUser);
         int result = sysUserService.changeStatus(sysUser);
        if (result < 1) {
            throw new BusinessException("激活用户失败");
        }

        String msgId = UUID.randomUUID().toString();
        rabbitTemplate.convertAndSend(MailConstants.MAIL_QUEUE_ACTIVE_USER, sysUser, new CorrelationData(msgId));

        // 删除 activeUserToken
        redisService.deleteKey(activeUserKey);
    }
}
