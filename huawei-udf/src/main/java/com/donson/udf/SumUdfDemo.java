package com.donson.udf;

import org.apache.hadoop.hive.ql.exec.UDF;

public class SumUdfDemo extends UDF {

  public int evaluate(int a, int b) {
    return a + b;
  }

}
