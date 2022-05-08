package liudu.leetcode;

public class Number116 {

  public Node connect(Node root) {
    if (root == null || root.left == null) {
      return root;
    }
    connect(root.left, root, true);
    connect(root.right, root, false);
    return root;
  }

  public void connect(Node root, Node father, Boolean isLeft) {
    if (isLeft) {
      root.next = father.right;
    } else {
      if (father.next != null) {
        root.next = father.next.left;
      }
    }
    if (root.left != null) {
      connect(root.left, root, true);
      connect(root.right, root, false);
    }
  }


}
