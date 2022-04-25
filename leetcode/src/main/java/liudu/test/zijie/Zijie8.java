package liudu.test.zijie;

import java.util.Arrays;

public class Zijie8 {

  public int[] twoSum(int[] nums, int target) {
//    O(nlog2n)
    int[] clone = nums.clone();
    Arrays.sort(nums);
    int left = 0;
    int right = nums.length - 1;
    while (left < right) {
      if (nums[left] + nums[right] > target) {
        right--;
      } else if (nums[left] + nums[right] < target) {
        left++;
      } else {
        break;
      }
    }

    int[] result = new int[2];
    int index1 = -1;
    int index2 = -1;
    for (int i = 0; i < clone.length; i++) {
      if (index1 < 0 &&clone[i] == nums[left]){
        index1 = i;
      }
      if (index2 < 0  && clone[i] == nums[right]){
        index2 = i;
      }
    }

    result[0] = index1;
    result[1] = index2;

    return result;
  }
}
