package com.lison.springcloudservice.config.rocketmq;

/**
 * @className: com.lison.springcloudservice.config.rocketmq-> TagConstant
 * @description:
 * @author: Lison
 * @createDate: 2024-04-20 16:56
 */
/**
 * @ClassName: TagConstant
 * @Description:
 * 为消息设置的标志，用于同一主题下区分不同类型的消息。
 * 来自同一业务单元的消息，可以根据不同业务目的在同一主题下设置不同标签。
 * 标签能够有效地保持代码的清晰度和连贯性，并优化RocketMQ提供的查询系统。
 * 消费者可以根据Tag实现对不同子主题的不同消费逻辑，实现更好的扩展性
 */
public interface TagConstant {
    String CODE_001001 = "001001";

}
