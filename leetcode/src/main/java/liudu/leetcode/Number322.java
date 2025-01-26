package liudu.leetcode;

import java.util.Arrays;

public class Number322 {

  public static void main(String[] args) {
    int[] ints = {2};
    Number322 number322 = new Number322();
    System.out.println(number322.coinChange(ints, 3));

  }

  public int coinChange(int[] coins, int amount) {
    int[] dp = new int[amount + 1];
    for (int i = 1; i < dp.length; i++) {
      dp[i] = Integer.MAX_VALUE;
    }

    for (int i = 1; i < dp.length; i++) {
      for (int coin : coins) {
        if (i - coin >= 0 && dp[i - coin] != Integer.MAX_VALUE) {
          dp[i] = Math.min(dp[i], dp[i - coin] + 1);
        }
      }
    }

    if (dp[amount] == Integer.MAX_VALUE) {
      return -1;
    } else {
      return dp[amount];
    }
  }

}
