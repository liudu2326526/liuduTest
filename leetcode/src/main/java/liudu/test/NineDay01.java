package liudu.test;

public class NineDay01 {

}


class SolutionNineDay01 {

  public int maxSubArray(int[] nums) {
    if (nums.length == 0){
      return 0;
    }
    int max = nums[0];
    for (int i = 1; i < nums.length; i++) {
      nums[i] = nums[i] + Math.max(nums[i-1],0);
      max = Math.max(nums[i],max);
    }

    return max;
  }
}