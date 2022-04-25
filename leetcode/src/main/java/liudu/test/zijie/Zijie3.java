package liudu.test.zijie;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Zijie3 {

  public static void main(String[] args) {
    System.out.println(lengthOfLongestSubstring("pwwkew"));
  }

  public static int lengthOfLongestSubstring(String s) {
    char[] chars = s.toCharArray();
    int result = 0;
    HashSet<Character> set = new HashSet<>();
    int r = 0;
    for (int i = 0; i < chars.length; i++) {
      if (i != 0) {
        set.remove(chars[i - 1]);
      }

      while (r < chars.length && !set.contains(chars[r])) {
        set.add(chars[r]);
        r++;
      }
      result = Math.max(result, r - i);
    }

    return result;
  }

  public static int lengthOfLongestSubstring1(String s) {
    // 哈希集合，记录每个字符是否出现过
    Set<Character> occ = new HashSet<Character>();
    int n = s.length();
    // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
    int rk = -1, ans = 0;
    for (int i = 0; i < n; ++i) {
      if (i != 0) {
        // 左指针向右移动一格，移除一个字符
        occ.remove(s.charAt(i - 1));
      }
      while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
        // 不断地移动右指针
        occ.add(s.charAt(rk + 1));
        ++rk;
      }
      // 第 i 到 rk 个字符是一个极长的无重复字符子串
      ans = Math.max(ans, rk - i + 1);
    }
    return ans;
  }

}
