package liudu.leetcode;

public class Number221 {

  public static void main(String[] args) {
    char[][] matrix =
        {
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'}
        };

    Number221 number221 = new Number221();
    System.out.println(number221.maximalSquare(matrix));
  }

  public int maximalSquare(char[][] matrix) {
    int length = matrix[0].length;
    int high = matrix.length;

    int[][] dp = new int[high][length];

    int max = 0;

    for (int i = 0; i < dp[0].length; i++) {
      if (matrix[0][i] == '1') {
        dp[0][i] = 1;
        max = 1;
      }
    }
    for (int i = 1; i < dp.length; i++) {
      if (matrix[i][0] == '1') {
        dp[i][0] = 1;
        max = 1;
      }
    }

    for (int i = 1; i < dp.length; i++) {
      for (int j = 1; j < dp[i].length; j++) {
        if (matrix[i][j] == '1') {
          dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
          max = Math.max(max, dp[i][j]);
        } else {
          dp[i][j] = 0;
        }
      }
    }

    return max * max;
  }

}
