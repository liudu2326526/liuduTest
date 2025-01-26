package liudu.leetcode;

public class Number92 {

  public static void main(String[] args) {
    ListNode listNode1 = new ListNode(1);
    ListNode listNode2 = new ListNode(2);
    ListNode listNode3 = new ListNode(3);
    ListNode listNode4 = new ListNode(4);
    ListNode listNode5 = new ListNode(5);

    listNode1.next = listNode2;
    listNode2.next = listNode3;
    listNode3.next = listNode4;
    listNode4.next = listNode5;

    System.out.println(new Number92().reverseBetween(listNode1, 2, 4));

  }


  public ListNode reverseBetween(ListNode head, int left, int right) {
    ListNode leftNode = null;
    ListNode leftPreNode = null;
    ListNode rightNode = null;
    ListNode rightNextNode = null;
    ListNode index = head;
    while (index != null) {
      if (left == 2) {
        leftPreNode = index;
      }
      if (left == 1) {
        leftNode = index;
      }

      if (right == 1) {
        rightNode = index;
        rightNextNode = rightNode.next;
      }
      left--;
      right--;
      index = index.next;
    }

    if (leftPreNode == null) {
      head = rightNode;
    } else {
      leftPreNode.next = null;
    }

    rightNode.next = null;

    ListNode reverse = reverse(leftNode);


    if (leftPreNode == null){
      head = reverse;
    } else {
      leftPreNode.next = reverse;
    }

    leftNode.next = rightNextNode;

    return head;
  }


  public ListNode reverse(ListNode head) {
    ListNode pre = null;
    ListNode index = head;
    if (index == null) {
      return head;
    }
    ListNode next = index.next;
    while (next != null) {
      index.next = pre;
      pre = index;
      index = next;
      next = index.next;
    }
    index.next = pre;

    return index;

  }

}
