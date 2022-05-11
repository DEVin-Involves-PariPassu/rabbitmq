package com.devinhouse.consumer.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TestConsumer {

    /*
        Listener para fila do tipo default exchange
     */
    @RabbitListener(queues = "teste")
    public void receiveMessage(String msg) throws InterruptedException {
        System.out.println("[x] Received '" + msg + "'");
        doWork(msg);
    }

    private void doWork(String msg) throws InterruptedException {
        for (char ch: msg.toCharArray()) {
            if(ch == '.') {
                Thread.sleep(1000);
            }
        }
    }

    @RabbitListener(queues = "entrega")
    public void atualizarRotas(String msg) {
        System.out.println("Serviço de entrega: " + msg);
    }

    @RabbitListener(queues = "preferencias")
    public void atualizarPreferencias(String msg) {
        System.out.println("Serviço de preferências: " + msg);
    }

}
