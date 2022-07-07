package liudu.leetcode;

public class Number147 {

  public static void main(String[] args) {
    ListNode listNode1 = new ListNode(4);
    ListNode listNode2 = new ListNode(2);
    ListNode listNode3 = new ListNode(1);
    ListNode listNode4 = new ListNode(3);

    listNode1.next = listNode2;
    listNode2.next = listNode3;
    listNode3.next = listNode4;

    new Number147().insertionSortList(listNode1);
  }

  public ListNode insertionSortList(ListNode head) {
    ListNode pre = new ListNode(0);
    pre.next = head;
    ListNode cur = pre;
    ListNode tail = null;
    while (tail != pre) {
      cur = pre;
      while (cur.next != tail) {
        exchange(cur);
        cur = cur.next;
      }
      tail = cur;
    }

    return pre.next;
  }

  public void exchange(ListNode node) {
    if (node.next != null && node.next.next != null && node.next.val > node.next.next.val) {
      ListNode tmp = node.next;
      node.next = node.next.next;
      tmp.next = node.next.next;
      node.next.next = tmp;
    }
  }

}
