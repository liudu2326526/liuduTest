package liudu.leetcode;

public class Number61 {

  public ListNode rotateRight(ListNode head, int k) {
    if (head == null) {
      return null;
    }
    ListNode tmp = head;
    int length = 1;
    while (tmp.next != null) {
      length++;
      tmp = tmp.next;
    }
    tmp.next = head;
    length = length - k % length;
    while (length > 0) {
      length--;
      tmp = tmp.next;
    }
    head = tmp.next;
    tmp.next = null;

    return head;
  }

}
