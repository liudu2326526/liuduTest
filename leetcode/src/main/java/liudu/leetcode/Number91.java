package liudu.leetcode;

public class Number91 {

  public static void main(String[] args) {
//    Number91 number91 = new Number91();
//    System.out.println(number91.numDecodings("06"));

    System.out.println("123456".substring(1, 3));

  }

  public int numDecodings(String s) {
    int length = s.length();
    int[] dp = new int[length + 1];

    dp[length] = 1;

    if (s.charAt(length - 1) != '0') {
      dp[length - 1] = 1;
    }

    for (int i = length - 2; i >= 0; i--) {
      char c1 = s.charAt(i);
      char c2 = s.charAt(i + 1);
      String num = s.substring(i, i + 2);
      int integer = Integer.parseInt(num);
//      String ss = c1+c2;

      if (c1 == '0') {
        dp[i] = 0;
      } else if (integer >= 10 & integer <= 26) {
        dp[i] = dp[i + 1] + dp[i + 2];
      } else {
        dp[i] = dp[i + 1];
      }
    }

    return dp[0];
  }

}
