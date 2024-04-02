package spring.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.kafka.MessageProducer;
import spring.kafka.event.NotificationEvent;

@RestController
@RequestMapping("api/notify")
public class NotificationController {

    @Value("${spring.kafka.topic.name}")
    private String topic;

    private final MessageProducer messageProducer;

    public NotificationController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @PostMapping("/mail")
    public ResponseEntity<String> signup(@Valid @RequestBody NotificationEvent event) {
        messageProducer.sendMessage(topic, event);
        return new ResponseEntity<>("message: \n" + event.getMessage() + "\nsent successfully to reciever: " + event.getNotificationReciever(), HttpStatus.OK);
    }
}
