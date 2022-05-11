package com.devinhouse.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
//@EnableScheduling
public class ProducerApplication implements CommandLineRunner {

	@Autowired
	private RabbitTemplate rabbitTemplate;


	AtomicInteger dots = new AtomicInteger(0);

	AtomicInteger count = new AtomicInteger(0);

	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Exemplo de envio para fila default
//		for(int i = 0; i < 20; i++) {
//			rabbitTemplate.convertAndSend("teste", "Hello " + i);
//		}


		// Exemplo de envio para fila do tipo fanout
//		rabbitTemplate.convertAndSend("usuarios", null, "Novo endereço: São Paulo");
//
//		Scanner scanner = new Scanner(System.in);
//
//		while(true) {
//			System.out.println("Digite a proxima mensagem");
//			String mensagem = scanner.nextLine();
//			rabbitTemplate.convertAndSend("usuarios", null, mensagem);
//			System.out.println("Mensagem enviada");
//		}


		rabbitTemplate.convertAndSend("cores", "orange", "5-laranja");
		rabbitTemplate.convertAndSend("cores", "black", "2-preta");
		rabbitTemplate.convertAndSend("cores", "green", "3-verde");
	}

	//@Scheduled(fixedDelay = 1000, initialDelay = 500)
	public void send() {
		StringBuilder builder = new StringBuilder();
		if(dots.getAndIncrement() == 4) {
			dots.set(1);
		}
		for(int i = 0; i < dots.get(); i++) {
			builder.append(".");
		}
		builder.append(count.incrementAndGet());
		String message = builder.toString();
		rabbitTemplate.convertAndSend("teste", message);
		System.out.println("[x] Sent'" + message + "'");
	}
}
