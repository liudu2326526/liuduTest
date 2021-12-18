package liudu.test;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SixDay01 {

}


class TreeNode {

  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }

  @Override
  public String toString() {
    return val + "";
  }
}

class Solution {

  public int[] levelOrder(TreeNode root) {
    ArrayList<Integer> integers = new ArrayList<>();
    Queue<TreeNode> queue = new LinkedList<TreeNode>();

    if (root == null) {
      return new int[0];
    }
    queue.add(root);
    levelOrder(integers, queue);
    int[] ints = new int[integers.size()];
    for (int i = 0; i < integers.size(); i++) {
      ints[i] = integers.get(i);
    }
    return ints;
  }

  public void levelOrder(List<Integer> list, Queue<TreeNode> queue) {
    if (queue.isEmpty()) {
      return;
    }
    TreeNode poll = queue.poll();
    list.add(poll.val);

    if (poll.left != null) {
      queue.add(poll.left);
    }
    if (poll.right != null) {
      queue.add(poll.right);
    }

    levelOrder(list, queue);

  }
}