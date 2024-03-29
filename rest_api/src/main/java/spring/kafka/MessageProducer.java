package spring.kafka;

import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import spring.kafka.event.NotificationEvent;

@Component
public class MessageProducer {

    private final KafkaTemplate<String, NotificationEvent> kafkaTemplate;

    public MessageProducer(KafkaTemplate<String, NotificationEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, NotificationEvent notificationEvent) {
        kafkaTemplate.send(topic, notificationEvent);
    }

//     try{
//        logger.info("Sending message: {} to topic: {}", message, topicName);
//        ListenableFuture<SendResult<String, String>> future  = kafkaProducer.send(topicName, message);
//
//
//        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
//
//            @Override
//            public void onSuccess(SendResult<String, String> result) {
//
//                System.out.println("Message Sent  " + result.getRecordMetadata().timestamp());
//                //your logic for the success scenario
//            }
//
//            @Override
//            public void onFailure(Throwable ex) {
//
//                System.out.println(" sending failed  ");
//// your logic if failed
//                throw new RuntimeException("Kafka Failed");
//
//
//            }
//        });
//
//    } catch (Exception e){
//        logger.error("Error sending message: {} to topic: {}", message, topicName, e);
//        throw new RuntimeException("Exception occurred");
//    }

}