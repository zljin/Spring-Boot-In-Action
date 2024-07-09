# spring-boot-kafka

## 快速安装kafka

> docker-compose quick to install

```yml
version: '3'
services:
  zookeeper:
    image: wurstmeister/zookeeper:latest
    container_name: zookeeper
    # always restart
    restart: always
    ports:
      - 2181:2181

  kafka:
    image: wurstmeister/kafka:latest
    container_name: kafka
    ports:
      - 9092:9092
    # host ip
    environment:
      KAFKA_ADVERTISED_HOST_NAME: kafka
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
    # always restart
    restart: always
    volumes:
      - ./docker.sock:/var/run/docker.sock
```

## kafka cheatsheet

```shell
docker exec -it kafka /bin/bash

# topic相关
kafka-topics.sh --help


## 创建topic
kafka-topics.sh --create --topic topicA \
--zookeeper zookeeper:2181 --replication-factor 1 \
--partitions 1


## 查看topic基本信息
kafka-topics.sh --zookeeper zookeeper:2181 \
--describe --topic topicA

## 查看其他topic信息
kafka-topics.sh --zookeeper zookeeper:2181 --list

# 生产消息 
kafka-console-producer.sh --topic=topicA \
--broker-list kafka:9092

# 消费消息
kafka-console-consumer.sh \
--bootstrap-server kafka:9092 \
--from-beginning --topic topicA

/data/kafka_2.11-1.1.0/bin/kafka-console-consumer.sh --bootstrap-server 
platform-kafka.rootcloud.name:9092 --property print.key=true --topic new_device_from_global

-- 6. 查看消费进度
kafka-consumer-groups.sh --bootstrap-server kafka:9092 --describe --group group1

```

