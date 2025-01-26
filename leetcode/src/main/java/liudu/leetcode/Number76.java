package liudu.leetcode;

import java.util.HashMap;
import java.util.HashSet;

public class Number76 {


  public String minWindow(String s, String t) {
//    存储 t 中字符数
    HashMap<Character, Integer> tMap = new HashMap<>();

    HashSet<Character> set = new HashSet<>();
    for (int i = 0; i < t.length(); i++) {
      set.add(t.charAt(i));
      if (tMap.containsKey(t.charAt(i))) {
        tMap.put(t.charAt(i), tMap.get(t.charAt(i)) + 1);
      } else {
        tMap.put(t.charAt(i), 1);
      }
    }

//    存储多余的字符
    HashMap<Character, Integer> sMap = new HashMap<>();

//    遍历 s ，找到最小的滑动窗口
    int index = 0;
    for (int i = 0; i < s.length(); i++) {


    }

    return "";
  }
}
