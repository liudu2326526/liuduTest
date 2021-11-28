package com.liudu.protobuf;


import com.google.protobuf.InvalidProtocolBufferException;
import com.monetization.adx.proto.common.AdxRequestMessageLogOuterClass;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.ByteBufferDeserializer;
import org.apache.kafka.common.serialization.BytesDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Properties;

public class ProtobufTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        Properties props = new Properties();
        props.put("bootstrap.servers",
//                "172.29.1.42:9092,172.29.1.47:9092,172.29.1.31:9092,172.29.1.77:9092,172.29.1.119:9092"
                "monetization-ad-log-kafka-01.meta.aws:9092," +
                        "monetization-ad-log-kafka-02.meta.aws:9092," +
                        "monetization-ad-log-kafka-03.meta.aws:9092," +
                        "monetization-ad-log-kafka-04.meta.aws:9092," +
                        "monetization-ad-log-kafka-05.meta.aws:9092"
        );
        // group.id，指定了消费者所属群组
        props.put("group.id", "liudu-test");
        props.put("key.deserializer", StringDeserializer.class);
        props.put("value.deserializer", ByteArrayDeserializer.class);
        props.put("enable.auto.commit", "true");
//        props.put("auto.commit.interval.ms", "1000");
//        props.put("session.timeout.ms", "30000");
        props.put("auto.offset.reset", "earliest");
        KafkaConsumer<String, byte[]> consumer = new KafkaConsumer<String, byte[]>(props);
        consumer.subscribe(Arrays.asList("prod-adx-detail-log"));
        while (true) {
            // 100 是超时时间（ms），在该时间内 poll 会等待服务器返回数据
            ConsumerRecords<String, byte[]> records = consumer.poll(100);

            for (ConsumerRecord<String, byte[]> record : records) {
                byte[] value = record.value();
                System.out.println("offset==="+record.offset());
//                AdxRequestMessageLogOuterClass.AdxRequestMessageLog adxRequestMessageLog = AdxRequestMessageLogOuterClass.AdxRequestMessageLog.parseFrom(value);

//                System.out.println(adxRequestMessageLog.toString());
            }
        }
    }
}
