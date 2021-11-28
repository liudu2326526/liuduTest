package liudu.com;

import com.amazonaws.services.s3.AmazonS3;
import java.io.File;

public class Test {

  public static void main(String[] args) throws InterruptedException {
//    String sql = "SELECT DISTINCT b.udid\n"
//        + "FROM test.test_shopee_br_message a\n"
//        + "join (\n"
//        + "  select udid, gaid from dwd.dwd_ad_user_detail_ss_daily_his where dt='2021-10-24'\n"
//        + ")  b\n"
//        + "on a.gaid = b.gaid\n"
//        + "LEFT join (\n"
//        + "  select udid from prod_user.applist where dt='2021-10-24' and packagename = 'com.shopee.br'\n"
//        + ") c\n"
//        + "on b.udid = c.udid\n"
//        + "WHERE c.udid IS NULL";
//    String output = "s3://dywx-beginner/liudu/query_shoppe/";
//    Boolean test = AmazonEKSUtils.sendJob("test", output, sql);
//    System.out.println(test);

//    AmazonS3Utils.uploadUserPackage("udid","test2",new File("/Users/liudu/Downloads/test_package.csv"));

    AthenaUtils.addUserPackagePartition("gaid","1636546943281");

//    AmazonS3 amazonS3Client = AmazonS3Utils.getAmazonS3Client();
//
//    String date = DateUtils.getCurrDate();
//    do {
//      date = DateUtils.getOffsetDate(date, -1);
//    } while (!amazonS3Client.doesObjectExist("dywx-data",
//        String.format("prod/data/dwd/dwd_ad_user_detail_ss_daily_his/dt=%s/_SUCCESS", date)));
//    System.out.println(date);

//    System.out.println(Long.parseLong(AthenaUtils.querySQL(
//        "select count(*) from ods.ods_user_mixed_crowd_package_inc_be where type = 'udid' and name = 'test2'",
//        "ods")));
  }

}
