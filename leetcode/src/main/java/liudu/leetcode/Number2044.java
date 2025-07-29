package liudu.leetcode;

import java.util.HashMap;

/**
 * @author Liu Du
 * @Date 2025/7/28
 */
public class Number2044 {

  public static void main(String[] args) {
    int[] ints = {3, 2, 1, 5};
//    int[] ints = {2, 2, 2};
    Number2044 number2044 = new Number2044();
    System.out.println(number2044.countMaxOrSubsets(ints));
  }

  public int countMaxOrSubsets(int[] nums) {
    int totalOr = 0;
    for (int x : nums) {
      totalOr |= x;
    }

    dfs(0, 0, nums, totalOr);
    return ans;
  }

  private int ans = 0;

  private void dfs(int i, int subsetOr, int[] nums, int totalOr) {
    if (subsetOr == totalOr) {
      ans += 1 << (nums.length - i);
      return;
    }
    if (i == nums.length) {
      return;
    }
    dfs(i + 1, subsetOr, nums, totalOr); // 不选 nums[i]
    dfs(i + 1, subsetOr | nums[i], nums, totalOr); // 选 nums[i]
  }

}

// 3215
// 325