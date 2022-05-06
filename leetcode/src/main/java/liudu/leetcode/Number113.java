package liudu.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Number113 {

  public List<List<Integer>> pathSum(TreeNode root, int targetSum) {

    List<Integer> list = new ArrayList<>();
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    pathSum(root, targetSum, result, list);
    return result;
  }

  public void pathSum(TreeNode root, int targetSum, List<List<Integer>> result,
      List<Integer> list) {
    if (root.left == null && root.right == null) {
      if (targetSum == root.val) {
        list.add(root.val);
        List<Integer> integers = new ArrayList<>(list);
        result.add(integers);
        list.remove(list.size() - 1);
      }
      return;
    }

    list.add(root.val);
    if (root.left != null) {
      pathSum(root.left, targetSum - root.val, result, list);
    }
    if (root.right != null) {
      pathSum(root.right, targetSum - root.val, result, list);
    }
    list.remove(list.size() - 1);

  }

}
