package liudu.leetcode;

public class Number256 {

  public int minCost(int[][] costs) {
    int l = costs.length;
    for (int i = 1; i < costs.length; i++) {
      for (int j = 0; j < costs[i].length; j++) {
        costs[i][j] = costs[i][j] + Math.min(costs[i - 1][(j + 1) % 3], costs[i - 1][(j + 2) % 3]);
      }
    }

    return Math.min(Math.min(costs[l-1][0],costs[l-1][1]),costs[l-1][2]);
  }

}
