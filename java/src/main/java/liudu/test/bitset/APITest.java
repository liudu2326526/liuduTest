package liudu.test.bitset;

import com.google.common.hash.Funnels;
import com.google.common.hash.Hashing;

public class APITest {

  public static void main(String[] args) {
    byte[] bytes = Hashing.murmur3_128()
        .hashObject("刘度", Funnels.stringFunnel())
        .asBytes();

    System.out.println(bytes);
    double v1 = Math.log(2) * Math.log(2);
    long v =(long) (-1000000000 * Math.log(0.003) / v1);
    int i =(int) (-1000000000 * Math.log(0.003) / v1);

    int max = Math
        .max(1, (int) Math.round((double) v / 1000000000 * Math.log(2)));

    System.out.println(max);

    System.out.println(v1);
    System.out.println(Math.log(0.001));
    System.out.println(v);
    System.out.println(i);
  }

}
