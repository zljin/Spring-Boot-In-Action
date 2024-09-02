package com.zljin.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建订单时消息会被发送至队列`order.delay.queue`，经过`TTL`的时间后消息会变成死信以`order.release.order`的路由键经交换机转发至队列`order.release.order.queue`，
 * 再通过监听该队列的消息来实现过期订单的处理
 */
@Configuration
public class MyOrderConfig {

    @Bean
    public Queue orderDelayQueue() {
        Map<String, Object> arguments = new HashMap<>();
        //出现dead letter之后将dead letter重新发送到指定exchange
        arguments.put("x-dead-letter-exchange", "order-event-exchange");
        //出现dead letter之后将dead letter重新按照指定的routing-key发送
        arguments.put("x-dead-letter-routing-key", "order.release.order");
        //控制消息的生存时间 60s
        arguments.put("x-message-ttl", 60000);
        return new Queue("order.delay.queue", true, false, false, arguments);
    }

    @Bean
    public Exchange orderEventExchange() {
        return new TopicExchange("order-event-exchange", true, false);
    }

    //创建订单后的路由到orderDelayQueue队列
    @Bean
    public Binding orderCreateOrderBingding() {
        return new Binding("order.delay.queue",
                Binding.DestinationType.QUEUE,
                "order-event-exchange",
                "order.create.order",
                null);
    }

    //过期消息的指定队列
    @Bean
    public Queue orderReleaseOrderQueue() {
        return new Queue("order.release.order.queue", true, false, false);
    }

    @Bean
    public Binding orderReleaseOrderBingding() {
        return new Binding("order.release.order.queue",
                Binding.DestinationType.QUEUE,
                "order-event-exchange",
                "order.release.order",
                null);
    }
}
