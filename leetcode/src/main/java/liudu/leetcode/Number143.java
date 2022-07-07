package liudu.leetcode;

public class Number143 {

  public static void main(String[] args) {
    ListNode listNode1 = new ListNode(1);
    ListNode listNode2 = new ListNode(2);
    ListNode listNode3 = new ListNode(3);
    ListNode listNode4 = new ListNode(4);

    listNode1.next = listNode2;
    listNode2.next = listNode3;
    listNode3.next = listNode4;

    new Number143().reorderList(listNode1);
  }

  public void reorderList(ListNode head) {
    // 快慢指针
    ListNode slow = head;
    ListNode quick = head;

    while (quick.next != null && quick.next.next != null) {
      slow = slow.next;
      quick = quick.next.next;
    }

    ListNode h2 = slow.next;
    slow.next = null;

    h2 = reversList(h2);

    head = connectList(head, h2,true);
  }

  public ListNode connectList(ListNode h1, ListNode h2, Boolean isH1) {
    if (isH1 && h1 != null) {
      h1.next = connectList(h1.next, h2, false);
      return h1;
    }

    if (!isH1 && h2 != null) {
      h2.next = connectList(h1, h2.next, true);
      return h2;
    }
    return null;
  }

  public ListNode reversList(ListNode head) {
    ListNode pre = head;
    ListNode index = null;
    while (pre != null) {
      ListNode tmp = pre.next;
      pre.next = index;
      index = pre;
      pre = tmp;
    }
    return index;
  }

}
