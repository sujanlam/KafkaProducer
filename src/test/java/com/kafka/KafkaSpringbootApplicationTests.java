package com.kafka;

import com.kafka.dto.Customer;
import com.kafka.services.KafkaMessagePublisher;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.kafka.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class KafkaSpringbootApplicationTests {

	static DockerImageName myImage = DockerImageName.parse("confluentinc/cp-kafka:latest").asCompatibleSubstituteFor("apache/kafka");

	@Container
    static KafkaContainer kafka = new KafkaContainer(myImage);


	@DynamicPropertySource
	public static void initKafkaProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
	}

	@Autowired
	private KafkaMessagePublisher publisher;

	@Test
	public void testSendEventsToTopic() {
		publisher.sendEventsToTopic(new Customer(263, "test_user", "test@gmail.com", "3232323"));
		await().pollInterval(Duration.ofSeconds(3)).atMost(10, SECONDS).untilAsserted(() -> {
			//asserts
		});
	}

}
