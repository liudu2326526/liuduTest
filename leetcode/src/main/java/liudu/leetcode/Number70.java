package liudu.leetcode;

public class Number70 {

  public int climbStairs(int n) {
    int[] dp = new int[n];

    for (int i = 0; i < dp.length; i++) {
      if (i == 0) {
        dp[i] = 1;
      } else if (i == 1) {
        dp[i] = 2;
      } else {

        dp[i] = dp[i - 1] + dp[i - 2];
      }

    }

    return dp[n - 1];
  }

}
