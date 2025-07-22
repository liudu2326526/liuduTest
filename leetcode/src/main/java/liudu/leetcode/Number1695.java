package liudu.leetcode;

import java.util.HashSet;

/**
 * @author Liu Du
 * @Date 2025/7/22
 */
public class Number1695 {

  public static void main(String[] args) {
    Number1695 number1695 = new Number1695();
    int[] ints = {4,2,4,5,6};
    System.out.println(number1695.maximumUniqueSubarray(ints));
  }

  public int maximumUniqueSubarray(int[] nums) {
    HashSet<Integer> set = new HashSet<>();
    int index = 0;
    int num = 0;
    int max = 0;

    for (int i = 0; i < nums.length; i++) {
      while (set.contains(nums[i])) {
        set.remove(nums[index]);
        num -= nums[index];
        index++;
      }
      set.add(nums[i]);
      num += nums[i];
      max = Math.max(num, max);
    }

    return max;
  }
}
