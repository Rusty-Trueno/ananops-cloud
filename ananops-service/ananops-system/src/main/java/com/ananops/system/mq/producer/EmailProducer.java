/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：EmailProducer.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.system.mq.producer;

import com.alibaba.fastjson.JSON;
import com.ananops.RedisKeyUtil;
import com.ananops.base.constant.AliyunMqTopicConstants;
import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.provider.model.domain.MqMessageData;
import com.ananops.provider.model.dto.PcSendEmailRequest;
import com.ananops.system.enums.UacEmailTemplateEnum;
import com.ananops.system.exceptions.UacBizException;
import com.ananops.system.service.UacFreeMarkerService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;

/**
 * The class Email producer.
 *
 * @author ananops.com@gmail.com
 */
@Component
@Slf4j
public class EmailProducer {
	@Resource
	private UacFreeMarkerService uacFreeMarkerService;

	/**
	 * Send email mq.
	 *
	 * @param emailSet          the email set
	 * @param emailTemplateEnum the email template enum
	 * @param param             the param
	 */
	public MqMessageData sendEmailMq(Set<String> emailSet, UacEmailTemplateEnum emailTemplateEnum, AliyunMqTopicConstants.MqTagEnum tagEnum, Map<String, Object> param) {
		log.info("pcSendEmailRequest - 发送邮件MQ. emailSet={}, param={}", emailSet, param);
		String msgBody;
		try {

			Preconditions.checkArgument(emailTemplateEnum != null, "邮箱模板信息不存在");
			PcSendEmailRequest request = new PcSendEmailRequest();

			String templateLocation = emailTemplateEnum.getLocation();
			String text = uacFreeMarkerService.getTemplate(param, templateLocation);

			request.setText(text);
			request.setTo(emailSet);
			request.setSubject(emailTemplateEnum.getSubject());

			msgBody = JSON.toJSONString(request);
		} catch (Exception e) {
			log.error("发送邮件验证码 smsMessage转换为json字符串失败", e);
			throw new UacBizException(ErrorCodeEnum.UAC10011021);
		}
		String topic = tagEnum.getTopic();
		String tag = tagEnum.getTag();
		String key = RedisKeyUtil.createMqKey(topic, tag, emailSet.toString(), msgBody);
		return new MqMessageData(msgBody, topic, tag, key);
	}
}
