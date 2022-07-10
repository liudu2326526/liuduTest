package liudu.leetcode;

public class Number238 {

  public static void main(String[] args) {
    Number238 number238 = new Number238();
    int[] ints = {-1, 1, 0, -3, 3};
    number238.productExceptSelf(ints);
  }

  public int[] productExceptSelf(int[] nums) {
    if (nums.length == 1) {
      int[] ints = {0};
      return ints;
    }
    int[] left = new int[nums.length];
    left[0] = nums[0];
    int[] right = new int[nums.length];
    right[nums.length - 1] = nums[nums.length - 1];
    for (int i = 1; i < nums.length; i++) {
      left[i] = left[i - 1] * nums[i];
    }
    for (int i = nums.length - 2; i >= 0; i--) {
      right[i] = right[i + 1] * nums[i];
    }
    int[] result = new int[nums.length];
    result[0] = right[1];
    result[nums.length - 1] = left[nums.length - 2];

    for (int i = 1; i < nums.length - 1; i++) {
      result[i] = left[i - 1] * right[i + 1];
    }

    return result;
  }

}
