package liudu.flink.test.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyBeanData {

  private int Id;
  private Long timestamp;
  private String message;

}
