package liudu.leetcode;

public class Number209 {


  public static void main(String[] args) {
    Number209 number209 = new Number209();
//    int[] ints = {2, 3, 1, 2, 4, 3};
//    System.out.println(number209.minSubArrayLen(7, ints));
    int[] ints = {1, 1, 1, 1, 7};
    System.out.println(number209.minSubArrayLen(7, ints));
  }

  public int minSubArrayLen(int target, int[] nums) {
    int start = 0;
    int end = 0;
    int sum = 0;
    int num = 0;
    int min = nums.length + 1;
    while (start < nums.length ) {
      if (sum < target) {
        if (end > nums.length - 1) {
          break;
        }
        sum += nums[end];
        end++;
        num++;
      } else {
        min = Math.min(min, num);
        sum -= nums[start];
        start++;
        num--;
      }
    }

    if (min == nums.length + 1) {
      return 0;
    }

    return min;
  }

}
