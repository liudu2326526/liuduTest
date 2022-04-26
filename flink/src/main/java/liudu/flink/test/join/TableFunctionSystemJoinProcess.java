package liudu.flink.test.join;

import avro.shaded.com.google.common.collect.Lists;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.List;
import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Schema;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/**
 * @author liudu
 * @title: TableFunctionSystemJoinProcess
 * @projectName liuduTest
 * @date 2022/4/26下午4:19
 */
public class TableFunctionSystemJoinProcess {

  public static void main(String[] args) {
    final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
    env.setRuntimeMode(RuntimeExecutionMode.STREAMING);
    env.setParallelism(1);
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
    StreamTableEnvironment tEnv = StreamTableEnvironment.create(env);

    // 左表
    List<Tuple3<Long, String, Timestamp>> orderData = Lists.newArrayList();
    orderData.add(new Tuple3<>(2L, "Euro", new Timestamp(2000L)));
    orderData.add(new Tuple3<>(1L, "US Dollar", new Timestamp(3000L)));
    orderData.add(new Tuple3<>(50L, "Yen", new Timestamp(4000L)));
    orderData.add(new Tuple3<>(3L, "Euro", new Timestamp(5000L)));
    orderData.add(new Tuple3<>(5L, "Euro", new Timestamp(9000L)));

    //注意这里虽然使用了assignTimestampsAndWatermarks也设置了Watermark，但是在本例中并没有用到，
    //因为 LATERAL TABLE 语法只需要Timestamp提取eventtime。
    DataStream<Tuple3<Long, String, Timestamp>> orderStream = env.fromCollection(orderData)
        .assignTimestampsAndWatermarks(
            WatermarkStrategy
                .<Tuple3<Long, String, Timestamp>>forBoundedOutOfOrderness(Duration.ofSeconds(10000))
                .withTimestampAssigner((event, timestamp) -> event.f2.getTime())
        );

    Table orderTable = tEnv.fromDataStream(orderStream, Schema.newBuilder()
        .columnByExpression("rowtime", "CAST(f2 AS TIMESTAMP(3))")
        .watermark("rowtime", "SOURCE_WATERMARK()")
        .build());

    tEnv.createTemporaryView("Orders", orderTable);

    orderTable.printSchema();//only for debug
    tEnv.from("Orders").execute().print(); // only for debug

    List<Tuple3<String, Long, Timestamp>> rateHistoryData = Lists.newArrayList();
    rateHistoryData.add(new Tuple3<>("US Dollar", 102L, new Timestamp(1000L)));
    rateHistoryData.add(new Tuple3<>("Euro", 114L, new Timestamp(1000L)));
    rateHistoryData.add(new Tuple3<>("Yen", 1L, new Timestamp(1000L)));
    rateHistoryData.add(new Tuple3<>("Euro", 116L, new Timestamp(5000L)));
    rateHistoryData.add(new Tuple3<>("Euro", 119L, new Timestamp(7000L)));

    DataStream<Tuple3<String, Long, Timestamp>> rateStream = env.fromCollection(rateHistoryData)
        .assignTimestampsAndWatermarks(
            WatermarkStrategy
                // here  <Tuple3< String, Long, Timestamp> is need for using withTimestampAssigner
                .<Tuple3<String, Long, Timestamp>>forBoundedOutOfOrderness(Duration.ofSeconds(1000))
                .withTimestampAssigner((event, timestamp) -> event.f2.getTime())
        );

    Table rateTable = tEnv.fromDataStream(
        rateStream,
        Schema.newBuilder()
            .columnByExpression("rowtime", "CAST(f2 AS TIMESTAMP(3))")
            .watermark("rowtime", "SOURCE_WATERMARK()")
            .primaryKey("f0")
            .build());

    tEnv.createTemporaryView("RatesHistory", rateTable);
    rateTable.printSchema();//only for debug
    tEnv.from("RatesHistory").execute().print();

    String sqlQuery2 =
        "SELECT o.f1 as currency, o.f0 as amount, o.rowtime, r.f1 as rate, " +
            " o.f0 * r.f1 as amount_sum " +
            "from " +
            " Orders AS o " +
            " JOIN RatesHistory FOR SYSTEM_TIME AS OF o.rowtime AS r " +
            "ON o.f1 = r.f0";
    tEnv.sqlQuery(sqlQuery2).execute().print();

  }

}
