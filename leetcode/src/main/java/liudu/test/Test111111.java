package liudu.test;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;

public class Test111111 {

  static final List<Long> LIKE_BINS = ImmutableList
      .of(0L, 3L, 5L, 10L, 15L, 30L, 50L, 70L, 100L, 200L, 300L, 400L, 500L, 600L, 700L, 800L, 900L,
          1000L, 1500L, 2000L, 2500L, 3000L, 3500L, 4000L, 4500L, 5000L, 6000L, 7000L, 8000L,
          10000L, 15000L, 20000L, 30000L, 50000L, 100000L);

  public static void main(String[] args) {
    ArrayList<String> strings = new ArrayList<>();
    strings.add("qw");
    strings.add("qgf");
    strings.add("sd");
    strings.add("gsd");

    strings.remove("ssss");
    System.out.println(strings);

  }

  public static int calllll(Long aLong) {
    System.out.println(LIKE_BINS);
    System.out.println(LIKE_BINS.size());
    for (int i = 0; i < LIKE_BINS.size(); i++) {
      System.out.println(LIKE_BINS.get(i));
      if (aLong <= LIKE_BINS.get(i)) {
        return i;
      }
    }
    return -1;
  }

}
