package liudu.leetcode;

public class Number329 {

  public static void main(String[] args) {
    int[][] xx = {
        {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
        {19, 18, 17, 16, 15, 14, 13, 12, 11, 10},
        {20, 21, 22, 23, 24, 25, 26, 27, 28, 29},
        {39, 38, 37, 36, 35, 34, 33, 32, 31, 30},
        {40, 41, 42, 43, 44, 45, 46, 47, 48, 49},
        {59, 58, 57, 56, 55, 54, 53, 52, 51, 50},
        {60, 61, 62, 63, 64, 65, 66, 67, 68, 69},
        {79, 78, 77, 76, 75, 74, 73, 72, 71, 70},
        {80, 81, 82, 83, 84, 85, 86, 87, 88, 89},
        {99, 98, 97, 96, 95, 94, 93, 92, 91, 90},
        {100, 101, 102, 103, 104, 105, 106, 107, 108, 109},
        {119, 118, 117, 116, 115, 114, 113, 112, 111, 110},
        {120, 121, 122, 123, 124, 125, 126, 127, 128, 129},
        {139, 138, 137, 136, 135, 134, 133, 132, 131, 130},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    Number329 number329 = new Number329();
    System.out.println(number329.longestIncreasingPath(xx));


  }

  int h;
  int l;
  int[][] longestPath;

  public int longestIncreasingPath(int[][] matrix) {
    h = matrix.length;
    l = matrix[0].length;

    longestPath = new int[h][l];

//    从第一个开始遍历
    int max = 0;
    for (int i = 0; i < h; i++) {
      for (int j = 0; j < l; j++) {
        max = Math.max(max, longestIncreasingPath(matrix, i, j));
      }
    }

    return max;
  }

  public int longestIncreasingPath(int[][] matrix, int i, int j) {
    int bottom = 0;
    if (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j]) {
      bottom = longestPath[i - 1][j] == 0 ? longestIncreasingPath(matrix, i - 1, j)
          : longestPath[i - 1][j];
    }

    int top = 0;
    if (i + 1 <= h - 1 && matrix[i + 1][j] > matrix[i][j]) {
      top = longestPath[i + 1][j] == 0 ? longestIncreasingPath(matrix, i + 1, j)
          : longestPath[i + 1][j];
    }

    int left = 0;
    if (j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j]) {
      left = longestPath[i][j - 1] == 0 ? longestIncreasingPath(matrix, i, j - 1)
          : longestPath[i][j - 1];
    }

    int right = 0;
    if (j + 1 <= l - 1 && matrix[i][j + 1] > matrix[i][j]) {
      right = longestPath[i][j + 1] == 0 ? longestIncreasingPath(matrix, i, j + 1)
          : longestPath[i][j + 1];
    }

    longestPath[i][j] = 1 + Math.max(
        Math.max(bottom, top),
        Math.max(left, right)
    );

    return longestPath[i][j];
  }

}
