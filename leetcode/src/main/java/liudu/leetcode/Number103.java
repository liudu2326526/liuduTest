package liudu.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Number103 {

  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    ArrayList<List<Integer>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    List<List<TreeNode>> lists = new ArrayList<>();
    ArrayList<TreeNode> treeNodes = new ArrayList<>();
    treeNodes.add(root);
    lists.add(treeNodes);
    int tier = 0;
    while (lists.size() != tier) {
      for (TreeNode treeNode : lists.get(tier)) {
        if (lists.size() == tier + 1 && (treeNode.left != null || treeNode.right != null)) {
          ArrayList<TreeNode> treeNodes1 = new ArrayList<>();
          lists.add(treeNodes1);
        }

        if (treeNode.left != null) {
          lists.get(tier + 1).add(treeNode.left);
        }
        if (treeNode.right != null) {
          lists.get(tier + 1).add(treeNode.right);
        }
      }

      tier++;
    }

    boolean flag = false;

    for (List<TreeNode> list : lists) {
      ArrayList<Integer> integers = new ArrayList<>();
      for (TreeNode treeNode : list) {
        integers.add(treeNode.val);
      }
      if (flag) {
        Collections.reverse(integers);
      }
      flag = !flag;
      result.add(integers);
    }

    return result;
  }

}
