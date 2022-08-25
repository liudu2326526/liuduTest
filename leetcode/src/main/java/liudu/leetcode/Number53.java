package liudu.leetcode;

public class Number53 {

  public int maxSubArray(int[] nums) {
    int pre = 0;
    int max = nums[0];
    for (int x : nums) {
      pre = Math.max(pre + x, x);
      max = Math.max(max, pre);
    }
    return max;
  }
}
