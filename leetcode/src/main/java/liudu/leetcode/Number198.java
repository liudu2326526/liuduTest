package liudu.leetcode;

public class Number198 {


  public int rob(int[] nums) {
    int[] dp = new int[nums.length + 3];

    for (int i = 3; i < dp.length; i++) {
      dp[i] = Math.max(Math.max(dp[i - 2] + nums[i - 3], dp[i - 3] + nums[i - 3]), dp[i - 1]);
    }

    return dp[nums.length + 2];
  }


}
