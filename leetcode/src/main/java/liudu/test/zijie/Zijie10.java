package liudu.test.zijie;

public class Zijie10 {

  public int maxProfit(int[] prices) {
    int min = Integer.MAX_VALUE;
    int maxResult = 0;
    for (int i = 0; i < prices.length; i++) {
      min = Math.min(min, prices[i]);
      if (min < prices[i]) {
        maxResult = Math.max(maxResult,prices[i] - min);
      }
    }

    return maxResult;
  }

}
