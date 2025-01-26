package liudu.leetcode;

public class Number43 {

  public static void main(String[] args) {
//    int[][] ints = {{1}};
    int[][] ints = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};

    Number43 number43 = new Number43();
    System.out.println(number43.searchMatrix(ints, 3));


  }


  public boolean searchMatrix(int[][] matrix, int target) {
    int wight = matrix[0].length;
    int high = matrix.length;

    int left = 0;
    int right = wight * high - 1;

    while (left <= right) {
      int mid = (left + right) / 2;
      int number = getNumber(matrix, mid);
      if (number > target) {
        right = mid - 1;
      } else if (number < target) {
        left = mid + 1;
      } else {
        return true;
      }
    }
    return false;
  }

  public int getNumber(int[][] matrix, Integer number) {
    int wight = matrix[0].length;

    return matrix[number / wight][number % wight];
  }
}
