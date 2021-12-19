package liudu.test;

public class SevenDay03 {

}


class SolutionSevenDay03 {

  public boolean isSymmetric(TreeNode root) {
    return root == null || recur(root.left, root.right);
  }

  boolean recur(TreeNode L, TreeNode R) {
    if (L == null && R == null) {
      return true;
    }
    if (L == null || R == null || L.val != R.val) {
      return false;
    }
    return recur(L.left, R.right) && recur(L.right, R.left);
  }

}