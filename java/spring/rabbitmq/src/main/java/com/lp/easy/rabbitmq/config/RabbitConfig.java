package com.lp.easy.rabbitmq.config;

import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Configuration
public class RabbitConfig {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * json 消息体
     *
     * @return
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 消息抵达服务器 就会回调
     * 定制RabbitTemplate
     * RabbitConfig 对象创建完成后执行
     */
    @PostConstruct
    public void initRabbitTemplate() {
        // 设置确认回调
        this.rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            /**
             * 回调
             * @param correlationData correlation data for the callback. 当前消息的为关联数据 消息唯一ID
             * @param ack true for ack, false for nack 消息是否成功收到
             * @param cause An optional cause, for nack, when available, otherwise null. 失败原因
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("correlationData==>" + correlationData + "=====ack=>" + ack + "=====cause=>" + cause);
            }
        });
        //设置消息抵达队列的确认回调
        this.rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            /**
             * 消息没有投递给指定队列，就会触发失败回调
             * @param returned the returned message and metadata.
             */
            @Override
            public void returnedMessage(ReturnedMessage returned) {
                System.out.println("returned==>" + returned);
            }
        });
        // 客户端消息确认 默认为自动确认 接收到了消息就自动回复
    }
}
