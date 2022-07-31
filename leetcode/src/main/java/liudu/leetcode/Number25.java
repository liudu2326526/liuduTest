package liudu.leetcode;

public class Number25 {

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

    new Number25().reverseKGroup(listNode1, 2);
  }

  public ListNode reverseKGroup(ListNode head, int k) {

    ListNode index = head;
    ListNode newHead = null;
    ListNode pre = null;

    while (true) {
      int x = k;
      ListNode now = index;
      while (x > 0 && index != null) {
        x--;
        index = index.next;
      }

      if (x > 0) {
        break;
      }
      ListNode reverse = reverse(now, k);
      if (newHead == null) {
        newHead = reverse;
      }
      if (pre != null) {
        pre.next = reverse;
      }
      pre = now;
      now.next = index;


    }

    return newHead;
  }

  private ListNode reverse(ListNode head, int number) {
    ListNode pre = null;
    ListNode index = head;

    for (int i = 0; i < number; i++) {
      ListNode next = index.next;
      index.next = pre;
      pre = index;
      index = next;
    }

    return pre;
  }

}
