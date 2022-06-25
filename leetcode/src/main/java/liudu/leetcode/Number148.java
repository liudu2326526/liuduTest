package liudu.leetcode;

/**
 * @author liudu
 * @title: Number147
 * @projectName liuduTest
 * @date 2022/6/23上午11:00
 */
//148. 排序链表
public class Number148 {

  public static void main(String[] args) {
    ListNode listNode1 = new ListNode(4);
    ListNode listNode2 = new ListNode(2);
    ListNode listNode3 = new ListNode(1);
    ListNode listNode4 = new ListNode(3);

    listNode1.next = listNode2;
    listNode2.next = listNode3;
    listNode3.next = listNode4;


    System.out.println(new Number148().sortList(listNode1));
  }

  public ListNode sortList(ListNode head) {

    if (head == null || head.next == null) {
      return head;
    }

    ListNode slow = head;
    ListNode quick = head;
    ListNode tmp = null;

    while (quick != null && quick.next != null) {
      if (tmp == null) {
        tmp = head;
      } else {
        tmp = tmp.next;
      }
      slow = slow.next;
      quick = quick.next.next;
    }
    tmp.next = null;

    ListNode listNode1 = sortList(slow);
    ListNode listNode2 = sortList(head);

    return mergeTwoLists(listNode1, listNode2);
  }

  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    } else if (l2 == null) {
      return l1;
    } else if (l1.val < l2.val) {
      l1.next = mergeTwoLists(l1.next, l2);
      return l1;
    } else {
      l2.next = mergeTwoLists(l1, l2.next);
      return l2;
    }
  }

}
