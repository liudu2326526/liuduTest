package liudu.leetcode;

/**
 * @author Liu Du
 * @Date 2025/2/8
 */
public class Number188 {

  public static void main(String[] args) {

    int[] ints = { 3, 2, 6, 5, 0, 3 };
    System.out.println(new Number188().maxProfit(2, ints));

  }

  /**
   * 188. 买卖股票的最佳时机 IV
   * 给定一个数组 prices 和一个整数 k，表示你最多可以完成 k 笔交易（买入和卖出为一次交易），
   * 设计一个算法来计算你所能获取的最大利润。
   * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
   *
   * @param k      最多交易次数
   * @param prices 股票价格数组
   * @return 最大利润
   */
  public int maxProfit(int k, int[] prices) {
    int n = prices.length;
    // dp[i][0][j] 表示第i天，持有股票，已经完成j次交易的最大利润
    // dp[i][1][j] 表示第i天，不持有股票，已经完成j次交易的最大利润
    int[][][] dp = new int[n][2][k];

    // 初始化：第0天，持有股票的状态，买入股票，利润为 -prices[0]
    for (int j = 0; j < k; j++) {
      dp[0][0][j] = -prices[0];
    }

    // 从第1天开始遍历
    for (int i = 1; i < n; i++) {
      for (int j = 0; j < k; j++) {
        // 持有股票的最大利润
        // 两种情况：1. 前一天就持有股票 2. 今天买入（前一天不持有且已完成j-1次交易）
        dp[i][0][j] = Math.max(
            dp[i - 1][0][j], // 继承前一天持有股票的状态
            (j == 0 ? 0 : dp[i][1][j - 1]) - prices[i] // 今天买入，前提是已完成j-1次交易
        );
        // 不持有股票的最大利润
        // 两种情况：1. 前一天就不持有股票 2. 今天卖出（前一天持有股票）
        dp[i][1][j] = Math.max(
            dp[i - 1][1][j], // 继承前一天不持有股票的状态
            dp[i][0][j] + prices[i] // 今天卖出
        );
      }
    }

    // 返回最后一天，不持有股票，完成k-1次交易的最大利润
    return dp[n - 1][1][k - 1];
  }

}
