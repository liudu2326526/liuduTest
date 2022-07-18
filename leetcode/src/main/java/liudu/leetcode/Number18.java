package liudu.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Number18 {


  public List<List<Integer>> fourSum(int[] nums, int target) {
    Arrays.sort(nums);
    ArrayList<List<Integer>> result = new ArrayList<>();

    for (int i = 0; i < nums.length; i++) {
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }
      if (target < 0 && nums[i] > 0) {
        break;
      }

      for (int j = i + 1; j < nums.length; j++) {
        if (j > i + 1 && nums[j] == nums[j - 1]) {
          continue;
        }
        if (target < 0 && nums[i] + nums[j] > 0) {
          break;
        }

        int l = j + 1;
        int r = nums.length - 1;
        while (l < r) {
          int tmp = nums[i] + nums[j] + nums[l] + nums[r];
          if (tmp > target) {
            r--;
          } else if (tmp < target) {
            l++;
          } else {
            result.add(Arrays.asList(nums[j], nums[i], nums[l], nums[r]));
            while (l < r && nums[l] == nums[l + 1]) {
              l++;
            }
            while (l < r && nums[r] == nums[r - 1]) {
              r--;
            }
            r--;
            l++;
          }


        }

      }
    }

    return result;
  }

}
