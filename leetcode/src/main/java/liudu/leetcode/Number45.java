package liudu.leetcode;

import java.util.HashMap;

/**
 * @author liudu
 * @title: Number45
 * @projectName liuduTest
 * @date 2022/6/17下午4:27
 */
public class Number45 {

  public static void main(String[] args) {
    int[] i = {2, 3, 1, 1, 4};
    System.out.println(new Number45().jump2(i));
  }

  public int jump(int[] nums) {
    HashMap<Integer, Integer> map = new HashMap<>();
    map.put(nums.length - 1, 0);
    for (int i = nums.length - 2; i >= 0; i--) {
      if (i + nums[i] >= nums.length - 1) {
        map.put(i, 1);
      } else {
        if (nums[i] == 0) {
          map.put(i, nums.length);
        } else {
          int min = nums.length;
          for (int j = 1; j <= nums[i]; j++) {
            min = Math.min(min, map.get(i + j));
          }
          map.put(i, min + 1);
        }
      }
    }

    return map.get(0);
  }

  public int jump2(int[] nums) {

    return jump2(nums, 0, 0);
  }

  public int jump2(int[] nums, int index, int step) {
    if (index >= nums.length - 1) {
      return step;
    }
    if (nums[index] == 0) {
      return jump2(nums, index - 1, step);
    }

    return jump2(nums, index + nums[index], step + 1);
  }

}
