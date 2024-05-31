package com.lison.springcloudservice.controller;

import com.lison.springcloudservice.service.ITestService;
import com.lison.springcloudservice.service.TestGlobalTransServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @className: com.lison.springcloudservice.controller-> TestSeataController
 * @description:
 * @author: Lison
 * @createDate: 2024-05-27
 */

@RestController
@RequestMapping(value = "/test/")
public class TestSeataController {
    @Resource
    private TestGlobalTransServiceImpl testGlobalTransService;
    @GetMapping("/seataTrans")
    public String testSeataTrans() throws Exception {
        testGlobalTransService.testTrans();
        return "success";
    }
}
