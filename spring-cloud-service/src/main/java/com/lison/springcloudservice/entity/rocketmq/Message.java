package com.lison.springcloudservice.entity.rocketmq;

import lombok.Data;

import java.util.Date;

/**
 * @className: com.lison.springcloudservice.entity.rocketmq-> Message
 * @description:
 * @author: Lison
 * @createDate: 2024-04-20 16:53
 */
@Data
public class Message {
    private String msgId;
    private String msgTopic;
    private String msgTag;
    private String msgKeys;
    private String msgBody;
    private String msgType;
    private String msgRetryId;
    private String msgRetryTopic;
    private Date createTime;
}

