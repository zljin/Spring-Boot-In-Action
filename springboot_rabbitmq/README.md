# springboot_rabbitmq

## 安装RabbitMq

```
docker run -id -p 5672:5672 -p 15672:15672 --name dev-rabbitmq rabbitmq:management

http://localhost:15672/#/   # guest guest

```

## 基本概念

publisher：生产者

exchange个：交换机,负责消息路由. 有三种类型分别代表三种消息模型: direct,fanout,topic

queue：队列，存储消息

virtualHost：虚拟主机，隔离不同租户的exchange、queue、消息的隔离

consumer：消费者

```
publisher ---> {(exchange ---> queue) virtualHost} ---> consumer
publisher ---> {(exchange ---> queue) virtualHost} ---> consumer
publisher ---> {(exchange ---> queue) virtualHost} ---> consumer
```

## springboot集成

> https://github.com/zljin/Spring-Boot-In-Action/tree/master/springboot_rabbitmq

```yml
spring:
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest
    publisher-returns: true # 消息正确抵达队列进行回调
    template:
      mandatory: true  # 强制要求消息必须被路由到一个队列。如果消息无法路由，会触发一个回调，通常用于处理无法投递的消息
    listener:
      simple:
        acknowledge-mode: manual # 手动签收
```

## Exchange的三种消息模型

direct
点对点模式，消息中的路由键如果和 Binding 中的 bindingkey 一致，交换器就将消息发到对应的队列中。

fanout
广播模式，每个发到 fanout 类型交换器的消息都会分到所有绑定的队列上去

topic (企业常用,重点关注此即可)

主题模式，Topic Exchange 可通过通配符路由键匹配映射多个Queue

## 消息可靠性发送与接收消费

发送： https://github.com/zljin/Spring-Boot-In-Action/blob/master/springboot_rabbitmq/src/main/java/com/zljin/config/MyRabbitConfig.java#L55

接收： https://github.com/zljin/Spring-Boot-In-Action/blob/master/springboot_rabbitmq/src/main/java/com/zljin/listener/OrderCloseListener.java#L27

## 死信队列用法

> 死信队列即延迟队列，给消息设置ttl,然后去往delayqueue,常用于如订单竣工场景，需要等待客户30秒后支付，是一个异步且需要等待的业务场景常用此技术实现

https://github.com/zljin/Spring-Boot-In-Action/blob/master/springboot_rabbitmq/src/main/java/com/zljin/config/MyOrderConfig.java#L21