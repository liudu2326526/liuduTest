package liudu.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Number300 {


  public static void main(String[] args) {

    Number300 number300 = new Number300();
    ArrayList<Integer> integers = new ArrayList<>();
    integers.add(1);
    integers.add(3);
    integers.add(5);
    integers.add(7);
    integers.add(9);
    System.out.println(number300.binarySearch(integers, 6, 0, integers.size() - 1));

    System.out.println(integers);
    integers.remove(1);
    integers.add(1, 2);
    System.out.println(integers);

  }


  public int lengthOfLIS(int[] nums) {
    ArrayList<Integer> integers = new ArrayList<>();
    integers.add(nums[0]);
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] > integers.get(integers.size() - 1)) {
        integers.add(nums[i]);
      } else {
        int index = binarySearch(integers, nums[i], 0, integers.size() - 1);
        integers.remove(index);
        integers.add(index, nums[i]);
      }
    }

    return integers.size();
  }

  public int binarySearch(List<Integer> list, int value, int left, int right) {
    if (left == right) {
      return left;
    }

    int mid = (left + right) / 2;

    if (list.get(mid) > value) {
      return binarySearch(list, value, left, mid);
    } else if (list.get(mid) < value) {
      return binarySearch(list, value, mid + 1, right);
    } else {
      return mid;
    }
  }

}
