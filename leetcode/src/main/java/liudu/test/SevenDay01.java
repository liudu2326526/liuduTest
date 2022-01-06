package liudu.test;

import java.util.ArrayList;
import java.util.List;

public class SevenDay01 {

}


class SolutionSevenDay01 {

  public boolean isSubStructure(TreeNode A, TreeNode B) {

    if (B == null) {
      return false;
    }

    ArrayList<TreeNode> treeNodes = new ArrayList<>();
    getTreeNodeList(A, treeNodes);

    for (TreeNode treeNode : treeNodes) {
      if (treeNode.val == B.val && isContain(treeNode,B)){
        return true;
      }
    }
    return false;
  }

  public void getTreeNodeList(TreeNode root, List<TreeNode> treeNodeList) {
    if (root == null) {
      return;
    }

    treeNodeList.add(root);
    getTreeNodeList(root.left, treeNodeList);
    getTreeNodeList(root.right, treeNodeList);
  }

  public boolean isContain(TreeNode A, TreeNode B) {

    if (B == null) {
      return true;
    } else if (A == null) {
      return false;
    } else {
      return A.val == B.val && isContain(A.left, B.left) && isContain(A.right, B.right);
    }

  }
}