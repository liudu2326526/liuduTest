package liudu.test;

import java.util.HashMap;
import java.util.Map;

public class EightDay02 {

}

class SolutionEightDay02 {

  Map<Integer, Integer> count;

  public int numWays(int n) {
    count = new HashMap<>();
    count.put(-2, 0);
    count.put(-1, 0);
    count.put(0, 1);
    count.put(1, 1);
    count.put(2, 2);

    for (int i = 1; i <= n; i++) {
      getMap(i);
    }

    return count.get(n);
  }

  public void getMap(int n) {
    if (!count.containsKey(n)) {
      count.put(n, (count.get(n - 1) + count.get(n - 2)) % 1000000007);
    }

  }
}