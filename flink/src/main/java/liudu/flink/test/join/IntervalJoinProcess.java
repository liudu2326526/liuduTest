package liudu.flink.test.join;

import liudu.flink.test.bean.MyBeanData;
import liudu.flink.test.source.MySource1;
import liudu.flink.test.source.MySource2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.ProcessJoinFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

/**
 * @author liudu
 * @title: IntervalJoinProcess
 * @projectName liuduTest
 * @description: TODO
 * @date 2022/6/22下午3:53
 */
public class IntervalJoinProcess {

  public static void main(String[] args) {
    StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
    env.setParallelism(1);
    DataStreamSource<MyBeanData> ds1 = env.addSource(new MySource1());
    DataStreamSource<MyBeanData> ds2 = env.addSource(new MySource2());

    ds1.keyBy(user -> user.getId())
        .intervalJoin(ds2.keyBy(user -> user.getId()))
        .between(Time.milliseconds(-5), Time.milliseconds(5))
        .process(new ProcessJoinFunction<MyBeanData, MyBeanData, MyBeanData>() {
          @Override
          public void processElement(MyBeanData myBeanData, MyBeanData myBeanData2, Context context,
              Collector<MyBeanData> collector) throws Exception {

          }
        });

  }

}
