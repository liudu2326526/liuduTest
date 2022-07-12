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
    //

  }
}
