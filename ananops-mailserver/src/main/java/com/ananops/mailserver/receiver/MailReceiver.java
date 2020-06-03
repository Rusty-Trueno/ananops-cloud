package com.ananops.mailserver.receiver;

import com.ananops.system.domain.MailConstants;
import com.ananops.system.domain.SysUser;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;

@Component
@Slf4j
public class MailReceiver {
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    MailProperties mailProperties;
    @Autowired
    TemplateEngine templateEngine;
    @Autowired
    StringRedisTemplate redisTemplate;

    @RabbitListener(queues = MailConstants.MAIL_QUEUE_NAME) //监听
    public void handler(Message message, Channel channel) throws IOException {
        SysUser sysUser = (SysUser) message.getPayload();
        log.info("sysUse:{}",sysUser);
        MessageHeaders headers = message.getHeaders();
        Long tag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        String msgId = headers.get("id").toString();
        //处理幂等性问题
        if (redisTemplate.opsForHash().entries("mail_log").containsKey(msgId)) {
            //redis包含改key,说明该消息已被消费过
            log.info(msgId+":消息已被消费过");
            channel.basicAck(tag,false); //手动确认消息已被消费
            return;
        }
        //收到消息，发送邮件
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg);
        try {
            helper.setTo(sysUser.getEmail());
            helper.setFrom(mailProperties.getUsername());
            helper.setSubject("欢迎加入安安运维");
            helper.setSentDate(new Date());

            Context context = new Context();
            context.setVariable("name", sysUser.getUserName());
            context.setVariable("companyName", sysUser.getCompanyName());
            context.setVariable("phoneNumber", sysUser.getPhonenumber());
            String mail = templateEngine.process("mail", context);
            helper.setText(mail, true);
            javaMailSender.send(msg);
            //发送成功，把msgId存储到redis中
            redisTemplate.opsForHash().put("mail_log",msgId,"ananops");
            channel.basicAck(tag,false);
            log.info(msgId+":邮件发送成功");
        } catch (MessagingException e) {
            //发送失败，手动消息添加到消费队列中
            channel.basicNack(tag,false,true);
            e.printStackTrace();
            log.error("邮件发送失败："+e.getMessage());
        }
    }
}
