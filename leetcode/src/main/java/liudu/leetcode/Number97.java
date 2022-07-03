package liudu.leetcode;

/**
 * 97. 交错字符串
 * <p>
 * 给定三个字符串s1、s2、s3，请你帮忙验证s3是否是由s1和s2 交错 组成的。
 * <p>
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
 * <p>
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 注意：a + b 意味着字符串 a 和 b 连接。
 */
public class Number97 {


  public static void main(String[] args) {
    System.out.println(new Number97().isInterleave("aabcc", "dbbca", "aadbbbaccc"));
  }

  public boolean isInterleave(String s1, String s2, String s3) {
    int n = s1.length(), m = s2.length(), t = s3.length();
    if ((m + n) != t) {
      return false;
    }

    boolean[][] dp = new boolean[n + 1][m + 1];
    dp[0][0] = true;
    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= m; j++) {
        int p = i + j - 1;
        if (i > 0) {
          dp[i][j] = dp[i][j] || (dp[i - 1][j] && s3.charAt(p) == s1.charAt(i - 1));
        }
        if (j > 0) {
          dp[i][j] = dp[i][j] || (dp[i][j - 1] && s3.charAt(p) == s2.charAt(j - 1));
        }

      }
    }

    return dp[n][m];
  }


}
