package example.mail.listener;

import example.mail.event.NotificationEvent;
import example.mail.service.ExampleMailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class NotificationTopicListener {

    Logger logger = LoggerFactory.getLogger(NotificationTopicListener.class);

    private final ExampleMailSender mailSender;

    public NotificationTopicListener(ExampleMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
    )
    public void consumeNotificationEvents(@Payload NotificationEvent event) {
        logger.info("Received a message contains a notification information with reciever {} and message: {}", event.getNotificationReciever(), event.getMessage());
        mailSender.sendMail(event.getNotificationReciever(), event.getMessage());
    }
}
