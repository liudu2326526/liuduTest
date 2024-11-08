package liudu.leetcode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author Liu Du
 * @Date 2024/11/8
 */
public class Number23 {

  public static void main(String[] args) {
    Number23 number23 = new Number23();
    ListNode listNode1 = new ListNode(1);
    listNode1.next = new ListNode(3);
    listNode1.next.next = new ListNode(5);
    listNode1.next.next.next = new ListNode(7);

    ListNode listNode2 = new ListNode(2);
    listNode2.next = new ListNode(4);
    listNode2.next.next = new ListNode(6);

    System.out.println(number23.mergeTwoNode(listNode1, listNode2));

  }

  public ListNode mergeKLists(ListNode[] lists) {
    LinkedList<ListNode> linkedList = new LinkedList<>(Arrays.asList(lists));

    while (linkedList.size() > 1) {
      ListNode listNode1 = linkedList.pollFirst();
      ListNode listNode2 = linkedList.pollFirst();
      linkedList.addLast(mergeTwoNode(listNode1, listNode2));
    }

    return linkedList.pollFirst();
  }


  public ListNode mergeTwoNode(ListNode node1, ListNode node2) {
    if (node1 == null) {
      return node2;
    }
    if (node2 == null) {
      return node1;
    }

    ListNode head = new ListNode();
    ListNode index = head;
    while (node1 != null && node2 != null) {
      if (node1.val < node2.val) {
        index.next = node1;
        node1 = node1.next;
      } else {
        index.next = node2;
        node2 = node2.next;
      }

      index = index.next;
    }

    if (node1 != null) {
      index.next = node1;
    }
    if (node2 != null) {
      index.next = node2;
    }

    return head.next;
  }

}
