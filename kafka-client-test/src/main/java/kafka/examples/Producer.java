package kafka.examples;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class Producer {
    private static String bootstrapServers = "localhost:9092";
    private static String topic = "TestTopic";

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        KafkaProducer<Integer, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 10; i++) {
            // 异步发送
            String value = "test " + i;
            producer.send(new ProducerRecord(topic, value), new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    System.out.println(recordMetadata);
                }
            });
        }
        Thread.sleep(100);
        producer.close();
    }
}
