package kafka.examples;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class SimpleProducer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9090,localhost:9092,localhost:9093");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        KafkaProducer<Integer, String> producer = new KafkaProducer<>(props);
        // 同步发送
        ProducerRecord<Integer, String> record = new ProducerRecord<>("TestTopic", 1,"test 1");
        RecordMetadata metadata = producer.send(record).get();
        ProducerRecord<Integer, String> record2 = new ProducerRecord<>("TestTopic", 2,"test 2");
        RecordMetadata metadata2 = producer.send(record2).get();
        System.out.println("主题:" + metadata2.topic());
        // key = 1 时 partition = 0   "leader": 103,
        // key = 2 时 partition = 2   "leader": 101,
        System.out.println("分区:" + metadata2.partition());
        System.out.println("偏移:" + metadata2.offset());
        producer.close();
    }
}
