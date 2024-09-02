package com.zljin.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import com.rabbitmq.client.Channel;


import java.io.IOException;
import java.util.Map;

/**
 * 30分钟后检查订单状态是否已经完成
 *
 * 若完成不处理，否则释放库存，关闭订单
 */
@RabbitListener(queues = "order.release.order.queue")
@Service
public class OrderCloseListener {

    @RabbitHandler
    public void listener(Map entity, Channel channel, Message message) throws IOException {
        try{
            System.out.println("订单处理"+entity);
            //手动签收 ok
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }catch (Exception e){
            //手动签收 reject
            channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
        }
    }
}
