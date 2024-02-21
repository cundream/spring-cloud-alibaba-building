package com.lison.springcloudservice.service;

public class TestSentinelMessage3ServiceImplFallbackClass { //注意这里必须使用static修饰方法
    public static String fallback(Throwable throwable) {
        return "接口发生异常了...";
    }
}
