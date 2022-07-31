package liudu.leetcode;

public class Number200 {

  public static void main(String[] args) {
    char[][] chars = {
        {'1', '1', '1', '1', '0'},
        {'1', '1', '0', '1', '0'},
        {'1', '1', '0', '0', '0'},
        {'0', '0', '0', '0', '0'}
    };
    System.out.println(new Number200().numIslands(chars));
  }

  public int numIslands(char[][] grid) {
    int sum = 0;
    while (dfs(grid)) {
      sum++;
    }
    return sum;
  }

  private boolean dfs(char[][] grid) {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == '1') {
          dfs(i, j, grid);
          return true;
        }


      }
    }

    return false;
  }

  private void dfs(int i, int j, char[][] grid) {
    if (i >= grid.length || i < 0 || j < 0 || j >= grid[0].length) {
      return;
    }
    if (grid[i][j] == '0') {
      return;
    }
    if (grid[i][j] == '1') {
      grid[i][j] = '0';
      dfs(i + 1, j, grid);
      dfs(i, j + 1, grid);
      dfs(i - 1, j, grid);
      dfs(i, j - 1, grid);
    }

  }
}
