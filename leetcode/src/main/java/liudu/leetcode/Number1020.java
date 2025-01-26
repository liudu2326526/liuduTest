package liudu.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Number1020 {

  public static void main(String[] args) {
    Number1020 number1020 = new Number1020();

    int[][] ints = {{
        0, 1, 1, 0
    }, {
        0, 0, 1, 0
    }, {
        0, 0, 1, 0
    }, {
        0, 0, 0, 0
    }};

    System.out.println(number1020.numEnclaves(ints));


  }

  public int numEnclaves(int[][] grid) {

    int sum = 0;

    boolean[][] visible = new boolean[grid.length][grid[0].length];

    ArrayList<Boolean> booleans = new ArrayList<>();

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (!visible[i][j] && grid[i][j] == 1) {

          int dfs = dfs(grid, visible, i, j, booleans);

          System.out.println(booleans.size());
          if (booleans.size() > 0) {
            booleans.clear();
          } else {
            sum += dfs;
          }


        }
      }
    }

    return sum;
  }


  public int dfs(int[][] ints, boolean[][] visible, int y, int x, List<Boolean> isBoundary) {
    int high = ints.length;
    int length = ints[0].length;

    //    出界情况
    if (y < 0 || x < 0 || y > high - 1 || x > length - 1) {
      return 0;
    }
    //    到过的情况
    if (visible[y][x]) {
      return 0;
    }



    visible[y][x] = true;

    if (ints[y][x] == 1) {
      if (y == 0 || x == 0 || y == high - 1 || x == length - 1) {
        isBoundary.add(false);
      }
      return 1 +
          dfs(ints, visible, y + 1, x, isBoundary) +
          dfs(ints, visible, y - 1, x, isBoundary) +
          dfs(ints, visible, y, x + 1, isBoundary) +
          dfs(ints, visible, y, x - 1, isBoundary);


    } else {
      return 0;
    }


  }

}
