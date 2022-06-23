package liudu.flink.test.function;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.async.ResultFuture;
import org.apache.flink.streaming.api.functions.async.RichAsyncFunction;

/**
 * @author liudu
 * @title: DimJoinDemoAyncFunction
 * @projectName liuduTest
 * @description: TODO
 * @date 2022/6/22下午5:39
 */
public class DimJoinDemoAyncFunction extends
    RichAsyncFunction<Tuple2<String, Integer>, Tuple3<String, Integer, String>> {

  // 链接
  private static String jdbcUrl = "jdbc:mysql://192.168.145.1:3306?useSSL=false";
  private static String username = "root";
  private static String password = "123";
  private static String driverName = "com.mysql.jdbc.Driver";
  java.sql.Connection conn;
  PreparedStatement ps;
  LoadingCache<Integer, String> dim;

  @Override
  public void open(Configuration parameters) throws Exception {
    super.open(parameters);

    //使用google LoadingCache来进行缓存
    dim = CacheBuilder.newBuilder()
        //最多缓存个数，超过了就根据最近最少使用算法来移除缓存
        .maximumSize(1000)
        //在更新后的指定时间后就回收
        .expireAfterWrite(10, TimeUnit.MINUTES)
        //指定移除通知
        .removalListener(new RemovalListener<Integer, String>() {
          @Override
          public void onRemoval(RemovalNotification<Integer, String> removalNotification) {
            System.out.println(
                removalNotification.getKey() + "被移除了，值为：" + removalNotification.getValue());
          }
        })
        .build(
            //指定加载缓存的逻辑
            new CacheLoader<Integer, String>() {
              @Override
              public String load(Integer cityId) throws Exception {
                String cityName = "readFromHbase(cityId)";
                return cityName;
              }
            }
        );

    Class.forName(driverName);
    conn = DriverManager.getConnection(jdbcUrl, username, password);
    ps = conn.prepareStatement("select city_name from tmp.city_info where id = ?");

  }

  @Override
  public void close() throws Exception {
    super.close();
    conn.close();
  }

  @Override
  public void asyncInvoke(Tuple2<String, Integer> input,
      ResultFuture<Tuple3<String, Integer, String>> resultFuture) throws Exception {
    ps.setInt(1, input.f1);
    ResultSet rs = ps.executeQuery();
    String cityName = null;
    if (rs.next()) {
      cityName = rs.getString(1);
    }
    List list = new ArrayList<Tuple2<Integer, String>>();
    list.add(new Tuple3<>(input.f0, input.f1, cityName));
    resultFuture.complete(list);
  }

  @Override
  public void timeout(Tuple2<String, Integer> input,
      ResultFuture<Tuple3<String, Integer, String>> resultFuture) throws Exception {
    List list = new ArrayList<Tuple2<Integer, String>>();
    list.add(new Tuple3<>(input.f0, input.f1, ""));
    resultFuture.complete(list);
  }
}
