package liudu.spark.java.explain;

import liudu.spark.scala.explan.RBMFunctions;
import liudu.spark.scala.explan.RBMFunctions$class;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

/**
 * @author liudu
 * @title: SparkExplainProcess
 * @projectName liuduTest
 * @date 2022/4/26下午4:50
 */
public class SparkExplainProcess {

  public static void main(String[] args) {
    SparkSession spark = SparkSession.builder()
        .appName("SparkStudy")
        .master("local[*]")
        .getOrCreate();

    Dataset<Row> sql = spark.sql("SELECT\n"
        + "COUNT(*) pv,\n"
        + "COUNT(DISTINCT distinct_id) uv,\n"
        + "region\n"
        + "FROM (\n"
        + "SELECT 'liudu' distinct_id,'guangdong' region\n"
        + ") GROUP BY region");
    sql.explain();
    sql.printSchema();
    sql.show();
  }

}
