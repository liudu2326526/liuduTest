package liudu.flink.test.join;

import liudu.flink.test.bean.MyBeanData;
import liudu.flink.test.broadcast.MyBroadcastTest1;
import liudu.flink.test.source.NumberSource;
import liudu.flink.test.source.MySource1;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.streaming.api.datastream.BroadcastConnectedStream;
import org.apache.flink.streaming.api.datastream.BroadcastStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class BroadcastJoinProcess {

  public static void main(String[] args) throws Exception {

    StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
    env.setParallelism(1);
    DataStreamSource<MyBeanData> dataStreamSource = env.addSource(new MySource1());
    DataStreamSource<Integer> idSource = env.addSource(new NumberSource());

    BroadcastStream<Integer> filter = idSource.broadcast(
        new MapStateDescriptor<Void, Integer>("filter", Types.VOID, Types.INT));

    BroadcastConnectedStream<MyBeanData, Integer> connect = dataStreamSource.connect(filter);
    connect.process(new MyBroadcastTest1()).print();

    env.execute();

  }
}
