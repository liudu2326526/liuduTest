package liudu.leetcode;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;

public class Number30 {

  public static void main(String[] args) {

    String s = "ababababab";
    String[] words = {"ababa","babab"};
    Number30 number30 = new Number30();
    System.out.println(number30.findSubstring(s, words));
  }

  public List<Integer> findSubstring(String s, String[] words) {
    int wordLength = words[0].length();
    int length = wordLength * words.length;
    ArrayList<Integer> result = new ArrayList<>();
    if (s.length() < length) {
      return result;
    }
    HashMap<String, Integer> map = new HashMap<>();
    ArrayList<String> strings = new ArrayList<>();
    HashMap<String, Integer> target = new HashMap<>();

    for (String word : words) {
      if (target.containsKey(word)) {
        target.put(word, target.get(word) + 1);
      } else {
        target.put(word, 1);
      }

    }

    for (int i = 0; i < wordLength; i++) {
      for (int j = i; j + wordLength <= s.length(); j += wordLength) {
        String substring = s.substring(j, j + wordLength);

        if (target.containsKey(substring)) {
          strings.add(substring);
          if (map.containsKey(substring)) {
            map.put(substring, map.get(substring) + 1);
          } else {
            map.put(substring, 1);
          }

          if (map.equals(target)) {
            result.add(j - length + wordLength);
          }

          if (strings.size() == words.length) {
            String remove = strings.remove(0);
            if (map.get(remove) == 1) {
              map.remove(remove);
            } else {
              map.put(remove, map.get(remove) - 1);
            }
          }


        } else {
          map.clear();
          strings.clear();
//          set.clear();
        }

      }
      map.clear();
      strings.clear();
    }

    return result;
  }

}
