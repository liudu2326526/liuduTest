package liudu.leetcode;

import java.util.Arrays;

public class Number474 {

  public static void main(String[] args) {
    Number474 number474 = new Number474();
//    String[] strings = {"10","0001","111001","1","0"};
    String[] strings = {"10","0","1"};
//    System.out.println(number474.findMaxForm(strings, 5, 3));
    System.out.println(number474.findMaxForm(strings, 1, 1));
  }



  public int findMaxForm(String[] strs, int m, int n) {
    int l = strs.length;

    int[][] numCount = new int[l][2];

    for (int i = 0; i < strs.length; i++) {
      int numZero = 0;
      int numOne = 0;
      for (int j = 0; j < strs[i].length(); j++) {
        if (strs[i].charAt(j) == '0') {
          numZero++;
        } else {
          numOne++;
        }
      }
      numCount[i][0] = numZero;
      numCount[i][1] = numOne;
    }

    int[][][] ints = new int[l + 1][m + 1][n + 1];

    for (int i = 1; i < ints.length; i++) {
      int zero = numCount[i - 1][0];
      int one = numCount[i - 1][1];
      for (int j = 0; j < ints[i].length; j++) {
        for (int k = 0; k < ints[i][j].length; k++) {

          if (zero <= j && one <= k) {
            ints[i][j][k] = Math.max(ints[i - 1][j][k], ints[i - 1][j - zero][k - one] +1);
          } else {
            ints[i][j][k] = ints[i - 1][j][k];
          }

        }
      }
    }

    return ints[l][m][n];
  }


}
