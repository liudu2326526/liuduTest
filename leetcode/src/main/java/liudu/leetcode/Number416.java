package liudu.leetcode;

import java.util.Arrays;

/**
 * @author liudu
 * @title: Number416
 * @projectName liuduTest
 * @description: 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * @date 2022/7/14下午5:02
 */
public class Number416 {

  public boolean canPartition(int[] nums) {
    Arrays.sort(nums);
    if (nums.length < 2) {
      return false;
    }

    int half = 0;
    for (int num : nums) {
      half += num;
    }

    if (half % 2 != 0) {
      return false;
    }

    half = half / 2;

    boolean[][] dp = new boolean[nums.length][half + 1];
    for (boolean[] booleans : dp) {
      booleans[0] = true;
    }
    dp[0][nums[0]] = true;

    for (int i = 1; i < dp.length; i++) {
      for (int j = 0; j < dp[i].length; j++) {
        dp[i][j] = dp[i - 1][j] || (j - nums[i] >= 0 && dp[i - 1][j - nums[i]]);
      }
    }

    return dp[nums.length - 1][half];
  }

}
