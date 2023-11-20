package liudu.leetcode;

public class Number73 {

  public static void main(String[] args) {
//    int[][] matrix = {{
//        0, 1, 2, 0
//    }, {
//        3, 4, 5, 2
//    }, {
//        1, 3, 1, 5
//    }};

    int[][] matrix = {{1,0,3}};

    new Number73().setZeroes(matrix);
  }


  public void setZeroes(int[][] matrix) {
    boolean row0 = false;
    boolean col0 = false;
    int m = matrix.length;
    if (m == 0) {
      return;
    }
    int n = matrix[0].length;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (matrix[i][j] == 0) {
          matrix[i][0] = 0;
          matrix[0][j] = 0;
          if (i == 0) {
            row0 = true;
          }
          if (j == 0) {
            col0 = true;
          }
        }
      }
    }

    for (int i = 1; i < n; i++) {
      if (matrix[0][i] == 0) {
        for (int j = 0; j < m; j++) {
          matrix[j][i] = 0;
        }
      }
    }

    for (int i = 1; i < m; i++) {
      if (matrix[i][0] == 0) {
        for (int j = 0; j < n; j++) {
          matrix[i][j] = 0;
        }
      }
    }

    if (col0) {
      for (int i = 0; i < m; i++) {
        matrix[i][0] = 0;
      }
    }

    if (row0){
      for (int i = 0; i < n; i++) {
        matrix[0][i] = 0;
      }
    }

  }


}
