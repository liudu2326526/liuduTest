package liudu.leetcode;

public class Number75 {

  public void sortColors(int[] nums) {
    int numZero = 0;
    int numOne = 0;
    for (int num : nums) {
      if (num == 0) {
        numZero++;
      }
      if (num == 1) {
        numOne++;
      }
    }

    for (int i = 0; i < nums.length; i++) {
      if (numZero > 0) {
        nums[i] = 0;
        numZero--;
      } else {
        if (numOne > 0) {
          nums[i] = 1;
          numOne--;
        } else {
          nums[i] = 2;
        }
      }
    }
  }

}
