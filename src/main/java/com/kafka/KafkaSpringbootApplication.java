package com.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaSpringbootApplication.class, args);
		System.out.println("Kafka publisher is running!!!");
	}

}
