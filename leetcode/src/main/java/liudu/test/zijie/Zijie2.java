package liudu.test.zijie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Zijie2 {

  public static void main(String[] args) {
    System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
  }


  public static List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);

    for (int i = 0; i < nums.length; i++) {
      System.out.print(nums[i] + ",");
    }
    System.out.println("\n");

    ArrayList<List<Integer>> result = new ArrayList<>();

    for (int i = 0; i < nums.length - 1; i++) {
      if (nums[i] > 0){
        return result;
      }

      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }
      int l = i + 1;
      int r = nums.length - 1;
      while (l < r) {
        int sum = nums[i] + nums[l] + nums[r];
        System.out.println(sum);
        if (sum > 0) {
          r--;
        } else if (sum < 0) {
          l++;
        } else {
          result.add(Arrays.asList(nums[i], nums[l], nums[r]));
          System.out.println("l:" + l);
          System.out.println("r:" + r);
          while (l < r && nums[l] == nums[l + 1]) {
            l++;
          }
          while (l < r && nums[r] == nums[r - 1]) {
            r--;
          }
          l++;
          r--;

        }

      }
    }
    return result;
  }
}
