package com.lison.springcloudservice.config.rocketmq;

/**
 * @ClassName: MqConstant
 */
public interface MqConstant {
    String NAME_SERVER_ADDRESS = "192.168.0.150:9876;192.168.0.150:9877";

    String TOPIC = "springboot-mq";

    String TOPIC_TAG = "springboot-mq:001001";

    String HEAP_TOPIC = "heap-up-topic";

}

