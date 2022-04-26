package liudu.flink.test;

import java.time.Duration;
import liudu.flink.test.bean.MyBeanData;
import liudu.flink.test.source.MySource1;
import org.apache.flink.api.common.eventtime.Watermark;
import org.apache.flink.api.common.eventtime.WatermarkGenerator;
import org.apache.flink.api.common.eventtime.WatermarkGeneratorSupplier;
import org.apache.flink.api.common.eventtime.WatermarkGeneratorSupplier.Context;
import org.apache.flink.api.common.eventtime.WatermarkOutput;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author liudu
 * @title: WatermarkProcess
 * @projectName liuduTest
 * @date 2022/4/26下午3:10
 */
public class WatermarkProcess {

  public static void main(String[] args) {
    StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
    env.setParallelism(1);
    DataStreamSource<MyBeanData> dataStreamSource = env.addSource(new MySource1());

    // 手动实现
    SingleOutputStreamOperator<MyBeanData> streamOperator = dataStreamSource
        .assignTimestampsAndWatermarks(new WatermarkStrategy<>() {
          @Override
          public WatermarkGenerator<MyBeanData> createWatermarkGenerator(
              WatermarkGeneratorSupplier.Context context) {
            return new WatermarkGenerator<>() {
              private long maxTimestamp;
              private long delay = 3000;

              //在这个onEvent方法里，我们从每个元素里抽取了一个时间字段，但是我们并没有生成水印发射给下游，而是自己保存了在一个变量里
              @Override
              public void onEvent(MyBeanData event, long eventTimestamp, WatermarkOutput output) {
                maxTimestamp = Math.max(maxTimestamp, event.getTimestamp());
              }

              //在onPeriodicEmit方法里，使用最大的日志时间减去我们想要的延迟时间作为水印发射给下游。
              @Override
              public void onPeriodicEmit(WatermarkOutput output) {
                output.emitWatermark(new Watermark(maxTimestamp - delay));
              }
            };
          }
        });

    //使用 forBoundedOutOfOrderness 生成 watermark
    SingleOutputStreamOperator<MyBeanData> myBeanDataSingleOutputStreamOperator = dataStreamSource
        .assignTimestampsAndWatermarks(WatermarkStrategy.forBoundedOutOfOrderness(
            Duration.ofSeconds(3)));

    //传入 event time 加 watermark
    dataStreamSource.assignTimestampsAndWatermarks(
        WatermarkStrategy.<MyBeanData>forBoundedOutOfOrderness(Duration.ofSeconds(3))
            .withTimestampAssigner((event, timestamp) -> event.getTimestamp()));


  }

}
