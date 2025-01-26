package liudu.leetcode;

public class Number377 {

  public int combinationSum4(int[] nums, int target) {
    int[] ints = new int[target + 1];
    ints[0] = 1;

    for (int i = 1; i < ints.length; i++) {
      for (int num : nums) {
        if (i - num >= 0) {
          ints[i] += ints[i - num];
        }
      }
    }

    return ints[target];
  }

}
