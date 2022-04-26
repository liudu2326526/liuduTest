package liudu.flink.test.source;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import liudu.flink.test.bean.MyBeanData;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

public class MySource1 implements SourceFunction<MyBeanData> {


  private boolean running = true;

  @Override
  public void run(SourceContext<MyBeanData> ctx) throws Exception {

    Random random = new Random();
    Map<Integer, Double> tempMap = new HashMap<>();
    for (int i = 0; i < 10; i++) {
      tempMap.put(i + 1, 60 + random.nextGaussian() * 20);
    }
    while (true) {
      for (Integer key : tempMap.keySet()) {
        Double newTmp = tempMap.get(key) + random.nextGaussian();
        tempMap.put(key, newTmp);
//        MyBeanData myBeanData = new MyBeanData(key, System.currentTimeMillis(), "liudu" + key);
//        ctx.collectWithTimestamp(myBeanData, System.currentTimeMillis());
        ctx.collect(new MyBeanData(key, System.currentTimeMillis(), "liudu" + key/2));
        Thread.sleep(1000L);
      }

//      System.out.println("======================");
    }

  }

  @Override
  public void cancel() {
    this.running = false;
  }
}
