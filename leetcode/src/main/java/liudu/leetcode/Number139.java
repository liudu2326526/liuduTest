package liudu.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Number139 {

  public static void main(String[] args) {
    ArrayList<String> strings = new ArrayList<>();
    strings.add("leet");
    strings.add("code");
    System.out.println(new Number139().wordBreak("leetcode", strings));

  }

  public boolean wordBreak(String s, List<String> wordDict) {
    int n = s.length();
    HashSet<String> strings = new HashSet<>(wordDict);
    boolean[] dp = new boolean[n + 1];
    dp[0] = true;
    for (int i = 1; i <= n; i++) {
      for (int j = 0; j < i; j++) {
        if (dp[j] && strings.contains(s.substring(j, i))) {
          dp[i] = true;
          break;
        }
      }
    }

    return dp[n];
  }

}
