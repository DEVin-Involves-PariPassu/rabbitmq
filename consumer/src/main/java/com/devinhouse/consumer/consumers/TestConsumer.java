package com.devinhouse.consumer.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TestConsumer {

    @RabbitListener(queues = "teste")
    public void receiveMessage(String msg) {

        System.out.println("message: " + msg);
    }


}
