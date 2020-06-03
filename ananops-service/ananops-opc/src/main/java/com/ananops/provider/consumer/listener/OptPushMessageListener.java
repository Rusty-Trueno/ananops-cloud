
package com.ananops.provider.consumer.listener;

import com.ananops.PublicUtil;
import com.ananops.base.constant.AliyunMqTopicConstants;
import com.ananops.common.mq.MqMessage;
import com.ananops.provider.annotation.MqConsumerStore;
import com.ananops.provider.consumer.MdcTopicConsumer;
import com.ananops.provider.consumer.OptSendEmailTopicConsumer;
import com.ananops.provider.consumer.OptSendSmsTopicConsumer;
import com.ananops.provider.service.MqMessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 消息逻辑处理监听器
 * The class Opt push message listener.
 *
 * @author ananops.com@gmail.com
 */
@Slf4j
@Component
public class OptPushMessageListener implements MessageListenerConcurrently {

	@Resource
	private OptSendSmsTopicConsumer optSendSmsTopicService;
	@Resource
	private OptSendEmailTopicConsumer optSendEmailTopicService;
	@Resource
	private MdcTopicConsumer mdcTopicConsumer;

	@Resource
	private MqMessageService mqMessageService;
	@Resource
	private StringRedisTemplate srt;

	/**
	 * Consume message consume concurrently status.
	 *
	 * @param messageExtList             the message ext list
	 * @param consumeConcurrentlyContext the consume concurrently context
	 *
	 * @return the consume concurrently status
	 */
	@Override
	@MqConsumerStore
	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messageExtList, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
		MessageExt msg = messageExtList.get(0);
		String body = new String(msg.getBody());
		String topicName = msg.getTopic();
		String tags = msg.getTags();
		String keys = msg.getKeys();
		log.info("MQ消费Topic={},tag={},key={}", topicName, tags, keys);
		ValueOperations<String, String> ops = srt.opsForValue();
		// 控制幂等性使用的key
		try {
			MqMessage.checkMessage(body, topicName, tags, keys);
			String mqKV = null;
			if (srt.hasKey(keys)) {
				mqKV = ops.get(keys);
			}
			if (PublicUtil.isNotEmpty(mqKV)) {
				log.error("MQ消费Topic={},tag={},key={}, 重复消费", topicName, tags, keys);
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
			if (AliyunMqTopicConstants.MqTopicEnum.SEND_SMS_TOPIC.getTopic().equals(topicName)) {
				optSendSmsTopicService.handlerSendSmsTopic(body, topicName, tags, keys);
			}
			if (AliyunMqTopicConstants.MqTopicEnum.SEND_EMAIL_TOPIC.getTopic().equals(topicName)) {
				optSendEmailTopicService.handlerSendEmailTopic(body, topicName, tags, keys);
			}
			if (AliyunMqTopicConstants.MqTopicEnum.TPC_TOPIC.getTopic().equals(topicName)) {
				mqMessageService.deleteMessageTopic(body, tags);
			}
			if (AliyunMqTopicConstants.MqTopicEnum.MDC_TOPIC.getTopic().equals(topicName)) {
				mdcTopicConsumer.handlerSendSmsTopic(body, topicName, tags, keys);
			} else {
				log.info("OPC订单信息消 topicName={} 不存在", topicName);
			}
		} catch (IllegalArgumentException ex) {
			log.error("校验MQ message 失败 ex={}", ex.getMessage(), ex);
		} catch (Exception e) {
			log.error("处理MQ message 失败 topicName={}, keys={}, ex={}", topicName, keys, e.getMessage(), e);
			// 如果消息消费失败，例如数据库异常等，扣款失败，发送失败需要重试的场景，
			// 返回下面代码，RocketMQ就认为消费失败。
			return ConsumeConcurrentlyStatus.RECONSUME_LATER;
		}
		//消息消费成功，redis 中存储该消息（幂等，过期时间 10 天）
		ops.set(keys, keys, 10, TimeUnit.DAYS);
		// 业务实现消费回调的时候，当且仅当返回下面代码时，RocketMQ才会认为这批消息是消费完成的
		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	}
}
