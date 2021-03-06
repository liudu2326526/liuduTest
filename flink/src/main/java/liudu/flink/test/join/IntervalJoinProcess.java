package liudu.flink.test.join;

import java.time.Duration;
import liudu.flink.test.bean.MyBeanData;
import liudu.flink.test.source.MySource1;
import liudu.flink.test.source.MySource2;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.ProcessJoinFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

/**
 * @author liudu
 * @title: IntervalJoinProcess
 * @projectName liuduTest
 * @description: https://mp.weixin.qq.com/s/_I1oE_pQXonQ9IoMsWIUzg
 * @date 2022/6/22下午3:53
 */
public class IntervalJoinProcess {

  public static void main(String[] args) throws Exception {
    StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
    env.setParallelism(1);
    SingleOutputStreamOperator<MyBeanData> ds1 = env.addSource(new MySource1())
        //注入 watermark
        .assignTimestampsAndWatermarks(
            WatermarkStrategy.<MyBeanData>forBoundedOutOfOrderness(Duration.ofSeconds(5))
                .withTimestampAssigner((event, timestamp) -> event.getTimestamp()));

    SingleOutputStreamOperator<MyBeanData> ds2 = env.addSource(new MySource2())
        .assignTimestampsAndWatermarks(
            WatermarkStrategy.<MyBeanData>forBoundedOutOfOrderness(Duration.ofSeconds(5))
                .withTimestampAssigner((event, timestamp) -> event.getTimestamp()));

    SingleOutputStreamOperator<MyBeanData> process = ds1.keyBy(MyBeanData::getId)
        .intervalJoin(ds2.keyBy(MyBeanData::getId))
        .between(Time.seconds(-5), Time.seconds(5))
        .process(new ProcessJoinFunction<MyBeanData, MyBeanData, MyBeanData>() {
          @Override
          public void processElement(MyBeanData myBeanData, MyBeanData myBeanData2, Context context,
              Collector<MyBeanData> collector) throws Exception {
            MyBeanData result = new MyBeanData(myBeanData.getId(), myBeanData.getTimestamp(),
                myBeanData.getMessage() + myBeanData2.getMessage());
            collector.collect(result);
          }
        });

    process.print();

    env.execute();
  }

}
