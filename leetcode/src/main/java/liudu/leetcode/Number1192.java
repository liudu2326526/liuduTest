package liudu.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Number1192 {

  public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
    HashMap<Integer, Set<Integer>> map = new HashMap<>();

    for (List<Integer> connection : connections) {
      Integer integer0 = connection.get(0);
      Integer integer1 = connection.get(1);

      Set<Integer> set0 = map.getOrDefault(integer0, new HashSet<>());
      Set<Integer> set1 = map.getOrDefault(integer1, new HashSet<>());

      set0.add(integer1);
      set1.add(integer0);

      map.put(integer0, set0);
      map.put(integer1, set1);
    }

    int[] ids = new int[map.size()];
    Arrays.fill(ids, -1);

    ArrayList<List<Integer>> res = new ArrayList<>();
    dfs(0, 0, -1, ids, map, res);

    return res;
  }

  /**
   * @param node   当前节点是多少
   * @param nodeID 当前节点 id，从父节点获取
   * @param par    当前节点的父节点
   * @param ids    id 列表
   * @param map
   * @param res
   * @return
   */
  public int dfs(int node, int nodeID, int par, int[] ids, HashMap<Integer, Set<Integer>> map,
      ArrayList<List<Integer>> res) {
    ids[node] = nodeID;

    Set<Integer> set = map.get(node);
    for (Integer neighbor : set) {
      if (neighbor == par) {
        continue;
      } else if (ids[neighbor] == -1) {
        ids[node] = Math.min(ids[node], dfs(neighbor, nodeID + 1, node, ids, map, res));
      } else {
        ids[node] = Math.min(ids[node], ids[neighbor]);
      }
    }

    if (nodeID == ids[node] && node != 0) {
      res.add(Arrays.asList(par, node));
    }

    return ids[node];
  }


}
