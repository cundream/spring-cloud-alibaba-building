package com.lison.springcloudservice.consumers.rocketmq;

import com.lison.springcloudservice.config.rocketmq.BaseEvent;
import com.lison.springcloudservice.config.rocketmq.TagConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @className: com.lison.springcloudservice.consumers.rocketmq-> HandlerFor001001
 * @description:
 * @author: Lison
 * @createDate: 2024-04-20 16:55
 */
//@Component
@Slf4j
public class HandlerFor001001 {

    @EventListener(condition = "#event.msgTag=='" + TagConstant.CODE_001001 +"'")
    public void execute(BaseEvent event) {
        Object source = event.getSource();
        log.info("事件监听类: tag: {}, msgType: {}, date: {}, data:{}", event.getMsgTag(), event.getMsgType(), event.getDate(), event.getSource());
    }
}
