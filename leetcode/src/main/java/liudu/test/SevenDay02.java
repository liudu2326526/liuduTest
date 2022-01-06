package liudu.test;

public class SevenDay02 {

}


class SolutionSevenDay02 {

  public TreeNode mirrorTree(TreeNode root) {
    if (root == null) {
      return null;
    }
    TreeNode treeNode = new TreeNode(root.val);

    mirrorTree(root, treeNode);
    return treeNode;
  }

  private void mirrorTree(TreeNode root, TreeNode copy) {
    if (root.left != null) {
      copy.right = new TreeNode(root.left.val);
      mirrorTree(root.left, copy.right);
    }

    if (root.right != null) {
      copy.left = new TreeNode(root.right.val);
      mirrorTree(root.right, copy.left);
    }

  }
}