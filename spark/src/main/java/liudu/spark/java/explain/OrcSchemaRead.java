package liudu.spark.java.explain;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

/**
 * @author liudu
 * @title: OrcSchemaRead
 * @projectName liuduTest
 * @description: TODO
 * @date 2022/5/26上午11:48
 */
public class OrcSchemaRead {

  public static void main(String[] args) {
    SparkSession spark = SparkSession.builder()
        .appName("SparkStudy")
        .master("local[*]")
        .getOrCreate();

    Dataset<Row> orc = spark.read().orc("/Users/liudu/IdeaProjects/liuduTest/spark/src/main/resources/da1cf86b-e0b4-4fc7-a388-3dc7c4c85bf6.orc");

    orc.printSchema();
  }

}
