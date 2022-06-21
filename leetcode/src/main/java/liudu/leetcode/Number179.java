package liudu.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Number179 {

  public static void main(String[] args) {
    String[] strings = {"96", "9", "988", "982", "99", "999"};
    Arrays.sort(strings);

    System.out.println(Arrays.stream(strings).collect(Collectors.toList()));
  }

  public String largestNumber(int[] nums) {

    ArrayList<Integer> list = new ArrayList<>();
    for (int num : nums) {
      list.add(num);
    }
    list.sort((o1, o2) -> {
      String s1 = "" + o2 + o1;
      String s2 = "" + o1 + o2;
      return s2.compareTo(s1);
    });

    StringBuilder result = new StringBuilder();

    if (list.size()>0 && list.get(0) == 0){
      return  "";
    }

    for (Integer integer : list) {
      result.append(integer);
    }

    return result.toString();
  }

}
