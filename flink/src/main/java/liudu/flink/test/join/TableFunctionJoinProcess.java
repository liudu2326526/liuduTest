package liudu.flink.test.join;

import static org.apache.flink.table.api.Expressions.$;

import com.google.common.collect.Lists;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.List;
import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/**
 * @author liudu
 * @title: TableFunctionJoinProcess
 * @projectName liuduTest
 * @date 2022/4/26下午3:49
 */
public class TableFunctionJoinProcess {

  public static void main(String[] args) {
    final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
    env.setRuntimeMode(RuntimeExecutionMode.STREAMING);
    env.setParallelism(1);
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
    StreamTableEnvironment tEnv = StreamTableEnvironment.create(env);

    // 左表
    List<Tuple3<Long, String, Timestamp>> orderData = Lists.newArrayList();
    orderData.add(new Tuple3<>(2L, "Euro", new Timestamp(2L)));
    orderData.add(new Tuple3<>(1L, "US Dollar", new Timestamp(3L)));
    orderData.add(new Tuple3<>(50L, "Yen", new Timestamp(4L)));
    orderData.add(new Tuple3<>(3L, "Euro", new Timestamp(5L)));
    orderData.add(new Tuple3<>(5L, "Euro", new Timestamp(9L)));

    //注意这里虽然使用了assignTimestampsAndWatermarks也设置了Watermark，但是在本例中并没有用到，
    //因为 LATERAL TABLE 语法只需要Timestamp提取eventtime。
    DataStream<Tuple3<Long, String, Timestamp>> orderStream = env.fromCollection(orderData)
        .assignTimestampsAndWatermarks(
            WatermarkStrategy
                .<Tuple3<Long, String, Timestamp>>forBoundedOutOfOrderness(Duration.ofSeconds(5))
                .withTimestampAssigner((event, timestamp) -> event.f2.getTime())
        );

    Table orderTable = tEnv.fromDataStream(orderStream,
        $("amount"), $("currency"), $("eventtime").rowtime());// here we use rowtiime()
    tEnv.registerTable("Orders", orderTable);

    orderTable.printSchema();//only for debug
    tEnv.from("Orders").execute().print(); // only for debug

    //右表
    List<Tuple3<String, Long, Timestamp>> rateHistoryData = Lists.newArrayList();
    rateHistoryData.add(new Tuple3<>("US Dollar", 102L, new Timestamp(1L)));
    rateHistoryData.add(new Tuple3<>("Euro", 114L, new Timestamp(1L)));
    rateHistoryData.add(new Tuple3<>("Yen", 1L, new Timestamp(1L)));
    rateHistoryData.add(new Tuple3<>("Euro", 116L, new Timestamp(5L)));
    rateHistoryData.add(new Tuple3<>("Euro", 119L, new Timestamp(7L)));

    DataStream<Tuple3<String, Long, Timestamp>> rateStream = env.fromCollection(rateHistoryData)
        .assignTimestampsAndWatermarks(
            WatermarkStrategy
                // here  <Tuple3< String, Long, Timestamp> is needed for using withTimestampAssigner
                .<Tuple3<String, Long, Timestamp>>forBoundedOutOfOrderness(Duration.ofSeconds(10))
                .withTimestampAssigner((event, timestamp) -> event.f2.getTime())
        );

    Table rateTable = tEnv.fromDataStream(rateStream,
        $("currency"), $("rate"), $("eventime").rowtime());
    tEnv.registerTable("RatesHistory", rateTable);

    rateTable.printSchema();//only for debug
    tEnv.from("RatesHistory").execute().print(); // only for debug

    tEnv.createTemporarySystemFunction(
        "Rates",
        rateTable.createTemporalTableFunction("eventime", "currency"));

    String sqlQuery =
        "SELECT o.eventtime, o.currency, o.amount, r.rate, " +
            " o.amount * r.rate as amount_sum " +
            "from " +
            " Orders AS o, " +
            " LATERAL TABLE (Rates(o.eventtime)) AS r " +
            "WHERE r.currency = o.currency";
    tEnv.sqlQuery(sqlQuery).execute().print();

  }

}
