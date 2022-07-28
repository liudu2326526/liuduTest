package liudu.leetcode;

public class Number34 {

  public static void main(String[] args) {
    int[] ints = {5, 7, 7, 8, 8, 10};
    new Number34().searchRange(ints, 6);
  }

  public int[] searchRange(int[] nums, int target) {
    int l = 0;
    int r = nums.length - 1;
    int mid = 0;

    while (l < r) {
      mid = (l + r) / 2;
      if (nums[mid] == target) {
        break;
      } else if (nums[mid] > target) {
        r = mid - 1;
      } else {
        l = mid + 1;
      }
    }

    if (l == r && nums[l] == target){
      return new int[]{r, r};
    }

    if (l > r) {
      return new int[]{-1, -1};
    }

    int[] result = {0, nums.length - 1};
    for (int i = mid; i < nums.length; i++) {
      if (nums[i] != target) {
        result[1] = i - 1;
        break;
      }
    }

    for (int i = mid; i >= 0; i--) {
      if (nums[i] != target) {
        result[0] = i + 1;
        break;
      }
    }

    return result;
  }

}
