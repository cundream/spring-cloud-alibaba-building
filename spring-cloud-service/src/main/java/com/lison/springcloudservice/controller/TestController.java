package com.lison.springcloudservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: com.lison.springbootbuilding.controller-> TestController
 * @description:
 * @author: Lison
 * @createDate: 2023-09-23 12:03
 */
@RestController
@RefreshScope//只需要在需要动态读取配置的类上添加此注解就可以
public class TestController {

    @Value("${config.appName}")
    private String configName;
    @Value("${config.env}")
    private String env;


    @Autowired
    private ConfigurableApplicationContext applicationContext;
    @Value("${server.port}")
    private String serverProd;

    @Value("${demo}")
    private String demo;

    @GetMapping(value = "/getServerProd")
    public String getServerPort(){
        return "Hello Nacos Discovery"+serverProd +"---demo"+demo;
    }

    @GetMapping(value = "/getConfigName")
    public String getConfigName(){
        return applicationContext.getEnvironment().getProperty("config.appName");
    }


    @GetMapping(value = "/getConfigNames")
    public String getConfigNames(){
        return configName+"环境"+env;
    }


}
