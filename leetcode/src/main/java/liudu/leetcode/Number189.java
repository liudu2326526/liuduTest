package liudu.leetcode;

import java.util.Arrays;

/**
 * @author liudu
 * @title: Number189
 * @projectName liuduTest
 * @description: 给你一个数组，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 * @date 2022/7/11下午5:38
 */
public class Number189 {

  public void rotate(int[] nums, int k) {
    int length = nums.length;
    int[] copy = Arrays.copyOf(nums, length);
    for (int i = 0; i < copy.length; i++) {
      nums[(i + k) % length] = copy[i];
    }
  }

  public void rotate2(int[] nums, int k) {
    int length = nums.length;
    k = k % length;
    int count = gcd(length, k);
    for (int i = 0; i < count; i++) {
      int start = i;
      int pre = nums[i];
      do {
        start = (start + k) % length;
        int tmp = nums[start];
        nums[start] = pre;
        pre = tmp;
      } while (start != i);
    }

  }

  public static void main(String[] args) {
    int[] nums = {1, 2, 3, 4, 5, 6, 7};
    int k = 3;
    new Number189().rotate2(nums, k);
    for (int i = 0; i < nums.length; i++) {
      System.out.print(nums[i] + ",");
    }
  }

  public int gcd(int x, int y) {
    return y > 0 ? gcd(y, x % y) : x;
  }

}
