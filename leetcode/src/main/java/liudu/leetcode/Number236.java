package liudu.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 236. 二叉树的最近公共祖先
 */
public class Number236 {

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    HashMap<TreeNode, Integer> map = new HashMap<>();
    map.put(null, 0);
    lowestCommonAncestor(root, p.val, q.val, map);
    return lowestCommonAncestor(root, map);
  }

  public int lowestCommonAncestor(TreeNode root, int v1, int v2, Map<TreeNode, Integer> map) {
    if (root == null) {
      return 0;
    }
    int num = lowestCommonAncestor(root.left, v1, v2, map) +
        lowestCommonAncestor(root.right, v1, v2, map);
    if (root.val == v1 || root.val == v2) {
      map.put(root, 1 + num);
      return 1 + num;
    } else {
      map.put(root, num);
      return num;
    }
  }

  public TreeNode lowestCommonAncestor(TreeNode root, Map<TreeNode, Integer> map) {
    if (map.get(root) == 2 && map.get(root.right) != 2 && map.get(root.left) != 2) {
      return root;
    }
    if (map.get(root.right) == 2) {
      return lowestCommonAncestor(root.right, map);
    } else {
      return lowestCommonAncestor(root.left, map);
    }

  }


}
