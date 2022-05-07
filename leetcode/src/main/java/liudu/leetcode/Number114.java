package liudu.leetcode;

public class Number114 {


  public void flatten(TreeNode root) {
    TreeNode cur = root;
    while (cur != null) {
      if (cur.left != null) {
        TreeNode next = cur.left;
        TreeNode pre = next;
        while (pre.right != null) {
          pre = pre.right;
        }
        pre.right = cur.right;
        cur.right = next;
        cur.left = null;
      }
      cur = cur.right;
    }

  }

}
