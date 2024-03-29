package example.mail.listener;

import example.mail.event.NotificationEvent;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@EmbeddedKafka
@SpringBootTest(properties =
        {"spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}",
                "spring.kafka.consumer.bootstrap-servers = ${spring.embedded.kafka.brokers}"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class NotificationTopicListenerTest {

    @Value("${spring.kafka.topic.name}")
    private String TOPIC_NAME;

    private Producer<String, NotificationEvent> producer;

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @SpyBean
    private NotificationTopicListener notificationTopicListener;

    @Captor
    ArgumentCaptor<NotificationEvent> notificationEventArgumentCaptor;

    @BeforeAll
    void setUp() {
        Map<String, Object> configs = new HashMap<>(KafkaTestUtils.producerProps(embeddedKafkaBroker));
        producer = new DefaultKafkaProducerFactory<>(configs, new StringSerializer(), new JsonSerializer<NotificationEvent>()).createProducer();
    }

    @Test
    void testLogKafkaMessages() {
        NotificationEvent notificationEvent = new NotificationEvent("mail@mail.com", "Hello form producer");
        producer.send(new ProducerRecord<>(TOPIC_NAME, notificationEvent));
        producer.flush();

        verify(notificationTopicListener, timeout(5000).times(1))
                .consumeNotificationEvents(notificationEventArgumentCaptor.capture());

        NotificationEvent event = notificationEventArgumentCaptor.getValue();

        assertNotNull(event);
        assertEquals("mail@mail.com", event.getNotificationReciever());
        assertEquals("Hello form producer", event.getMessage());
    }

    @AfterAll
    void shutdown() {
        producer.close();
    }
}
