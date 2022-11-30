package com.lp.easy.rabbitmq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * RabbitAutoConfiguration
 *
 * @RabbitListener 监听消息
 */
@EnableRabbit
@SpringBootApplication
public class RabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqApplication.class, args);
    }

}
