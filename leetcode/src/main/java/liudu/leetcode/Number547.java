package liudu.leetcode;

public class Number547 {

  public int findCircleNum(int[][] isConnected) {
    int length = isConnected.length;
    boolean[] visible = new boolean[length];
    int num = 0;

    for (int i = 0; i < length; i++) {
      if (!visible[i]) {
        dfs(isConnected, visible, length, i);
        num++;
      }

    }

    return num;
  }


  public void dfs(int[][] isConnected, boolean[] visible, int cities, int index) {
    for (int i = 0; i < cities; i++) {
      if (isConnected[index][i] == 1 && !visible[i]) {
        visible[i] = true;
        dfs(isConnected, visible, cities, i);
      }
    }
  }

}
