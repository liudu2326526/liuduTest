package liudu.leetcode;

import java.util.HashSet;

public class Number3 {

  public static void main(String[] args) {
    Number3 number3 = new Number3();
    System.out.println(number3.lengthOfLongestSubstring("au"));


  }

  public int lengthOfLongestSubstring(String s) {
    HashSet<Character> set = new HashSet<>();

    int index = 0;
    if (s.length() == 0) {
      return 0;
    }
    if (s.length() == 1) {
      return 1;
    }

    set.add(s.charAt(0));

    int res = 0;
    for (int i = 1; i < s.length(); i++) {

      while (set.contains(s.charAt(i)) && index <= i) {
        set.remove(s.charAt(index));
        index++;
      }
      set.add(s.charAt(i));
      res = Math.max(res, i - index + 1);

    }

    return res;
  }

  public int lengthOfLongestSubstring1(String s) {
    int index = 0;
    if (s.length() == 0) {
      return 0;
    }
    if (s.length() == 1) {
      return 1;
    }

    int res = 0;

    for (int i = 1; i <= s.length(); i++) {
      while (isDuplicat(s.substring(index, i)) && index < i) {
        index++;
      }
      res = Math.max(res, i - index);

    }

    return res;

  }

  public boolean isDuplicat(String s) {
    HashSet<Character> set = new HashSet<>();
    for (int i = 0; i < s.length(); i++) {
      if (set.contains(s.charAt(i))) {
        return true;
      } else {
        set.add(s.charAt(i));
      }

    }
    return false;
  }

}
