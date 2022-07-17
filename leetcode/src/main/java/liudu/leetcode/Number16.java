package liudu.leetcode;

import java.util.Arrays;

//给你一个长度为 n 的整数数组nums和 一个目标值target。请你从 nums 中选出三个整数，使它们的和与target最接近。
//
//返回这三个数的和。
//
//假定每组输入只存在恰好一个解。
//
public class Number16 {

  public int threeSumClosest(int[] nums, int target) {
    Arrays.sort(nums);

    int diff = Integer.MAX_VALUE;
    int tmp = 0;

    for (int i = 0; i < nums.length; i++) {
      int l = i + 1;
      int r = nums.length - 1;
      while (l < r) {
        int sum = nums[i] + nums[l] + nums[r];
        if (sum > target) {
          if (sum - target < diff) {
            diff = sum - target;
            tmp = sum;
          }
          r--;
        } else if (sum < target) {
          if (target - sum < diff) {
            diff = target - sum;
            tmp = sum;
          }
          l++;
        } else {
          return target;
        }
      }
    }
    return tmp;
  }

}
