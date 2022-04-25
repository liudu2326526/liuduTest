package liudu.test.zijie;

import java.util.HashSet;

public class Zijie6 {

  public static void main(String[] args) {

  }

  public int longestConsecutive(int[] nums) {

    HashSet<Integer> set = new HashSet<>();

    for (int num : nums) {
      set.add(num);
    }
    int result = 0;

    for (Integer integer : set) {
      int num = integer;
      int length = 0;
      if (!set.contains(num - 1)) {
        length++;
        while (set.contains(num + 1)) {
          length++;
          num++;
        }
      }
      result = Math.max(result, length);

    }

    return result;
  }

}
