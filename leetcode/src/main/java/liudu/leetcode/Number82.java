package liudu.leetcode;

/**
 * 82. 删除排序链表中的重复元素 II
 */
public class Number82 {

  public static void main(String[] args) {
    ListNode listNode1 = new ListNode(1);
    ListNode listNode2 = new ListNode(2);
    ListNode listNode3 = new ListNode(3);
    ListNode listNode4 = new ListNode(3);
    ListNode listNode5 = new ListNode(4);
    ListNode listNode6 = new ListNode(4);
    ListNode listNode7 = new ListNode(5);

    listNode1.next = listNode2;
    listNode2.next = listNode3;
    listNode3.next = listNode4;
    listNode4.next = listNode5;
    listNode5.next = listNode6;
    listNode6.next = listNode7;
    System.out.println(new Number82().deleteDuplicates(listNode1));
  }

  public ListNode deleteDuplicates(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    } else if (head.next.val != head.val) {
      head.next = deleteDuplicates(head.next);
      return head;
    } else {
      int val = head.val;
      ListNode tmp = head.next;
      while (tmp.next != null && val == tmp.next.val){
        tmp = tmp.next;
      }
      return deleteDuplicates(tmp.next);
    }

  }

}
