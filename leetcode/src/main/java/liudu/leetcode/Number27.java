package liudu.leetcode;

public class Number27 {


  public int removeElement(int[] nums, int val) {
    int index = nums.length - 1;
    for (int i = 0; i <= index; ) {
      if (nums[i] == val) {
        int tmp = nums[index];
        nums[index] = val;
        nums[i] = tmp;
        index--;
      } else {
        i++;
      }
    }
    if (index == 0){
      nums = new int[]{};
    }

    return index + 1;
  }

}
