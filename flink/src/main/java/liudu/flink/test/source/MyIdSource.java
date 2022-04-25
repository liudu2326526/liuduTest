package liudu.flink.test.source;

import java.util.Random;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

public class MyIdSource implements SourceFunction<Integer> {

  @Override
  public void run(SourceContext<Integer> sourceContext) throws Exception {
    Random random = new Random();
    while (true) {
      sourceContext.collect(random.nextInt(10));
      Thread.sleep(1000);
    }
  }

  @Override
  public void cancel() {

  }
}
