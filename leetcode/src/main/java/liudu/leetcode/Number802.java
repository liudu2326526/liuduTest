package liudu.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Number802 {

  public List<Integer> eventualSafeNodes(int[][] graph) {

    boolean[] visible = new boolean[graph.length];
    boolean[] isSafe = new boolean[graph.length];
    ArrayList<Integer> result = new ArrayList<>();

    for (int i = 0; i < graph.length; i++) {
      if (!visible[i]) {
        dfs(graph, visible, isSafe, i);
      }
    }

    for (int i = 0; i < isSafe.length; i++) {
      if (isSafe[i]){
        result.add(i);
      }
    }

    return result;
  }

  public void dfs(int[][] graph, boolean[] visible, boolean[] isSafe, int index) {
    visible[index] = true;
    for (int i = 0; i < graph[index].length; i++) {
      if (!visible[graph[index][i]]) {
        visible[graph[index][i]] = true;
        dfs(graph, visible, isSafe, graph[index][i]);
      }
      if (!isSafe[graph[index][i]]) {
        return;
      }


    }

    isSafe[index] = true;

  }


}
