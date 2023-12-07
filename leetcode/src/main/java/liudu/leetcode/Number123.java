package liudu.leetcode;

public class Number123 {

  public static void main(String[] args) {
    Number123 number123 = new Number123();
    int[] ints = {1, 2, 3, 4, 5};
    System.out.println(number123.maxProfit(ints));
  }

  public int maxProfit(int[] prices) {
    int index = 0;
    int num = 0;
    int max = 0;

    int maxIndex = prices.length - 1;

    if (maxIndex == 0) {
      return 0;
    }

    if (maxIndex == 1) {
      return Math.max(prices[1] - prices[0], 0);
    }

    int[] ints1 = new int[maxIndex + 1];
    int[] ints2 = new int[maxIndex + 1];

    // 收益降到 0 就归零
    for (int i = 1; i <= maxIndex; i++) {
      num = prices[i] - prices[index];
      if (num < 0) {
        index = i;
      }
      max = Math.max(max, num);
      ints1[i] = max;
    }

    index = maxIndex;
    max = 0;

    for (int i = maxIndex; i >= 0; i--) {
      num = prices[index] - prices[i];
      if (num < 0) {
        index = i;
      }
      max = Math.max(max, num);
      ints2[i] = max;
    }

    max = 0;
    for (int i = 0; i <= maxIndex; i++) {
      max = Math.max(max,ints1[i] + ints2[i]);
    }

    return max;
  }


}
