package liudu.leetcode;

public class Number35 {

  public static void main(String[] args) {
    Number35 number35 = new Number35();
    System.out.println(number35.searchInsert(new int[]{1}, 0));
  }

  public int searchInsert(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    while (left <= right) {
      int mid = (left + right) / 2;
      if (nums[mid] == target) {
        return mid;
      } else if (nums[mid] < target) {
        left = mid + 1;
      } else {
        right = mid -1;
      }
    }

    return left;
  }


  public int searchInsert(int[] nums, int target, int left, int right) {
    if (left == right) {
      if (nums[left] >= target) {
        return left;
      } else {
        return right + 1;
      }
    }
    if (left + 1 == right) {
      if (nums[left] == target) {
        return left;
      }
      if (nums[right] == target) {
        return right;
      }
      if (nums[right] < target) {
        return right + 1;
      }
      return right;
    }
    int mid = (left + right + 1) / 2;
    if (nums[mid] == target) {
      return mid;
    } else if (nums[mid] < target) {
      return searchInsert(nums, target, mid, right);
    } else {
      return searchInsert(nums, target, left, mid);
    }
  }


}
