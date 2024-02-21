package com.lison.springcloudservice.service;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class TestSentinelMessage3ServiceImplBlockHandlerClass { //注意这里必须使用static修饰方法
    public static String blockHandler(BlockException ex) {
        return "接口被限流或者降级了...";
    }
}
