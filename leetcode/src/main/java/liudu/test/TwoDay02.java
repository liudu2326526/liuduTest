package liudu.test;

public class TwoDay02 {

  public static void main(String[] args) {
    Node n0 = new Node(0);
    Node n1 = new Node(1);
    Node n2 = new Node(2);
    Node n3 = new Node(3);
    Node n4 = new Node(4);
    Node n5 = new Node(5);

    n0.next = n1;
    n1.next = n2;
    n2.next = n3;
    n3.next = n4;
    n4.next = n5;

    System.out.println(n0);

    Node copyRandomList = new SolutionTwoDay02().copyRandomList(n0);

    System.out.println(copyRandomList);
  }

}

/**
 * 1. 拉伸链表
 * <p>
 * 2. 随机的指向
 */
class SolutionTwoDay02 {

  public Node copyRandomList(Node head) {

    if (head == null) {
      return head;
    }

    Node copyHead = head;

    // 拉长链表
    while (copyHead != null) {
      Node linkNode = new Node(copyHead.val);
      linkNode.next = copyHead.next;
      copyHead.next = linkNode;
      copyHead = linkNode.next;
    }

    copyHead = head;

    // 指定随机节点
    while (copyHead != null) {
      if (copyHead.random != null) {
        copyHead.next.random = copyHead.random.next;
      }
      copyHead = copyHead.next.next;
    }

    Node newHead = head.next;
    copyHead = head;
    Node newHeadCopy = head.next;

    // 获取复制链表
    while (copyHead != null) {
      copyHead.next = copyHead.next.next;
      copyHead = copyHead.next;

      if (newHeadCopy.next != null) {
        newHeadCopy.next = newHeadCopy.next.next;
        newHeadCopy = newHeadCopy.next;
      }
    }

    return newHead;
  }
}

class Node {

  int val;
  Node next;
  Node random;

  public Node(int val) {
    this.val = val;
    this.next = null;
    this.random = null;
  }

  @Override
  public String toString() {
    return "Node{" +
        "val=" + val +
        ", next=" + next +
        '}';
  }
}