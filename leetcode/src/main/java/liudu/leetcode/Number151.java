package liudu.leetcode;

import java.util.Objects;

public class Number151 {

  public String reverseWords(String s) {
    StringBuilder result = new StringBuilder();
    String[] s1 = s.split(" ");
    for (int i = s1.length - 1; i >= 0; i--) {
      if (!Objects.equals(s1[i], "")){
        result.append(s1[i]).append(" ");
      }
    }
    result.deleteCharAt(result.length()-1);

    return result.toString();

  }

}
