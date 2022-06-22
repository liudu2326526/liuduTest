package liudu.leetcode;

/**
 * @author liudu
 * @title: Number48
 * @projectName liuduTest
 * @date 2022/6/17下午2:41
 */
public class Number48 {

  public static void main(String[] args) {

    int[][] ii = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
    new Number48().rotate(ii);
  }

  public void rotate(int[][] matrix) {
    for (int i = 0; i * 2 < matrix.length; i++) {
      rotate(matrix, i);
    }

  }

  public void rotate(int[][] matrix, int num) {

    int length = matrix.length;

    for (int i = num; i < length - num - 1; i++) {
      //上到右
      int matrix1 = matrix[num][i];

      //右到下
      int matrix2 = matrix[i][length - num - 1];
      matrix[i][length - num - 1] = matrix1;

      //下到左
      int matrix3 = matrix[length - num - 1][length - 1 - i];
      matrix[length - num - 1][length - i - 1] = matrix2;

      //左到上
      int matrix4 = matrix[length - i - 1][num];
      matrix[length - 1 - i][num] = matrix3;

      matrix[num][i] = matrix4;
    }
  }

}
