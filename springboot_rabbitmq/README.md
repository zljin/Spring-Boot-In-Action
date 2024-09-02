# springboot_rabbitmq

## 安装RabbitMq

```
docker run -id -p 5672:5672 -p 15672:15672 --name dev-rabbitmq rabbitmq:management

http://localhost:15672/#/   # guest guest

```

## 基本概念

publisher：生产者

exchange个：交换机，负责消息路由

queue：队列，存储消息

virtualHost：虚拟主机，隔离不同租户的exchange、queue、消息的隔离

consumer：消费者

```
publisher ---> {(exchange ---> queue) virtualHost} ---> consumer
publisher ---> {(exchange ---> queue) virtualHost} ---> consumer
publisher ---> {(exchange ---> queue) virtualHost} ---> consumer
```

## springboot配置

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

## 消息模型

常用发布订阅的Topic Exchange 主题模式

## 消息可靠性发送与接收消费

## 死信队列

MyOrderConfig.orderDelayQueue