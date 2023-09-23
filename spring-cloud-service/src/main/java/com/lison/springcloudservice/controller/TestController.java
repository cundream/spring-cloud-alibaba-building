package com.lison.springcloudservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: com.lison.springbootbuilding.controller-> TestController
 * @description:
 * @author: 李村 200900681
 * @createDate: 2023-09-23 12:03
 */
@RestController
public class TestController {
    @Value("${server.port}")
    private String serverProd;

    @GetMapping(value = "/getServerProd")
    public String getServerPort(){
        return "Hello Nacos Discovery"+serverProd;
    }
}
