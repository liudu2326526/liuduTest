package liudu.leetcode;

public class Number72 {

  public static void main(String[] args) {
    Number72 number72 = new Number72();

    System.out.println(number72.minDistance("horse", "ros"));

  }


  public int minDistance(String word1, String word2) {
    int length1 = word1.length();
    int length2 = word2.length();
    int[][] ints = new int[length1 + 1][length2 + 1];
    for (int i = 0; i < ints.length; i++) {
      ints[i][0] = i;
    }
    for (int i = 0; i < ints[0].length; i++) {
      ints[0][i] = i;
    }
    for (int i = 1; i < ints.length; i++) {
      for (int j = 1; j < ints[i].length; j++) {
        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
          ints[i][j] =
              1 + Math.min(Math.min(ints[i - 1][j], ints[i][j - 1]), ints[i - 1][j - 1] - 1);
        } else {
          ints[i][j] =
              1 + Math.min(Math.min(ints[i - 1][j], ints[i][j - 1]), ints[i - 1][j - 1]);
        }
      }
    }

    return ints[length1][length2];
  }

}
