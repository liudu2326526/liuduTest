package liudu.leetcode;

public class Number55 {

  public boolean canJump(int[] nums) {
    int max = 0;
    for (int i = 0; i < nums.length; i++) {
      if (i > max) {
        return false;
      }
      if (max > nums.length){
        return true;
      }
      max = Math.max(max, i + nums[i]);
    }
    return true;
  }
}
