package com.lison.springcloudservice.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

/**
 * @className: com.lison.springcloudservice.service-> TestSentinelMessage3ServiceImpls
 * @description:
 * @author: 李村 200900681
 * @createDate: 2024-02-20 10:48
 */
@Service

public class TestSentinelMessage3ServiceImpls {
    int i = 0;

    @SentinelResource(
            value = "message",
            blockHandlerClass = TestSentinelMessage3ServiceImplBlockHandlerClass.class,
            blockHandler = "blockHandler",
            fallbackClass = TestSentinelMessage3ServiceImplFallbackClass.class,
            fallback = "fallback"
    )
    public String message() {
        i++;
        if (i % 3 == 0) {
            throw new RuntimeException();
        }
        return "message4";
    }
}


