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

    System.out.println(new Number25().reverseKGroup(listNode1, 3));
//    System.out.println(new Number25().reverse(listNode1));
  }

  public ListNode reverseKGroup(ListNode head, int k) {
    ListNode newHead = new ListNode();
    newHead.next = head;

    ListNode preTail = newHead;
    ListNode nextHead = head;
    ListNode nextTail = null;
    int i;
    ListNode index = head;

    while (index != null) {

      for (i = 0; i < k; i++) {

        if (index != null) {
          if (i == k-1){
            nextHead = index.next;
            index.next = null;
            index = nextHead;
          } else {
            index = index.next;
          }
        } else {
          break;
        }
      }

      if ( i == k){
        preTail.next = reverse(head);
        preTail = head;
        head = nextHead;
      } else {
        preTail.next = nextHead;
      }

    }


    return newHead.next;
  }

  private ListNode reverse(ListNode head) {
    ListNode pre = null;
    ListNode index = head;

    if (head.next == null) {
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
