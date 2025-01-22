package liudu.leetcode;

/**
 * @author Liu Du
 * @Date 2024/11/11
 */
public class Number300 {

  public static void main(String[] args) {
    Number300 number300 = new Number300();
    int[] ints = {0, 1, 0, 3, 2, 3};
    System.out.println(number300.lengthOfLIS(ints));
  }

  public int lengthOfLIS(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }
    int[] dp = new int[nums.length];
    dp[0] = 1;
    int max = 1;
    for (int i = 1; i < nums.length; i++) {
      dp[i] = 1;
      for (int j = 0; j < i; j++) {

        if (nums[i] > nums[j]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }

      }

      max = Math.max(max, dp[i]);
    }

    return max;
  }


  public int lengthOfLIS1(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }
    int[] dp = new int[nums.length];
    dp[0] = 1;
    int maxans = 1;
    for (int i = 1; i < nums.length; i++) {
      dp[i] = 1;
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
      maxans = Math.max(maxans, dp[i]);
    }
    return maxans;
  }


}
