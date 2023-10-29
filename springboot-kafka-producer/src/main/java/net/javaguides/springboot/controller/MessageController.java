package net.javaguides.springboot.controller;

import net.javaguides.springboot.Service.KafkaProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class MessageController {


    @Value("${spring.kafka.topic1.name}")
    private String topic1Name;


    @Value("${spring.kafka.topic2.name}")
    private String topic2Name;

    @Value("${spring.kafka.topic3.name}")
    private String topic3Name;

    @Value("${spring.kafka.topic4.name}")
    private String topic4Name;
    private KafkaProducer kafkaProducer;

    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    // http:localhost:8080/kafka/publish?message=hello world
    @GetMapping("/publish/topics")
    public ResponseEntity<String> publish(@RequestParam("message") String message){
        kafkaProducer.sendMessage(message,topic1Name);
        kafkaProducer.sendMessage(message,topic3Name);
        kafkaProducer.sendMessage(message,topic4Name);
        kafkaProducer.sendMessage("11111",topic2Name);
       // kafkaProducer.sendMessageWithCallback(message,topic1Name);
        kafkaProducer.sendMessageWithCallback(message,"notopic");
        return ResponseEntity.ok("Message sent to the topic");
    }

}
