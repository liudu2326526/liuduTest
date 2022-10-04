package dc.flink.bean;

import lombok.Data;

@Data
public class DataBean {
    String data;
    Long mtime;
    String dt;

    public DataBean() {
    }

    public DataBean(String data, Long mtime, String dt) {
        this.data = data;
        this.mtime = mtime;
        this.dt = dt;
    }
}
