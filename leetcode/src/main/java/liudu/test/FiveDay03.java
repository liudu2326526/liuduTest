package liudu.test;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class FiveDay03 {

  public static void main(String[] args) {
    System.out.println(new SolutionFiveDay03().firstUniqChar("leetcode"));
  }

}


class SolutionFiveDay03 {

  public char firstUniqChar(String s) {
    int[] bitmap = new int[26];
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = 0; i < s.length(); i++) {
      bitmap[s.charAt(i) - 97]++;
    }

    for (int i = 0; i < bitmap.length; i++) {
      if (bitmap[i] == 1) {
        list.add(i);
      }
    }

    for (int i = 0; i < s.length(); i++) {
      if (list.contains(s.charAt(i) - 97)){
        return s.charAt(i);
      }
    }
    return ' ';
  }
}