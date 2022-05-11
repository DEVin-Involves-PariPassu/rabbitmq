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

    // Exemplo para tipo fanout
    @RabbitListener(queues = "entrega")
    public void atualizarRotas(String msg) {
        System.out.println("Serviço de entrega: " + msg);
    }

    // Exemplo para tipo fanout
    @RabbitListener(queues = "preferencias")
    public void atualizarPreferencias(String msg) {
        System.out.println("Serviço de preferências: " + msg);
    }


    @RabbitListener(queues = "QueueCor1")
    public void produzirTintaLaranja(String msg) {
        System.out.println("Maquina 1: Produzindo + " + msg + " latas de tinta na cor laranja");
    }

    @RabbitListener(queues = "QueueCor2")
    public void produzirTintaPretaAndVerde(String msg) {
        System.out.println("Maquina 2: Produzindo + " + msg + " latas de tinta");
    }
}
