package liudu.leetcode;

import java.util.Arrays;

public class Number167 {

  public static void main(String[] args) {
    Number167 number167 = new Number167();
    int[] ints = {2, 7, 11, 15};
    System.out.println(Arrays.toString(number167.twoSum(ints, 9)));
  }

  public int[] twoSum(int[] numbers, int target) {
    int[] ints = new int[2];
    int l = 0;
    int r = numbers.length - 1;
    while (l < r) {
      if (numbers[l] + numbers[r] == target) {
        ints[0] = l + 1;
        ints[1] = r + 1;
        break;
      } else if (numbers[l] + numbers[r] > target) {
        r--;
      } else {
        l++;
      }
    }

    return ints;
  }


}
