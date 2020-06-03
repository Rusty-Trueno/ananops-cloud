package com.ananops.system.manager;

import com.ananops.system.domain.MailConstants;
import com.ananops.system.domain.SysUser;
import com.ananops.system.domain.MailSendLog;
import com.ananops.system.mapper.SysUserMapper;
import com.ananops.system.service.IMailSendLogService;
import com.ananops.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
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

    public void register(final SysUser sysUser) {
        log.info("注册用户. user={}", sysUser);
        int result = sysUserMapper.insertUser(sysUser);
//        UacRoleUser uacRoleUser = new UacRoleUser();
//        uacRoleUser.setUserId(sysUser.getId());
//        uacRoleUser.setRoleId(GlobalConstant.Spc.SPC_MANAGER_ROLE_ID);
//        uacRoleUserMapper.insertSelective(uacRoleUser);
        if(result == 1){
            SysUser user = sysUserService.selectUserByEmail(sysUser.getEmail());
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
            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME, MailConstants.MAIL_ROUTING_KEY_NAME, user, new CorrelationData(msgId));
        }
    }
}
