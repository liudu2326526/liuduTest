package liudu.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class Number117 {

  public static void main(String[] args) {
    Node node1 = new Node(1);
    Node node2 = new Node(2);
    Node node3 = new Node(3);
    Node node4 = new Node(4);
    Node node5 = new Node(5);
    Node node6 = new Node(6);
    Node node7 = new Node(7);
    Node node8 = new Node(8);

    node1.left = node2;
    node1.right = node3;
    node2.left = node4;
    node2.right = node5;
    node3.right = node6;
    node4.left = node7;
    node6.right = node8;

    new Number117().connect(node1);

  }


  public Node connect(Node root) {
    if (root == null) {
      return null;
    }
    Queue<Node> queue = new LinkedList<Node>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      int n = queue.size();
      Node last = null;
      for (int i = 1; i <= n; ++i) {
        Node f = queue.poll();
        if (f.left != null) {
          queue.offer(f.left);
        }
        if (f.right != null) {
          queue.offer(f.right);
        }
        if (i != 1) {
          last.next = f;
        }
        last = f;
      }
    }
    return root;
  }

}
