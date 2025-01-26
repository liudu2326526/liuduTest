package liudu.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liudu
 * @title: Number15
 * @projectName liuduTest
 * @description: 三数之和
 * @date 2022/7/15下午4:23
 */
public class Number15 {

  public static void main(String[] args) {
    Number15 number15 = new Number15();
//    int[] ints = {-2, 0, 0, 2, 2};
    int[] ints = {0, 0, 0};
    List<List<Integer>> lists = number15.threeSum(ints);
    System.out.println(lists);

  }

  public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);
    ArrayList<List<Integer>> result = new ArrayList<>();

    for (int i = 0; i < nums.length; i++) {
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }

      int j = i + 1;
      int k = nums.length - 1;

      while (j < k) {
        if (nums[i] + nums[j] + nums[k] < 0) {
          j++;
        } else if (nums[i] + nums[j] + nums[k] > 0) {
          k--;
        } else {
          ArrayList<Integer> integers = new ArrayList<>();
          integers.add(nums[i]);
          integers.add(nums[j]);
          integers.add(nums[k]);
          result.add(integers);
          j++;
          k--;
        }

        while (k < nums.length - 1 && nums[k] == nums[k + 1] && j < k) {
          k--;
        }

        while (j > i + 1 && nums[j] == nums[j - 1] && j < k) {
          j++;
        }


      }


    }

    return result;
  }
}
