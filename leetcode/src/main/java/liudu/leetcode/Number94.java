package liudu.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Number94 {

  public List<Integer> inorderTraversal(TreeNode root) {
    ArrayList<Integer> integers = new ArrayList<>();
    inorderTraversal(root, integers);
    return integers;
  }

  public void inorderTraversal(TreeNode root, List<Integer> list) {
    if (root == null){
      return;
    }
    if (root.left != null) {
      inorderTraversal(root.left, list);
    }

    list.add(root.val);

    if (root.right != null) {
      inorderTraversal(root.right, list);
    }

  }
}
