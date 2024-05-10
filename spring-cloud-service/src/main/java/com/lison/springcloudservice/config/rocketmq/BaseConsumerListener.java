package com.lison.springcloudservice.config.rocketmq;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.lison.springcloudservice.entity.rocketmq.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @className: com.lison.springcloudservice.config.rocketmq-> BaseConsumerListener
 * @description:
 * @author: Lison
 * @createDate: 2024-04-20 16:53
 */
@Slf4j
//@Component
//@RocketMQMessageListener(topic = "${rocketmq.topic}", consumerGroup = "${rocketmq.consumer.group}")
public class BaseConsumerListener implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener {



    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void onMessage(MessageExt message) {
        String topic = message.getTopic();
        String tag = message.getTags();
        byte[] body = message.getBody();
        String keys = message.getKeys();
        String msgId = message.getMsgId();
        String realTopic = message.getProperty("REAL_TOPIC");
        String originMessageId = message.getProperty("ORIGIN_MESSAGE_ID");

        // 获取重试的次数 失败一次消息中的失败次数会累加一次
        int reconsumeTimes = message.getReconsumeTimes();

        String jsonBody = JSONObject.toJSONString((new String(body)));
        log.info("消息监听类: msgId:{},topic:{}, tag:{}, body:{},keys:{},realTopic:{},originMessageId:{},reconsumeTimes:{}", msgId, topic, tag, jsonBody, keys, realTopic, originMessageId, reconsumeTimes);

        // 布隆过滤器进行去重
//        if (bitMapBloomFilter.contains(keys)) {
//            return;
//        }
//        bitMapBloomFilter.add(keys);

        // 消费者幂等处理: 设计去重表,防止重复消费
        applicationContext.publishEvent(new BaseEvent(tag, jsonBody));
    }

    private Message buildMessage(MessageExt messageExt) {
        Message message = new Message();
        message.setMsgId(messageExt.getMsgId());
        message.setMsgTopic(messageExt.getTopic());
        message.setMsgTag(messageExt.getTags());

        message.setMsgBody(JSONObject.toJSONString((new String(messageExt.getBody()))));


        // 判断是否是重试消息
        String realTopic = messageExt.getProperty("REAL_TOPIC");
        String originMessageId = messageExt.getProperty("ORIGIN_MESSAGE_ID");
        if (StrUtil.isNotBlank(realTopic) && StrUtil.isNotBlank(originMessageId) ) {
            message.setMsgType("2");
            message.setMsgKeys(messageExt.getKeys()+":"+originMessageId+":"+ IdUtil.fastUUID());
        } else {
            message.setMsgType("1");
            message.setMsgKeys(messageExt.getKeys());
        }
        message.setMsgRetryId(originMessageId);
        message.setMsgRetryTopic(realTopic);
        message.setCreateTime(new Date());
        return message;
    }


    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        // 设置最大重试次数
        consumer.setMaxReconsumeTimes(10);
        // 如下，设置其它consumer相关属性
        consumer.setPullBatchSize(16);
    }
}
