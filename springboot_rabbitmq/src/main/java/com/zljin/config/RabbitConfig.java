package com.zljin.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息队列配置类
 */
@Configuration
public class RabbitConfig {
    /**
     * 苹果采购消息队列
     */
    @Bean
    public Queue appleQueue() {
        return new Queue("apple-queue");
    }

    /**
     * 香蕉采购消息队列
     */
    @Bean
    public Queue bananaQueue() {
        return new Queue("banana-queue");
    }

    /**
     * 配置交换机
     */
    @Bean
    TopicExchange exchangeTopic() {
        return new TopicExchange("exchange-topic");
    }

    /**
     * 交换机绑定苹果采购消息队列
     */
    @Bean
    Binding bindAppleQueue() {
        return BindingBuilder.bind(appleQueue()).to(exchangeTopic()).with("#.apple.#");
    }

    /**
     * 交换机绑定香蕉采购消息队列
     */
    @Bean
    Binding bindBananaQueue() {
        return BindingBuilder.bind(bananaQueue()).to(exchangeTopic()).with("#.banana.#");
    }
}

