package liudu.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;


class Tuple2 {

  public String s;
  public Integer i;

  public Tuple2(String s, Integer i) {
    this.s = s;
    this.i = i;
  }

}

public class Number433 {

  public static void main(String[] args) {
    String[] strings = {"AACCGATT", "AACCGATA", "AAACGATA", "AAACGGTA"};
//    String[] strings = {"AACCGGTA", "AACCGCTA", "AAACGGTA"};

    Number433 number433 = new Number433();
    System.out.println(number433.minMutation("AACCGGTT", "AAACGGTA", strings));
//    System.out.println(number433.minMutation("AACCGGTT", "AAACGGTA", strings));

  }


  public int minMutation(String start, String end, String[] bank) {

    LinkedList<Tuple2> strings = new LinkedList<>();

    HashSet<String> set = new HashSet<>(Arrays.asList(bank));

    if (!set.contains(end)) {
      return -1;
    }

    strings.add(new Tuple2(start, 0));

    while (!strings.isEmpty()) {
      Tuple2 remove = strings.remove();

      if (Objects.equals(remove.s, end)){
        return remove.i;
      }

      ArrayList<String> tmpList = new ArrayList<>();
      for (String s : set) {
        if (isNextStep(remove.s, s)) {
          strings.add(new Tuple2(s, remove.i + 1));
          tmpList.add(s);
        }
      }

      for (String s : tmpList) {
        set.remove(s);
      }
    }

    return -1;
  }


  public boolean isNextStep(String start, String end) {
    int tag = 0;

    for (int i = 0; i < start.length(); i++) {
      if (start.charAt(i) != end.charAt(i)) {
        tag++;
      }
      if (tag > 1) {
        return false;
      }
    }
    return tag != 0;
  }


}
