package liudu.leetcode;

import java.util.HashMap;
import java.util.Map.Entry;

public class Number169 {

  public static void main(String[] args) {
    int[] ints = {1, 2, 2, 2, 1, 1, 1, 2, 2, 3, 2};
    new Number169().majorityElement(ints);

  }

  public int majorityElement(int[] nums) {
    int count = 0;
    Integer candidate = null;

    for (int num : nums) {
      if (count == 0) {
        candidate = num;
      }
      count += (num == candidate) ? 1 : -1;
    }

    return candidate;

  }

  public int majorityElement2(int[] nums) {

    HashMap<Integer, Integer> maps = new HashMap<>();

    for (int num : nums) {
      if (!maps.containsKey(num)) {
        maps.put(num, 1);
      } else {
        maps.put(num, maps.get(num) + 1);
      }
    }

    for (Entry<Integer, Integer> integerIntegerEntry : maps.entrySet()) {
      if (integerIntegerEntry.getValue() > nums.length / 2) {
        return integerIntegerEntry.getKey();
      }

    }
    return 0;
  }

}
