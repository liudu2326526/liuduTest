
package liudu.flink.test.cdc;


import com.alibaba.fastjson.JSONObject;
import com.ververica.cdc.connectors.mysql.MySqlSource;
import com.ververica.cdc.connectors.mysql.table.StartupOptions;
import com.ververica.cdc.debezium.StringDebeziumDeserializationSchema;
import com.ververica.cdc.debezium.DebeziumSourceFunction;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.runtime.state.filesystem.FsStateBackend;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.streaming.connectors.kafka.KafkaSerializationSchema;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * @author liudu
 * @title: FlinkCDC
 * @projectName liuduTest
 * @description: TODO
 * @date 2022/6/24下午2:26
 */
public class FlinkCDC {

  public static void main(String[] args) throws Exception {

    //1.创建执行环境
    StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
    env.setParallelism(1);

    //2.Flink-CDC将读取binlog的位置信息以状态的方式保存在CK,如果想要做到断点续传,需要从Checkpoint或者Savepoint启动程序
    //2.1 开启Checkpoint,每隔60秒钟做一次CK
    env.enableCheckpointing(60 * 1000L);
    //2.2 指定CK的一致性语义
    env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
    //2.3 设置任务关闭的时候保留最后一次CK数据
    env.getCheckpointConfig().enableExternalizedCheckpoints(
        CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
    //2.4 指定从CK自动重启策略
    env.setRestartStrategy(RestartStrategies.fixedDelayRestart(3, 2000L));
    //2.5 设置状态后端
    env.setStateBackend(new FsStateBackend(
        "file:///Users/liudu/IdeaProjects/liuduTest/flink/src/main/resources/flink_cdc"));
    //2.6 设置访问HDFS的用户名
//    System.setProperty("HADOOP_USER_NAME", "atguigu");

    //3.创建Flink-MySQL-CDC的Source
    //initial (default): Performs an initial snapshot on the monitored database tables upon first startup, and continue to read the latest binlog.
    //latest-offset: Never to perform snapshot on the monitored database tables upon first startup, just read from the end of the binlog which means only have the changes since the connector was started.
    //timestamp: Never to perform snapshot on the monitored database tables upon first startup, and directly read binlog from the specified timestamp. The consumer will traverse the binlog from the beginning and ignore change events whose timestamp is smaller than the specified timestamp.
    //specific-offset: Never to perform snapshot on the monitored database tables upon first startup, and directly read binlog from the specified offset.
    DebeziumSourceFunction<String> mysqlSource = MySqlSource.<String>builder()
        .hostname("172.18.203.53")
        .port(3307)
        .username("root")
        .password("ds@2004.456")
//        .databaseList("flink_cdc")
        .tableList(
            "mip.activity_account_config,mip.activity_content_config,mip.activity_platform_config,mip.activity") //可选配置项,如果不指定该参数,则会读取上一个配置下的所有表的数据，注意：指定的时候需要使用"db.table"的方式
//        .startupOptions(StartupOptions.latest())
        .deserializer(new CustomDebeziumDeserializationSchemaString())
        .build();

    //4.使用CDC Source从MySQL读取数据
    DataStreamSource<String> mysqlDS = env.addSource(mysqlSource);

    //5.打印数据
    mysqlDS.print();

    Properties properties = new Properties();
    properties.setProperty("bootstrap.servers",
        "116.63.66.114:9094,116.63.68.191:9094,122.9.100.78:9094");
    properties.setProperty("transaction.timeout.ms", "900000");

    KafkaSerializationSchema<String> serializationSchema = (element, timestamp) -> {
      return new ProducerRecord<>(
          "t_dc_be_cdc", // target topic
          element.getBytes(StandardCharsets.UTF_8)); // record contents
    };

    FlinkKafkaProducer<String> myProducer = new FlinkKafkaProducer<>(
        "t_dc_be_cdc",             // target topic
        serializationSchema,    // serialization schema
        properties,             // producer config
        FlinkKafkaProducer.Semantic.EXACTLY_ONCE); // fault-tolerance

    mysqlDS.addSink(myProducer);

    //6.执行任务
    env.execute();

  }
}
