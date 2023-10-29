
package net.javaguides.springboot.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.topic1.name}")
    private String topicName;

    @Value("${spring.kafka.topic2.name}")
    private String topic2Name;

    @Bean
    public NewTopic javaguidesTopic(){
        return TopicBuilder.name(topicName)
                .build();
    }

    @Bean
    public NewTopic javaguidesJsonTopic(){
        return TopicBuilder.name(topic2Name)
                .build();
    }
}
