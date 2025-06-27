package liudu.leetcode;

/**
 * @author Liu Du
 * @Date 2025/2/8
 */
public class Number188 {

  public static void main(String[] args) {

    int[] ints = {3, 2, 6, 5, 0, 3};
    System.out.println(new Number188().maxProfit(2, ints));


  }

  public int maxProfit(int k, int[] prices) {
    int n = prices.length;
    int[][][] dp = new int[n][2][k];

    for (int j = 0; j < k; j++) {
      dp[0][0][j] = -prices[0];
    }

    for (int i = 1; i < n; i++) {

      for (int j = 0; j < k; j++) {
//        0 是买的收益
        dp[i][0][j] = Math.max(dp[i - 1][0][j], (j == 0 ? 0 : dp[i][1][j - 1]) - prices[i]);
//        1 是买的收益
        dp[i][1][j] = Math.max(dp[i - 1][1][j], dp[i][0][j] + prices[i]);
      }

    }

    return dp[n - 1][1][k - 1];
  }

}
