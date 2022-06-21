package liudu.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Number230 {

  public int kthSmallest(TreeNode root, int k) {
    List<TreeNode> integers = new ArrayList<>(k);
    pre(root, integers, k);
    return integers.get(k - 1).val;
  }

  public void pre(TreeNode root, List<TreeNode> list, int k) {
    if (root == null || list.size() == k) {
      return;
    }
    pre(root.left, list, k);
    list.add(root);
    pre(root.right, list, k);

  }
}
