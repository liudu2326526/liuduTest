package liudu.flink.test.join;

import liudu.flink.test.bean.MyBeanData;
import liudu.flink.test.source.MySource1;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author liudu
 * @title: SourcePrint
 * @projectName liuduTest
 * @description: TODO
 * @date 2022/6/22下午4:23
 */
public class SourcePrint {

  public static void main(String[] args) throws Exception {
    StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
    env.setParallelism(1);
    SingleOutputStreamOperator<MyBeanData> ds1 = env.addSource(new MySource1());
    ds1.print();
    env.execute();
  }

}
