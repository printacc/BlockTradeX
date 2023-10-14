package cn.test.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    @KafkaListener(topics = "user_consumer",groupId = "my-group-id")
    public void messageListener(ConsumerRecord<?, ?> record) {
        System.out.println("接收到的信息: " + record.value());
    }
}
