package liudu.flink.test.window;

import java.time.Duration;
import liudu.flink.test.bean.MyBeanData;
import liudu.flink.test.source.MySource1;
import org.apache.flink.api.common.eventtime.Watermark;
import org.apache.flink.api.common.eventtime.WatermarkGenerator;
import org.apache.flink.api.common.eventtime.WatermarkGeneratorSupplier;
import org.apache.flink.api.common.eventtime.WatermarkGeneratorSupplier.Context;
import org.apache.flink.api.common.eventtime.WatermarkOutput;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.datastream.WindowedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.streaming.api.windowing.assigners.SlidingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.assigners.SlidingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

/**
 * @author liudu
 * @title: WindowCountProcess
 * @projectName liuduTest
 * @date 2022/4/26下午7:12
 */
public class WindowCountProcess {

  public static void main(String[] args) throws Exception {
    StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
    env.setParallelism(1);
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
    DataStreamSource<MyBeanData> dataStreamSource = env.addSource(new MySource1());

    SingleOutputStreamOperator<Tuple2<String, Integer>> map = dataStreamSource
        .assignTimestampsAndWatermarks(
            WatermarkStrategy.<MyBeanData>forBoundedOutOfOrderness(Duration.ofSeconds(3))
                .withTimestampAssigner((event, timestamp) -> event.getTimestamp()))
        .map(new MapFunction<MyBeanData, Tuple2<String, Integer>>() {
          @Override
          public Tuple2<String, Integer> map(MyBeanData value) throws Exception {
            return new Tuple2<>(value.getMessage(), 1);
          }
        });

    KeyedStream<Tuple2<String, Integer>, String> keyedStream = map
        .keyBy(value -> value.f0);

    WindowedStream<Tuple2<String, Integer>, String, TimeWindow> window = keyedStream
        .window(SlidingEventTimeWindows.of(Time.seconds(5),Time.seconds(3)));

    SingleOutputStreamOperator<Tuple2<String, Integer>> sum = window.sum(1);
    dataStreamSource.print();
    sum.print();

//    dataStreamSource.print();
    env.execute();
  }

}
