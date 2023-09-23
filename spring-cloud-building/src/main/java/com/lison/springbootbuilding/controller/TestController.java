package com.lison.springbootbuilding.controller;

import com.lison.springbootbuilding.fegin.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * @className: com.lison.springbootbuilding.controller-> TestController
 * @description:
 * @author: 李村 200900681
 * @createDate: 2023-09-23 12:03
 */
@RestController
public class TestController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ITestService iTestService;

    @GetMapping(value = "/naocs/consumer")
    public String getServerPort(){

        return iTestService.getServerPort();

    }
}
