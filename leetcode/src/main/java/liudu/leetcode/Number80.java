package liudu.leetcode;

public class Number80 {

  public static void main(String[] args) {
    int[] ints = {1, 1, 1, 2, 2, 3};
    System.out.println(new Number80().removeDuplicates(ints));
  }

  public int removeDuplicates(int[] nums) {
    int index = 1;
    boolean is_deplicate = false;
    if (nums.length == 1) {
      return 1;
    }
    int tmp = nums[0];
    for (int i = 1; i < nums.length; i++) {
      if (is_deplicate && tmp == nums[i]) {
        continue;
      }

      is_deplicate = tmp == nums[i];

      nums[index] = nums[i];
      index++;
      tmp = nums[i];

    }

    return index;
  }


}
