package liudu.leetcode;

/**
 * @author liudu
 * @title: Number33
 * @projectName liuduTest
 * @description: TODO
 * @date 2022/6/10下午4:49
 */
public class Number33 {

  public static void main(String[] args) {
    System.out.println(new Number33().search(new int[]{1}, 1));
  }

  public int search(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    while (left < right) {
      int mid = (left + right) / 2;
      if (nums[mid] == target) {
        return mid;
      }
      if (nums[left] == target) {
        return left;
      }
      if (nums[right] == target) {
        return right;
      }
      if (nums[left] < nums[mid]) {
        if (nums[left] < target && target < nums[mid]) {
          right = mid - 1;
        } else {
          left = mid + 1;
        }
      } else {
        if (nums[mid] < target && target < nums[right]) {
          left = mid + 1;
        } else {
          right = mid - 1;
        }
      }

    }
    return -1;
  }

}
