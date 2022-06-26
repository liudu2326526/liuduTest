package liudu.leetcode;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

  int val;
  TreeNode left;
  TreeNode right;

  TreeNode() {
  }

  TreeNode(int val) {
    this.val = val;
  }

  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }

  public void BFSPrint() {
    // 广度遍历
    ArrayList<List<TreeNode>> lists = new ArrayList<>();
    ArrayList<TreeNode> treeNodes = new ArrayList<>();
    treeNodes.add(this);
    lists.add(treeNodes);
    int leval = 0;
    while (lists.size() > leval) {
      List<TreeNode> levalNodes = lists.get(leval);
      boolean hasNextLeval = false;
      ArrayList<TreeNode> nextLeval = new ArrayList<>();
      for (TreeNode levalNode : levalNodes) {
        if (levalNode == null) {
          nextLeval.add(null);
          nextLeval.add(null);
          continue;
        }
        nextLeval.add(levalNode.left);
        nextLeval.add(levalNode.right);
        if (!hasNextLeval || levalNode.left != null || levalNode.right != null) {
          hasNextLeval = true;
        }
      }
      if (hasNextLeval) {
        lists.add(nextLeval);
      }
      leval++;
    }
    StringBuilder sb = new StringBuilder();
    //输出树的形状
    for (List<TreeNode> list : lists) {
      for (TreeNode treeNode : list) {
        if (treeNode == null) {
          sb.append("null");
        } else {
          sb.append(treeNode.val);
        }
        sb.append("\t");
      }
      sb.append("\n");
    }

    System.out.println(sb);
  }


}