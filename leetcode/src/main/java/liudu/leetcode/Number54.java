package liudu.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Number54 {

  public static void main(String[] args) {
    Number54 number54 = new Number54();
    int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    System.out.println(number54.spiralOrder(matrix));
  }

  public List<Integer> spiralOrder(int[][] matrix) {
    ArrayList<Integer> integers = new ArrayList<>();
    spiralOrder(matrix, 0, integers);
    return integers;
  }

  public void spiralOrder(int[][] matrix, int num, List<Integer> list) {

    int high = matrix.length;

    if (high == 0) {
      return;
    }

    int length = matrix[0].length;

    if (length <= 2 * num || high <= 2 * num) {
      return;
    }

    if (length == 2 * num + 1) {
      for (int i = num; i < high - num; i++) {
        list.add(matrix[i][num]);
      }
      return;
    } else if (high == 2 * num + 1) {
      for (int i = num; i < length - num; i++) {
        list.add(matrix[num][i]);
      }
      return;
    }

    for (int i = num; i < length - num - 1; i++) {
      list.add(matrix[num][i]);
    }

    for (int i = num; i < high - num - 1; i++) {
      list.add(matrix[i][length - num - 1]);
    }

    for (int i = length - num - 1; i > num; i--) {
      list.add(matrix[high - num - 1][i]);
    }

    for (int i = high - num - 1; i > num; i--) {
      list.add(matrix[i][num]);
    }

    spiralOrder(matrix, num + 1, list);

  }

}
