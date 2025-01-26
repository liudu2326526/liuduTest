package liudu.leetcode;

import java.util.HashSet;

public class Number260 {

  public int[] singleNumber(int[] nums) {
    HashSet<Integer> set = new HashSet<>();
    for (int num : nums) {
      if (set.contains(num)){
        set.remove(num);
      } else {
        set.add(num);
      }
    }
    int[] ints = new int[2];
    int i = 0;
    for (Integer integer : set) {
      ints[i]= integer;
      i++;
     }


    return ints;
  }

}
