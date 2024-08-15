package hhplus.ecommerce.kafka;

import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
@EmbeddedKafka(partitions = 1, topics = { "test-topic" })
class KafkaIntegrationTest {


    private static final DockerComposeContainer<?> DOCKER_COMPOSE = new DockerComposeContainer<>(new File("/docker-compose.yml"))
            .withExposedService("kafka", 9092)
            .withExposedService("zookeeper", 2181);

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        String kafkaBootstrapServers = DOCKER_COMPOSE.getServiceHost("kafka", 9092)
                + ":" + DOCKER_COMPOSE.getServicePort("kafka", 9092);
        registry.add("spring.kafka.bootstrap-servers", kafkaBootstrapServers::toString);

        String zookeeperConnect = DOCKER_COMPOSE.getServiceHost("zookeeper", 2181)
                + ":" + DOCKER_COMPOSE.getServicePort("zookeeper", 2181);
        registry.add("spring.kafka.zookeeper-connect", zookeeperConnect::toString);
    }


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final BlockingQueue<String> records = new LinkedBlockingQueue<>();

    @Test
    void testKafkaMessageSendAndReceive() {
        //given
        String message = "Test message";

        //when
        kafkaTemplate.send("test-topic", message);

        //then
        Awaitility.await().atMost(10, TimeUnit.SECONDS).untilAsserted(() -> {
            String received = records.poll(10, TimeUnit.SECONDS);
            assertThat(received).isEqualTo(message);
        });
    }

}