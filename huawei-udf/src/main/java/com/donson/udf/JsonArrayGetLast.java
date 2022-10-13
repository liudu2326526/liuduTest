package com.donson.udf;

import com.alibaba.fastjson.JSONArray;
import org.apache.hadoop.hive.ql.exec.UDF;

public class JsonArrayGetLast extends UDF {

  public String evaluate(String stringJsonArray) {

    JSONArray jsonArray = JSONArray.parseArray(stringJsonArray);

    if (jsonArray.size() == 0) {
      return null;
    }

    return jsonArray.getJSONObject(jsonArray.size() - 1).toJSONString();
  }

  public String evaluate(String stringJsonArray, String key) {

    JSONArray jsonArray = JSONArray.parseArray(stringJsonArray);

    if (jsonArray.size() == 0) {
      return null;
    }

    return jsonArray.getJSONObject(jsonArray.size() - 1).getString(key);
  }

}
