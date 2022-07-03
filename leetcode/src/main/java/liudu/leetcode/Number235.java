package liudu.leetcode;

/**
 * 235. 二叉搜索树的最近公共祖先
 */
public class Number235 {

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    // q 为大值
    if (p.val >= q.val) {
      TreeNode tmp = q;
      q = p;
      p = tmp;
    }
    if (root.val <= q.val && root.val >= p.val) {
      return root;
    }
    if (root.val < q.val) {
      return lowestCommonAncestor(root.right, p, q);
    }
    return lowestCommonAncestor(root.left, p, q);

  }
}
