package liudu.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Number199 {

  public List<Integer> rightSideView(TreeNode root) {
    ArrayList<Integer> integers = new ArrayList<>();
    if (root == null) {
      return integers;
    }

    ArrayList<TreeNode> treeNodes = new ArrayList<>();
    treeNodes.add(root);
    ArrayList<List<TreeNode>> lists = new ArrayList<>();
    lists.add(treeNodes);
    rightSideView(lists, 0);
    for (List<TreeNode> list : lists) {
      if (list.size() > 0) {
        integers.add(list.get(list.size() - 1).val);
      }
    }

    return integers;
  }

  public void rightSideView(List<List<TreeNode>> treeNodeLists, int stage) {
    List<TreeNode> nodes = treeNodeLists.get(stage);
    if (nodes.size() == 0) {
      return;
    }
    ArrayList<TreeNode> treeNodes = new ArrayList<>();
    for (TreeNode node : nodes) {
      if (node.left != null) {
        treeNodes.add(node.left);
      }
      if (node.right != null) {
        treeNodes.add(node.right);
      }
    }
    treeNodeLists.add(treeNodes);
    rightSideView(treeNodeLists, stage + 1);
  }


}
