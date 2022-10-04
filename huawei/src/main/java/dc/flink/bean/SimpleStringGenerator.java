package dc.flink.bean;

import org.apache.flink.streaming.api.functions.source.SourceFunction;

public class SimpleStringGenerator implements SourceFunction<String> {
    boolean running = true;
    int i = 0;

    @Override
    public void run(SourceContext<String> sourceContext) throws Exception {
        while (running) {
            sourceContext.collect("element-" + i);
            i += 1;
            Thread.sleep(1000);
        }
    }

    @Override
    public void cancel() {
        running = false;
    }
}

