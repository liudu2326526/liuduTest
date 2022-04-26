package liudu.flink.test;

import java.time.LocalDateTime;
import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Schema;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.connector.ChangelogMode;
import org.apache.flink.types.Row;

/**
 * @author liudu
 * @title: TemporalTablesProcess
 * @projectName liuduTest
 * @date 2022/4/26下午2:28
 */
public class TemporalTablesProcess {

  public static void main(String[] args) throws Exception {
    final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
    env.setRuntimeMode(RuntimeExecutionMode.STREAMING);
    env.setParallelism(1);
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
    StreamTableEnvironment tEnv = StreamTableEnvironment.create(env);

    DataStream<Row> ratesStream = env.fromElements(
        Row.of(LocalDateTime.parse("2021-08-21T09:02:00"), "US Dollar", 102),
        Row.of(LocalDateTime.parse("2021-08-21T09:00:00"), "Euro", 114),
        Row.of(LocalDateTime.parse("2021-08-21T09:00:00"), "Yen", 1),
        Row.of(LocalDateTime.parse("2021-08-21T10:45:00"), "Euro", 116),
        Row.of(LocalDateTime.parse("2021-08-21T11:15:00"), "Euro", 119),
        Row.of(LocalDateTime.parse("2021-08-21T11:49:00"), "Pounds", 108))
        .returns(
            Types.ROW_NAMED(
                new String[] {"currency_time", "currency", "rate"},
                Types.LOCAL_DATE_TIME, Types.STRING, Types.INT));


    // normal table
    Table rateTable = tEnv.fromDataStream(ratesStream, Schema.newBuilder().build());
    tEnv.createTemporaryView("RatesHistory", rateTable);
    rateTable.printSchema();
    tEnv.from("RatesHistory").execute().print();

    System.out.println("------------version table-------------");

    // version table
    Table versionedTable = tEnv.fromDataStream(ratesStream, Schema.newBuilder()
        .columnByExpression("rowtime", "CAST(currency_time AS TIMESTAMP(3))")
        .primaryKey("currency")
        .build());
    tEnv.createTemporaryView("versionRate", versionedTable);
    System.out.println("versioned table get");
    versionedTable.printSchema();
    tEnv.from("versionRate").execute().print();

    System.out.println("------------key version table-------------");

    // https://nightlies.apache.org/flink/flink-docs-release-1.14/zh/docs/dev/table/concepts/versioned_tables/#%E5%A3%B0%E6%98%8E%E7%89%88%E6%9C%AC%E8%A7%86%E5%9B%BE
    Table versionedRateView = tEnv.sqlQuery(
        "select currency, rate, currency_time " + // (1) `currency_time` 保留了事件时间
            "from ( " +
            "select *, " +
            "ROW_NUMBER() OVER (PARTITION BY currency " + //(2) `currency` 是去重query的unique key，作为主键
            "    ORDER BY currency_time DESC) AS rowNum " +
            "FROM RatesHistory ) " +
            "WHERE rowNum = 1");
    tEnv.createTemporaryView("versioned_rates", versionedRateView);
    versionedRateView.printSchema();
    tEnv.from("versioned_rates").execute().print();

    // default retract mode
    tEnv.toChangelogStream(versionedRateView)
        .executeAndCollect()
        .forEachRemaining(System.out::println);

    tEnv.toChangelogStream(versionedRateView, Schema.newBuilder().build(), ChangelogMode.upsert())
        .executeAndCollect()
        .forEachRemaining(System.out::println);


  }

}
