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

  public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);
    ArrayList<List<Integer>> result = new ArrayList<>();

    for (int a = 0; a < nums.length - 1; a++) {
      if (a > 0 && nums[a] == nums[a - 1]) {
        continue;
      }

      int left = a + 1;
      int right = nums.length - 1;
      while (left < right) {
        while (nums[a] + nums[left] + nums[right] > 0) {
          right--;
        }
        if (left == right){
          break;
        }
        if (nums[a] + nums[left] + nums[right] == 0) {
          ArrayList<Integer> list = new ArrayList<>();
          list.add(nums[a]);
          list.add(nums[right]);
          list.add(nums[left]);
          result.add(list);
        }
        left++;
      }
    }
    return result;
  }
}
