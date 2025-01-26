package liudu.leetcode;

public class Number712 {

  public static void main(String[] args) {
    Number712 number712 = new Number712();

    System.out.println(number712.minimumDeleteSum("delete", "leet"));

  }


  public int minimumDeleteSum(String s1, String s2) {
    int[][] dp = new int[s1.length() + 1][s2.length() + 1];
    for (int i = 1; i < dp.length; i++) {
      dp[i][0] = dp[i - 1][0] + s1.charAt(i-1);
    }

    for (int i = 1; i < dp[0].length; i++) {
      dp[0][i] = dp[0][i - 1] + s2.charAt(i-1);
    }

    for (int i = 1; i < dp.length; i++) {
      char si = s1.charAt(i-1);
      for (int j = 1; j < dp[i].length; j++) {
        char sj = s2.charAt(j-1);
        if (si == sj) {
          dp[i][j] = Math.min(
              dp[i - 1][j - 1],
              Math.min(
                  dp[i - 1][j] + si,
                  dp[i][j - 1] + sj
              )
          );
        } else {
          dp[i][j] = Math.min(
              dp[i - 1][j - 1] + si + sj,
              Math.min(
                  dp[i - 1][j] + si,
                  dp[i][j - 1] + sj
              )
          );
        }


      }
    }

    return dp[s1.length()][s2.length()];
  }


}
