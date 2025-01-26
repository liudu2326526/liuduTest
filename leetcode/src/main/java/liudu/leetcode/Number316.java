package liudu.leetcode;

import java.util.ArrayDeque;
import java.util.HashSet;

public class Number316 {

  public String removeDuplicateLetters(String s) {
    int length = s.length();
    char[] chars = s.toCharArray();
    int[] lastIndex = new int[26];
    for (int i = 0; i < chars.length; i++) {
      lastIndex[chars[i] - 'a'] = i;
    }

    ArrayDeque<Character> stak = new ArrayDeque<>();
    boolean[] booleans = new boolean[26];

    for (int i = 0; i < chars.length; i++) {
      if (booleans[chars[i] - 'a']) {
        continue;
      }
      while (!stak.isEmpty() && stak.getLast() > chars[i] && lastIndex[stak.getLast() - 'a'] > i) {
        Character top = stak.removeLast();
        booleans[top - 'a'] = false;
      }

      stak.addLast(chars[i]);
      booleans[chars[i] - 'a'] = true;
    }

    StringBuilder stringBuilder = new StringBuilder();
    for (char character : stak) {
      stringBuilder.append(character);
    }

    return stringBuilder.toString();
  }

}
