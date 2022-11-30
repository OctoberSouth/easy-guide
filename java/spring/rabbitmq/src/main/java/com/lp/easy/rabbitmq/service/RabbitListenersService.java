package com.lp.easy.rabbitmq.service;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;


@RabbitListener(queues = "hello-java-queue")
@Component
public class RabbitListenersService {

    /**
     * 原生消息体 Message
     * 可选 object 可以接收消息实体
     * 可选 channel 消息通道
     *
     * @param message
     */
//    @RabbitListener(queues = "hello-java-queue")
    @RabbitHandler
    public void getMessageListener(Message message, Object object, Channel channel) {
        System.out.println("接收到的消息内容：" + message);
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        // 签收消息 multiple 是否批量签收
        try {
            if (deliveryTag % 2 == 0) {
                //签收
                channel.basicAck(deliveryTag, false);
            } else {
                //拒绝 requeue 是否丢弃消息
                channel.basicNack(deliveryTag, false, true);
            }
        } catch (IOException e) {
            // 网络中断
        }
    }
}
