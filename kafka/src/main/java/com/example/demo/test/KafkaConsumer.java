package com.example.demo.test;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private static Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "test", groupId = "1111")
    public void listen(ConsumerRecord<?, ?> record) {
        logger.info("主题:{} 内容：{}", record.topic(), record.value());
    }

    @KafkaListener(topics = "test", groupId = KafkaProducer.TOPIC_GROUP1, autoStartup = "true")
    public void topic_test(ConsumerRecord<?, ?> record) {
        logger.info("topic_test 消费了： Topic:" + record.topic() + ",Message:" + record.value());
    }

    @KafkaListener(topics = "test", groupId = KafkaProducer.TOPIC_GROUP2)
    public void topic_test1(ConsumerRecord<?, ?> record) {

        logger.info("topic_test 消费了： Topic:" + record.topic() + ",Message:" + record.value());
    }
}
