package liudu.leetcode;

public class Number24 {

  public ListNode swapPairs(ListNode head) {
    ListNode pre = head;
    ListNode cur = null;
    ListNode back = null;

    int tag = 0;

    while (pre != null && pre.next != null) {
      ListNode tmp = pre.next.next;
      cur = pre;
      pre = pre.next;
      pre.next = cur;
      cur.next = tmp;

      if (tag == 0) {
        head = pre;
      }
      tag++;
      if (back != null) {
        back.next = pre;
      }
      back = cur;
      pre = tmp;

    }

    return head;
  }

}
