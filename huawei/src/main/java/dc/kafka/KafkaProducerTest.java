package dc.kafka;


import com.google.common.collect.Lists;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.List;
import java.util.Properties;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

public class KafkaProducerTest {

    static String s1 = "{\"tag\": \"t_dc_content_report_data_ods_tag\", \"data\": {\"_id\": \"633200e21caff55e5999e72e\", \"ctime\": 1661699352, \"source\": 1013, \"article_id\": \"4807577889937254\", \"insert_time\": 1664221410, \"adorable_cnt\": 41373, \"forward_cnt\": 3619, \"comment_cnt\": 2569, \"time\": 1664221410, \"item_id\": \"4807577889937254\", \"type\": 2}}";
    static String s2 = "{\"tag\": \"t_dc_content_report_data_ods_tag\", \"data\": {\"_id\": \"633200e21caff55e5999e72f\", \"ctime\": 1659768080, \"source\": 1013, \"article_id\": \"4799477549106406\", \"insert_time\": 1664221410, \"adorable_cnt\": 0, \"forward_cnt\": 0, \"comment_cnt\": 0, \"time\": 1664221410, \"item_id\": \"4799477549106406\", \"type\": 2}}";
    static String s3 = "{\"tag\": \"t_dc_content_report_data_ods_tag\", \"data\": {\"_id\": \"633200e21caff55e5999e72f\", \"ctime\": 1659768080, \"source\": 1013, \"article_id\": \"4799477549106406\", \"insert_time\": 1664221410, \"adorable_cnt\": 0, \"forward_cnt\": 0, \"comment_cnt\": 0, \"time\": 1664221410, \"item_id\": \"4799477549106406\", \"type\": 2}}";

    public static void main(String[] args) {

//        System.out.println(s1);

        Properties conf = new Properties();
        conf.setProperty(BOOTSTRAP_SERVERS_CONFIG, "139.9.211.253:9092");
        conf.put(ACKS_CONFIG, "all");
        conf.put(KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        conf.put(VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> producer = new KafkaProducer<>(conf);
        String toTopic = "test_liudu";
        List<String> list = Lists.newArrayList(s1, s2, s3);
        String key = null;
        for (String value : list) {
            ProducerRecord<String, String> record = new ProducerRecord<>(toTopic, key, value);
            try {
                // 简单的直接send，在消费的时候，是消费不到的
                //producer.send(record);
                producer.send(record).get();
            } catch (Exception e) {
                System.out.println(e.toString());
            }

        }
    }
}
