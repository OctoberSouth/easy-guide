package com.lp.easy.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;

@SpringBootTest
class RabbitmqApplicationTests {

    @Resource
    private AmqpAdmin amqpAdmin;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    void createExchange() {
        DirectExchange directExchange = new DirectExchange("hello-java-exchange");
        amqpAdmin.declareExchange(directExchange);
    }

    @Test
    void createQueue() {
        Queue queue = new Queue("hello-java-queue");
        amqpAdmin.declareQueue(queue);
    }

    @Test
    void createBinding() {
        Binding binding = new Binding("hello-java-queue", Binding.DestinationType.QUEUE, "hello-java-exchange", "hello.java", new HashMap<>());
        amqpAdmin.declareBinding(binding);
    }

    @Test
    void sendMessage() {
        for (int i = 0; i < 10; i++) {
            this.rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java", "你好啊发生的啊" + i);
        }

    }


}
