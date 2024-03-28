package example.mail.listener;

import example.mail.service.ExampleMailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MailTopicListener {

    Logger logger = LoggerFactory.getLogger(MailTopicListener.class);

    private final ExampleMailSender mailSender;

    public MailTopicListener(ExampleMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @KafkaListener(
            topics = "testTopic"
    )
    public void listener(String data) {
        logger.info("Listener recieved: " + data);
        mailSender.sendMail("mail.com", data);
    }
}
