package liudu.test.zijie;

import java.util.Arrays;

public class ZIjie5 {

  public static void main(String[] args) {
//    2147483647
//    System.out.println(Integer.MAX_VALUE);
//    System.out.println(Integer.MIN_VALUE);
    System.out.println(reverse(2147483612));
  }


  public static int reverse(int x) {
    int ans = 0;
    int tmp;
    boolean flag = x > 0;

    if (!flag) {
      x = -x;
    }

    int num1 = 1000000000;
    boolean dangerous = x > num1;
    int max = Integer.MAX_VALUE;
    while (x > 0) {
      tmp = x % 10;
      if (dangerous) {
        int i = max / num1;
        if (tmp > i) {
          return 0;
        } else if (tmp < i) {
          dangerous = false;
        } else {
          max %= num1;
          num1 /= 10;
        }

      }

      ans *= 10;
      ans += tmp;
      x /= 10;
    }

    if (!flag) {
      ans = -ans;
    }

    return ans;
  }

}
