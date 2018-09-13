package com.aaa.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    //指定主题
    @KafkaListener(topics = "output")
    public void getMessage(ConsumerRecord<?, ?> record){
        //offset是显示当前消费了第多少条消息
        System.out.printf("topic = %s, offset = %d, value = %s \n", record.topic(), record.offset(), record.value());

    }
}
