package liudu.leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class BSTIterator {

  private TreeNode cur;
  private Deque<TreeNode> stack;

  public BSTIterator(TreeNode root) {
    this.cur = root;
    this.stack = new LinkedList<>();
  }

  public int next() {
    while (cur != null) {
      stack.push(cur);
      cur = cur.left;
    }
    TreeNode pop = stack.pop();
    cur = pop.right;
    return pop.val;
  }

  public boolean hasNext() {
    return cur != null || !stack.isEmpty();
  }

}
