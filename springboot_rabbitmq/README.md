# springboot_rabbitmq

## 安装RabbitMq

```
docker run -id -p 5672:5672 -p 15672:15672 --name dev-rabbitmq rabbitmq:management

http://localhost:15672/#/   # guest guest
```

## 基本结构

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

## 消息模型

常用发布订阅的Topic Exchange 主题模式