package liudu.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Number95 {

  public List<TreeNode> generateTrees(int n) {
    if (n == 0) {
      return new ArrayList<>();
    }
    return generateTrees(1, n);
  }

  public List<TreeNode> generateTrees(int begin, int end) {
    ArrayList<TreeNode> treeNodes = new ArrayList<>();
    if (begin > end) {
      treeNodes.add(null);
      return treeNodes;
    }
    for (int i = begin; i <= end; i++) {
      List<TreeNode> left = generateTrees(begin, i - 1);
      List<TreeNode> right = generateTrees(i + 1, end);
      for (TreeNode leftTreeNode : left) {
        for (TreeNode rightTreeNode : right) {
          TreeNode treeNode = new TreeNode(i);
          treeNode.right = rightTreeNode;
          treeNode.left = leftTreeNode;
          treeNodes.add(treeNode);
        }
      }
    }
    return treeNodes;
  }
}
