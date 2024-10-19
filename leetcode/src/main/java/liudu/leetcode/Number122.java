package liudu.leetcode;

public class Number122 {

  public int maxProfit(int[] prices) {
    int oneIndex = 0;
    int num = 0;

    int length = prices.length;

    if (length <= 1) {
      return 0;
    }

    for (int i = 1; i < length; i++) {
      if (prices[i] < prices[i - 1]) {
        num = prices[i - 1] - prices[oneIndex] + num;
        oneIndex = i;
      }
    }

    if (oneIndex != length - 1) {
      num = prices[length - 1] - prices[oneIndex] + num;
    }

    return num;
  }


}
