package liudu.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Number129 {

  public static void main(String[] args) {
    TreeNode treeNode1 = new TreeNode(1);
    TreeNode treeNode2 = new TreeNode(2);
    TreeNode treeNode3 = new TreeNode(3);

    treeNode1.left = treeNode2;
    treeNode1.right = treeNode3;

    System.out.println(new Number129().sumNumbers(treeNode1));
  }

  public int sumNumbers(TreeNode root) {
    ArrayList<Integer> objects = new ArrayList<>();
    if (root == null){
      return 0;
    }
    return sumNumbers(root, objects, 0);
  }

  public int sumNumbers(TreeNode root, List<Integer> numbers, int sum) {
    numbers.add(root.val);
    if (root.left == null && root.right == null) {
      int tmp = 0;
      for (int i = 0; i < numbers.size(); i++) {
        tmp = tmp * 10 + numbers.get(i);
      }
      sum = sum + tmp;
      numbers.remove(numbers.size() - 1);
      return sum;
    }

    if (root.left != null) {
      sum = sumNumbers(root.left, numbers, sum);
    }
    if (root.right != null) {
      sum = sumNumbers(root.right, numbers, sum);
    }
    numbers.remove(numbers.size() - 1);
    return sum;
  }


}
