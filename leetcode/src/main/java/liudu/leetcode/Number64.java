package liudu.leetcode;

public class Number64 {

  public static void main(String[] args) {

//    int[][] lists = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
//    int[][] lists = {{1, 2, 3}, {4, 5, 6}};
    int[][] lists = {{0}};
    int i = new Number64().minPathSum(lists);
    System.out.println(i);
  }

  public int minPathSum(int[][] grid) {

    return minPathSum(grid, 0);
  }

  public int minPathSum(int[][] grid, int num) {
    int high = grid.length;
    int length = grid[0].length;
    if (num == 0) {
      for (int i = 1; i < high; i++) {
        grid[i][num] = grid[i - 1][num] + grid[i][num];
      }

      for (int i = 1; i < length; i++) {
        grid[num][i] = grid[num][i - 1] + grid[num][i];
      }

      if (num == high - 1 || num == length - 1) {
        return grid[high - 1][length - 1];
      }

    } else if (length > high && num == high - 1) {
      for (int i = num; i < length; i++) {
        grid[num][i] = Math.min(grid[num - 1][i], grid[num][i - 1]) + grid[num][i];
      }
      return grid[high - 1][length - 1];
    } else if (length <= high && num == length - 1) {
      for (int i = num; i < high; i++) {
        grid[i][num] = Math.min(grid[i - 1][num], grid[i][num - 1]) + grid[i][num];
      }
      return grid[high - 1][length - 1];
    } else {
      for (int i = num; i < high; i++) {
        grid[i][num] = Math.min(grid[i - 1][num], grid[i][num - 1]) + grid[i][num];
      }
      for (int i = num + 1; i < length; i++) {
        grid[num][i] = Math.min(grid[num - 1][i], grid[num][i - 1]) + grid[num][i];
      }
    }
    return minPathSum(grid, num + 1);
  }

}
