package liudu.leetcode;

public class Number516 {

  public int longestPalindromeSubseq(String s) {
    int l = s.length();
    int[][] ints = new int[l][l];
    for (int i = 0; i < l; i++) {
      ints[i][i] = 1;
    }
    for (int i = l - 1; i >= 0; i--) {
      for (int j = i + 1; j < l; j++) {
        if (s.charAt(i) == s.charAt(j)) {
          ints[i][j] = ints[i + 1][j - 1] + 2;
        } else {
          ints[i][j] = Math.max(ints[i + 1][j], ints[i][j - 1]);
        }
      }
    }

    return ints[0][l - 1];
  }

}
