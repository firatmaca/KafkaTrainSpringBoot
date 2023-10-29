package net.javaguides.springboot.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic1.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeTopic1(String message){
        LOGGER.info(String.format("Message received topic1 -> %s", message));
    }

    @KafkaListener(topics = "${spring.kafka.topic2.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeTopic2(String message){
        LOGGER.info(String.format("Message received topic2 -> %s", message));
    }

    @KafkaListener(topics = { "${spring.kafka.topic2.name}", "${spring.kafka.topic3.name}" }, groupId = "${spring.kafka.consumer.group-id}")
    void commonListenerForMultipleTopics(String message) {
        LOGGER.info("MultipleTopicListener - [{}]", message);
    }

    @KafkaListener(topics = "${spring.kafka.topic4.name}")
    @SendTo("${spring.kafka.topic2.name}")
    String listenAndReply(String message) {
        LOGGER.info("ListenAndReply [{}]", message);
        return "This is a reply sent to 'topic2' topic after receiving message at 'topic4' topic";
    }
}
