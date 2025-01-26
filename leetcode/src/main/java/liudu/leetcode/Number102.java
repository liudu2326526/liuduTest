package liudu.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Number102 {

  public static void main(String[] args) {

  }


  public List<List<Integer>> levelOrder(TreeNode root) {
    ArrayList<List<Integer>> result = new ArrayList<>();
    if (root == null){
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


    for (List<TreeNode> list : lists) {
      ArrayList<Integer> integers = new ArrayList<>();
      for (TreeNode treeNode : list) {
        integers.add(treeNode.val);
      }
      result.add(integers);
    }

    return result;
  }


}
