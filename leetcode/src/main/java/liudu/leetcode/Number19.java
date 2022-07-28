package liudu.leetcode;

public class Number19 {

  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode index1 = head;
    while (n > 0 && index1 != null) {
      index1 = index1.next;
      n--;
    }

    if (index1 == null) {
      return head.next;
    }

    index1 = index1.next;

    ListNode index2 = head;
    while (index1 != null) {
      index1 = index1.next;
      index2 = index2.next;
    }

    index2.next = index2.next.next;

    return head;
  }

}
