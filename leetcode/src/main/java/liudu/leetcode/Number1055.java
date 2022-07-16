package liudu.leetcode;

import java.util.HashSet;

public class Number1055 {

  public static void main(String[] args) {
    System.out.println(new Number1055().shortestWay("xyz", "xzyxz"));
  }

  public int shortestWay(String source, String target) {
    HashSet<Character> set = new HashSet<>();
    for (char c : source.toCharArray()) {
      set.add(c);
    }
    for (char c : target.toCharArray()) {
      if (!set.contains(c)) {
        return -1;
      }
    }

    int start = 0;
    int end = 1;
    int[] dp = new int[target.length()];
    dp[0] = 1;
    for (int i = 1; i < target.length(); i++) {
      end = i + 1;
      if (isChild(source, target.substring(start, end))) {
        dp[i] = dp[i - 1];
      } else {
        dp[i] = dp[i - 1] + 1;
        start = i;
      }
    }

    return dp[target.length() - 1];
  }

  public boolean isChild(String father, String child) {
    int index = 0;
    for (int i = 0; i < father.length(); i++) {
      if (father.charAt(i) == child.charAt(index)) {
        index++;
        if (index == child.length()) {
          return true;
        }
      }
    }
    return false;
  }

}
