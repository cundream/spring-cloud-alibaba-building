package com.lison.springcloudservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: com.lison.springcloudservice.controller-> TestGatewayController
 * @description:
 * @author: Lison
 * @createDate: 2024-02-22 15:18
 */
@RestController
public class TestGatewayController {
    @RequestMapping(value = "/gateway/test")
    public String test(String code) {
        return "{'name':'网关测试','age':'123456'}";
    }
}
