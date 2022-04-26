package liudu.flink.test.broadcast;

import liudu.flink.test.bean.MyBeanData;
import org.apache.flink.api.common.state.BroadcastState;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.common.state.ReadOnlyBroadcastState;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.streaming.api.functions.co.BroadcastProcessFunction;
import org.apache.flink.util.Collector;

public class MyBroadcastTest1 extends BroadcastProcessFunction<MyBeanData, Integer, MyBeanData> {

  private final MapStateDescriptor<Void, Integer> descriptor = new MapStateDescriptor<>("filter",
      Types.VOID,
      Types.INT);

  @Override
  public void processElement(MyBeanData myBeanData,
      BroadcastProcessFunction<MyBeanData, Integer, MyBeanData>.ReadOnlyContext readOnlyContext,
      Collector<MyBeanData> collector) throws Exception {

    int id = myBeanData.getId();
    ReadOnlyBroadcastState<Void, Integer> filter = readOnlyContext.getBroadcastState(descriptor);
    if (filter != null) {
      Integer integer = filter.get(null);
      if (integer != null && id == integer) {
        collector.collect(myBeanData);
      }
    }

  }

  @Override
  public void processBroadcastElement(Integer integer,
      BroadcastProcessFunction<MyBeanData, Integer, MyBeanData>.Context context,
      Collector<MyBeanData> collector) throws Exception {
    BroadcastState<Void, Integer> filter = context.getBroadcastState(descriptor);
    filter.clear();
    filter.put(null, integer);
  }
}
