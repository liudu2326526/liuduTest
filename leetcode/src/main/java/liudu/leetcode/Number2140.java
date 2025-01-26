package liudu.leetcode;

public class Number2140 {

  public long mostPoints(int[][] questions) {
    long[] dp = new long[questions.length + 1];

    for (int i = dp.length - 2; i >= 0; i--) {
      int point = questions[i][0];
      int brainpower = questions[i][1];
      dp[i] = Math.max(point + dp[Math.min(i + brainpower + 1, dp.length - 1)], dp[i + 1]);
    }

    return dp[0];
  }


}
