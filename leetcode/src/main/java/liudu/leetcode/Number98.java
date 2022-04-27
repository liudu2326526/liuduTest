package liudu.leetcode;

public class Number98 {

  public boolean isValidBST(TreeNode root) {

    return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
  }

  public boolean isValidBST(TreeNode root, long lower, long bigger) {
    if (root == null) {
      return true;
    }
    if (root.val <= lower || root.val >= bigger) {
      return false;
    }

    return isValidBST(root.left, lower, root.val) && isValidBST(root.right, root.val, bigger);
  }


}
