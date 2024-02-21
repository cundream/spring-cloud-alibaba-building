package com.lison.springcloudservice.controller;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.lison.springcloudservice.service.TestSentinelMessage3ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: com.lison.springcloudservice.controller-> TestSentinelController
 * @description:
 * @author: 李村 200900681
 * @createDate: 2024-02-19 09:51
 */
@RestController
public class TestSentinelController {

    @Autowired
    private TestSentinelMessage3ServiceImpl testSentinelMessage3Service;

    @RequestMapping("/sentinel/message1")
    public String message1() {
        testSentinelMessage3Service.message();
        return "message1";
    }
    @RequestMapping("/sentinel/message2")
    public String message2() {
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        testSentinelMessage3Service.message();
        return "message2";
    }

    @RequestMapping("/sentinel/message3")
    @SentinelResource("message3")//注意这里必须使用这个注解标识,热点规则不生效
    public String message3(String name, Integer age) {
        return name + age;
    }


    @RequestMapping(value = "/sentinel/test")
    public String test(String code) {
        long remainder = System.currentTimeMillis() % 2;
        if(remainder == 0 || StringUtils.isNotBlank(code)){
            int i = 10/0;
        }
        return "code";
    }


}
