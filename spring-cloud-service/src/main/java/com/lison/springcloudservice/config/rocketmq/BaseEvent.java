package com.lison.springcloudservice.config.rocketmq;

import org.springframework.context.ApplicationEvent;

import java.util.Date;

/**
 * @className: com.lison.springcloudservice.config.rocketmq-> BaseEvent
 * @description:
 * @author: Lison
 * @createDate: 2024-04-20 16:52
 */
public class BaseEvent extends ApplicationEvent {

    private static final long serialVersionUID = -114655712312251238L;


    public BaseEvent(Object source) {
        super(source);
    }

    /**
     * 消息tag
     */
    private String msgTag;

    /**
     * 消息类型   0、第一次发送，1、重发
     */
    private String msgType;

    private Date date;

    public BaseEvent(String msgTag, String source) {
        super(source);
        this.msgTag = msgTag;
    }


    public BaseEvent(String msgTag, String source, Date date) {
        super(source);
        this.msgTag = msgTag;
        this.date = date;
    }

    public BaseEvent(String msgTag, String source, String msgType) {
        super(source);
        this.msgTag = msgTag;
        this.msgType = msgType;
    }

    public BaseEvent(String msgTag, String source, String msgType, Date date) {
        super(source);
        this.msgTag = msgTag;
        this.msgType = msgType;
        this.date = date;
    }



    public String getMsgTag() {
        return msgTag;
    }

    public void setMsgTag(String msgTag) {
        this.msgTag = msgTag;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

