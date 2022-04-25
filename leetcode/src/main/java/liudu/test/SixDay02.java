package liudu.test;

import java.util.ArrayList;
import java.util.List;

public class SixDay02 {

  public static void main(String[] args) {
    TreeNode n1 = new TreeNode(3);
    TreeNode n2 = new TreeNode(9);
    TreeNode n3 = new TreeNode(20);
    TreeNode n4 = new TreeNode(14);
    TreeNode n5 = new TreeNode(7);

    n1.left = n2;
    n1.right = n3;
    n3.left = n4;
    n3.right = n5;

    System.out.println(new SolutionSixDay02().levelOrder(n1));
  }

}


class SolutionSixDay02 {

  public List<List<Integer>> levelOrder(TreeNode root) {


    List<List<Integer>> result = new ArrayList<>();

    if (root == null){
      return result;
    }
    List<List<TreeNode>> resultTreeNode = new ArrayList<>();
    ArrayList<TreeNode> nodes = new ArrayList<>();
    nodes.add(root);
    resultTreeNode.add(nodes);
    levelOrder(resultTreeNode, 0);



    for (List<TreeNode> treeNodes : resultTreeNode) {
      ArrayList<Integer> integers = new ArrayList<>();
      for (TreeNode treeNode : treeNodes) {
        integers.add(treeNode.val);
      }
      result.add(integers);
    }

    return result;
  }

  public void levelOrder(List<List<TreeNode>> result, int level) {

    if (result.size() <= level) {
      return;
    }
    List<TreeNode> treeNodes = result.get(level);
    for (TreeNode treeNode : treeNodes) {
      if (treeNode.left != null) {
        if (result.size() <= level + 1) {
          result.add(new ArrayList<>());
        }
        result.get(level + 1).add(treeNode.left);
      }

      if (treeNode.right != null) {
        if (result.size() <= level + 1) {
          result.add(new ArrayList<>());
        }
        result.get(level + 1).add(treeNode.right);
      }
    }

    levelOrder(result, level + 1);

  }
}