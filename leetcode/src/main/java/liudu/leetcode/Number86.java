package liudu.leetcode;

public class Number86 {

  public static void main(String[] args) {
    ListNode listNode1 = new ListNode(1);
    ListNode listNode2 = new ListNode(4);
    ListNode listNode3 = new ListNode(3);
    ListNode listNode4 = new ListNode(2);
    ListNode listNode5 = new ListNode(5);
    ListNode listNode6 = new ListNode(2);
    listNode1.next = listNode2;
    listNode2.next = listNode3;
    listNode3.next = listNode4;
    listNode4.next = listNode5;
    listNode5.next = listNode6;
    ListNode partition = new Number86().partition(listNode1, 3);
    System.out.println(partition);
  }

  public ListNode partition(ListNode head, int x) {
    ListNode smallHead = null;
    ListNode bigHead = null;
    ListNode index = head;
    ListNode smallTail = null;
    ListNode bigTail = null;

    while (index != null) {
      if (index.val < x) {
        if (smallHead == null) {
          smallHead = index;
        }
        if (smallTail != null) {
          smallTail.next = index;
        }
        smallTail = index;
      } else {
        if (bigHead == null) {
          bigHead = index;
        }
        if (bigTail != null) {
          bigTail.next = index;
        }
        bigTail = index;
      }

      index = index.next;
    }
    if (bigTail == null && smallTail == null){
      return null;
    } else if (bigTail == null){
      return smallHead;
    } else if (smallTail == null){
      return bigHead;
    }
    bigTail.next = null;
    smallTail.next = bigHead;
    return smallHead;
  }


}
