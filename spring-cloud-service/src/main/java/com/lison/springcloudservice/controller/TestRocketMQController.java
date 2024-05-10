package com.lison.springcloudservice.controller;

import cn.hutool.core.util.IdUtil;
import com.lison.springcloudservice.config.rocketmq.MqConstant;
import com.lison.springcloudservice.entity.rocketmq.Person;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @className: com.lison.springcloudservice.controller-> TestRocketMQController
 * @description:
 * @author: Lison
 * @createDate: 2024-04-20
 */
@RestController
public class TestRocketMQController {


    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    /**
     * 测试发送简单的消息
     * @param messages
     * @return
     */
    @RequestMapping(value = "/rocketmq/sendMessage")
    public String sendMessage(String messages) throws Exception{

        SendResult sendResult = rocketMQTemplate.syncSend(MqConstant.TOPIC_TAG, "我是一个同步简单消息");
        System.out.println(sendResult.getSendStatus());
        System.out.println(sendResult.getMsgId());
        System.out.println(sendResult.getMessageQueue());
        return "";
    }
    @RequestMapping(value = "/rocketmq/testObjectMsg")
    public void testObjectMsg() {
        Person person = new Person();
        person.setUserId(IdUtil.simpleUUID());
        person.setAge(24);
        person.setName("李少谦");

        rocketMQTemplate.syncSend(MqConstant.TOPIC_TAG, person);
    }

    @RequestMapping(value = "/rocketmq/testCollectionMsg")
    public void testCollectionMsg() {

        List<Person> list = new ArrayList<>();
        Person person = new Person();
        person.setUserId(IdUtil.simpleUUID());
        person.setAge(24);
        person.setName("李少谦");

        Person person1 = new Person();
        person1.setUserId(IdUtil.simpleUUID());
        person1.setAge(24);
        person1.setName("李谦钰");
        list.add(person);
        list.add(person1);
        rocketMQTemplate.syncSend(MqConstant.TOPIC_TAG, list);
    }
}
