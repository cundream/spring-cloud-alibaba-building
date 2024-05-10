package com.lison.springcloudservice.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

/**
 * @className: com.lison.springcloudservice.service-> TestSentinelMessage3ServiceImpl
 * @description:
 * @author: Lison
 * @createDate: 2024-02-19 15:32
 */
@Service
public class TestSentinelMessage3ServiceImpl {
        int i = 0;

        @SentinelResource(
                value = "message",
                blockHandler = "blockHandler",//指定发生BlockException时进入的方法
                fallback = "fallback"//指定发生Throwable时进入的方法
        )
        public String message() {
            i++;
            if (i % 3 == 0) {
                throw new RuntimeException();
            }
            return "message";
        }

        //BlockException时进入的方法
        public String blockHandler(BlockException ex) {
            return "接口被限流或者降级了...";
        }

        //Throwable时进入的方法
        public String fallback(Throwable throwable) {
            return "接口发生异常了...";
        }


}
