package dc.flink;

import org.apache.flink.api.common.serialization.SimpleStringEncoder;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.contrib.streaming.state.RocksDBStateBackend;
import org.apache.flink.core.fs.Path;
import org.apache.flink.runtime.state.filesystem.FsStateBackend;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.filesystem.StreamingFileSink;
import org.apache.flink.streaming.api.functions.sink.filesystem.bucketassigners.DateTimeBucketAssigner;
import org.apache.flink.streaming.api.functions.sink.filesystem.rollingpolicies.OnCheckpointRollingPolicy;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;


public class DataToObs {
    private static final Logger LOG = LoggerFactory.getLogger(DataToObs.class);

    public static void main(String[] args) throws Exception {
        LOG.info("Start Kafka2OBS Flink Streaming Source Java Demo.");
        ParameterTool params = ParameterTool.fromArgs(args);
        LOG.info("Params: " + params.toString());

        // Kafka连接地址
        String bootstrapServers;
        // Kafka消费组
        String kafkaGroup;
        // Kafka topic
        String kafkaTopic;
        // 消费策略，只有当分区没有Checkpoint或者Checkpoint过期时，才会使用此配置的策略；
        //          如果存在有效的Checkpoint，则会从此Checkpoint开始继续消费
        // 取值有： LATEST,从最新的数据开始消费，此策略会忽略通道中已有数据
        //         EARLIEST,从最老的数据开始消费，此策略会获取通道中所有的有效数据
        String offsetPolicy;
        // OBS文件输出路径，格式obs://ak:sk@obsEndpoint/bucket/path
        String outputPath;
        // Checkpoint输出路径，格式obs://ak:sk@obsEndpoint/bucket/path
        String checkpointPath;
        // 测试内网
//        bootstrapServers = params.get("bootstrap.servers", "172.18.203.57:9092");
        // 生产内网
//        bootstrapServers = params.get("bootstrap.servers", "172.18.203.79:9092");
        // 测试公网
//        bootstrapServers = params.get("bootstrap.servers", "120.25.157.50:9092");
        // 生产公网
        bootstrapServers = params.get("bootstrap.servers", "120.25.208.63:9092,120.25.179.177:9092,47.107.233.126:9092");
        kafkaGroup = params.get("group.id", "dli_test1");
        kafkaTopic = params.get("topic", "t_dc_report_data_ods");
        offsetPolicy = params.get("offset.policy", "latest");
//        offsetPolicy = params.get("offset.policy", "earliest");
//        outputPath = params.get("output.path", "obs://D4X34IUUCG7PRDHPZEN5:m4Wb41ZYQvJ5AVon7XnQ4T1MpsGIusXeZOzoYk1e@obs.cn-south-1.myhuaweicloud.com:443/mip-obs-01/data/dev/dli_test1");
        outputPath = params.get("output.path", "file:///C:/Users/admin/IdeaProjects/dc-huawei-data/src/main/resources/output");
//        checkpointPath = params.get("checkpoint.path", "obs://D4X34IUUCG7PRDHPZEN5:m4Wb41ZYQvJ5AVon7XnQ4T1MpsGIusXeZOzoYk1e@obs.cn-south-1.myhuaweicloud.com:443/mip-obs-01/data/dev/dli_ck1");
        checkpointPath = params.get("checkpoint.path", "file:///C:/Users/admin/IdeaProjects/dc-huawei-data/src/main/resources/ck");

        try {
            // 创建执行环境
            StreamExecutionEnvironment streamEnv = StreamExecutionEnvironment.getExecutionEnvironment();
            RocksDBStateBackend rocksDbBackend = new RocksDBStateBackend(new FsStateBackend(checkpointPath), true);
            streamEnv.setStateBackend(rocksDbBackend);
            // 开启Flink CheckPoint配置，开启时若触发CheckPoint，会将Offset信息同步到Kafka
            streamEnv.enableCheckpointing(10 * 1000);
            // 设置两次checkpoint的最小间隔时间
            streamEnv.getCheckpointConfig().setMinPauseBetweenCheckpoints(10 * 1000);
            // 设置checkpoint超时时间
            streamEnv.getCheckpointConfig().setCheckpointTimeout(60 * 1000);
            // 设置checkpoint最大并发数
            streamEnv.getCheckpointConfig().setMaxConcurrentCheckpoints(1);
            // 设置作业取消时保留checkpoint
            streamEnv.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);

            // Source: 连接kafka数据源
            Properties properties = new Properties();
            properties.setProperty("bootstrap.servers", bootstrapServers);
            properties.setProperty("group.id", kafkaGroup);
            properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, offsetPolicy);
            String topic = kafkaTopic;

            // 创建kafka consumer
            FlinkKafkaConsumer<String> kafkaConsumer = new FlinkKafkaConsumer<>(topic, new SimpleStringSchema(), properties);
            /**
             * 从 Kafka brokers 中的 consumer 组（consumer 属性中的 group.id 设置）提交的偏移量中开始读取分区。
             * 如果找不到分区的偏移量，那么将会使用配置中的 auto.offset.reset 设置。
             * 详情 https://ci.apache.org/projects/flink/flink-docs-release-1.13/zh/docs/connectors/datastream/kafka/
             */
            kafkaConsumer.setStartFromGroupOffsets();

            //将kafka 加入数据源
            DataStream<String> stream = streamEnv.addSource(kafkaConsumer).setParallelism(1).disableChaining();

//            DataStreamSource<String> stream = streamEnv.addSource(new SimpleStringGenerator());

            // 创建文件输出流
            final StreamingFileSink<String> sink = StreamingFileSink
                    // 指定文件输出路径与行编码格式
                    .forRowFormat(new Path(outputPath), new SimpleStringEncoder<String>("UTF-8"))
                    // 指定文件输出路径批量编码格式，以parquet格式输出
                    //.forBulkFormat(new Path(outputPath), ParquetAvroWriters.forGenericRecord(schema))
                    // 指定自定义桶分配器
                    .withBucketAssigner(new DateTimeBucketAssigner<>())
                    // 指定滚动策略
                    .withRollingPolicy(OnCheckpointRollingPolicy.build()).build();

            // Add sink for DIS Consumer data source
            stream.addSink(sink).disableChaining().name("obs");

            stream.print();
            streamEnv.execute();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
