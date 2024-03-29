package spring.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.ActiveProfiles;
import spring.kafka.event.NotificationEvent;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@EmbeddedKafka
@SpringBootTest(properties =
        {"spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}",
                "spring.kafka.producer.bootstrap-servers = ${spring.embedded.kafka.brokers}",
        "spring.liquibase.enabled=false"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class MessageProducerTest {

    @Value("${spring.kafka.topic.name}")
    private String TOPIC_NAME;

    private BlockingQueue<ConsumerRecord<String, String>> records;

    private KafkaMessageListenerContainer<String, String> container;

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @Autowired
    private MessageProducer producer;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    void setUp() {
        DefaultKafkaConsumerFactory<String, String> consumerFactory = new DefaultKafkaConsumerFactory<>(KafkaTestUtils.consumerProps("group", "false", embeddedKafkaBroker));
        ContainerProperties containerProperties = new ContainerProperties(TOPIC_NAME);
        container = new KafkaMessageListenerContainer<>(consumerFactory, containerProperties);
        records = new LinkedBlockingQueue<>();
        container.setupMessageListener((MessageListener<String, String>) records::add);
        container.start();
        ContainerTestUtils.waitForAssignment(container, embeddedKafkaBroker.getPartitionsPerTopic());
    }

    @Test
    void testWriteToKafka() throws InterruptedException, IOException {
        NotificationEvent notificationEvent = new NotificationEvent("mail@mail.com", "Hello from producer");
        producer.sendMessage(TOPIC_NAME, notificationEvent);
        ConsumerRecord<String, String> message = records.poll(500, TimeUnit.MILLISECONDS);
        assertNotNull(message);
        NotificationEvent result = objectMapper.readValue(message.value(), NotificationEvent.class);
        assertNotNull(result);
        assertEquals("mail@mail.com", result.getNotificationReciever());
        assertEquals("Hello from producer", result.getMessage());
    }

    @AfterAll
    void tearDown() {
        container.stop();
    }
}
