package liudu.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Number99 {

  public void recoverTree(TreeNode root) {
    ArrayList<TreeNode> treeNodes = new ArrayList<>();
    listTree(root, treeNodes);
    int index1 = -1;
    int index2 = -1;
    for (int i = 0; i < treeNodes.size() - 1; i++) {
      if (treeNodes.get(i).val > treeNodes.get(i + 1).val) {
        index2 = i + 1;
        if (index1 == -1) {
          index1 = i;
        } else {
          break;
        }
      }
    }
    int tmp = treeNodes.get(index1).val;
    treeNodes.get(index1).val = treeNodes.get(index2).val;
    treeNodes.get(index2).val = tmp;
  }

  public void listTree(TreeNode root, List<TreeNode> treeNodeList) {
    if (root == null) {
      return;
    }
    listTree(root.left, treeNodeList);
    treeNodeList.add(root);
    listTree(root.right, treeNodeList);

  }

}
