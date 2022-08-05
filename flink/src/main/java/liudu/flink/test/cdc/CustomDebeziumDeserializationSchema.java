package liudu.flink.test.cdc;

import com.alibaba.fastjson.JSONObject;
import com.ververica.cdc.debezium.DebeziumDeserializationSchema;
import java.util.List;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.util.Collector;
import org.apache.kafka.connect.data.Field;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liudu
 * @title: CustomDebeziumDeserializationSchema
 * @projectName liuduTest
 * @description: TODO
 * @date 2022/6/24下午3:03
 */
public class CustomDebeziumDeserializationSchema implements
    DebeziumDeserializationSchema<JSONObject> {

  @Override
  public void deserialize(SourceRecord sourceRecord, Collector<JSONObject> collector)
      throws Exception {

    final Logger LOGGER = LoggerFactory.getLogger(CustomDebeziumDeserializationSchema.class);

    final long serialVersionUID = 7906905121308228264L;

    JSONObject resJson = new JSONObject();
    try {
      Struct valueStruct = (Struct) sourceRecord.value();
      Struct afterStruct = valueStruct.getStruct("after");
      Struct beforeStruct = valueStruct.getStruct("before");
      Struct sourceStruct = valueStruct.getStruct("source");
      putSchema(sourceStruct, resJson);
      // 注意：若valueStruct中只有after,则表明插入；若只有before，说明删除；若既有before，也有after，则代表更新
      if (afterStruct != null && beforeStruct != null) {
        // 修改
        System.out.println("Updating >>>>>>>");
        List<Field> fields = afterStruct.schema().fields();
        String name;
        Object value;
        for (Field field : fields) {
          name = field.name();
          value = afterStruct.get(name);
          resJson.put(name, value);
        }
        LOGGER.info("Updated, ignored ...");
      } else if (afterStruct != null) {
        // 插入
        System.out.println("Inserting >>>>>>>");
        List<Field> fields = afterStruct.schema().fields();
        String name;
        Object value;
        for (Field field : fields) {
          name = field.name();
          value = afterStruct.get(name);
          resJson.put(name, value);
        }
      } else if (beforeStruct != null) {
        // 删除
        System.out.println("Deleting >>>>>>>");
        LOGGER.info("Deleted, ignored ...");
      } else {
        System.out.println("No this operation ...");
        LOGGER.warn("No this operation ...");
      }
    } catch (Exception e) {
      System.out.println("Deserialize throws exception:");
      LOGGER.error("Deserialize throws exception:", e);
    }
    collector.collect(resJson);

  }

  private void putSchema(Struct struct, JSONObject resJson) {
    resJson.put("db", struct.get("db"));
    resJson.put("table", struct.get("table"));
  }

  @Override
  public TypeInformation<JSONObject> getProducedType() {
    return BasicTypeInfo.of(JSONObject.class);
  }
}
