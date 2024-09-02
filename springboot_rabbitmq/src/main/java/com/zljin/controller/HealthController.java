package com.zljin.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {

    @Resource
    RabbitTemplate rabbitTemplate;

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.status(200).body("success");
    }

    @GetMapping("/testmq")
    public void test() {
        Map<String,String> msgMap = new HashMap<>();
        msgMap.put("key1","value1");
        // 发送消息 参数分别为：交换机名称、路由键、消息内容
        rabbitTemplate.convertAndSend("exchange-topic", "apple", "苹果来了10斤");
        rabbitTemplate.convertAndSend("exchange-topic", "banana", "香蕉来了5斤");
        rabbitTemplate.convertAndSend("exchange-topic", "apple.banana", "苹果来了8斤;香蕉来了20斤");
        rabbitTemplate.convertAndSend("exchange-topic", "fish", msgMap);
    }

    @GetMapping("/createOrder")
    public void createOrder(){
        Map<String,String> msgMap = new HashMap<>();
        msgMap.put("ordernum","1234566");
        rabbitTemplate.convertAndSend("order-event-exchange", "order.create.order", msgMap);
    }
}
