package liudu.leetcode;

public class Number2 {


  public static void main(String[] args) {
    ListNode head1 = new ListNode(2);
    head1.next = new ListNode(4);
    head1.next.next = new ListNode(3);

    ListNode head2 = new ListNode(5);
    head2.next = new ListNode(6);
    head2.next.next = new ListNode(4);

    Number2 number2 = new Number2();
    System.out.println(number2.addTwoNumbers(head1, head2));
  }

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode head = new ListNode();
    ListNode index = head;
    int add = 0;
    while (l1 != null || l2 != null) {
      int i1;
      int i2;

      if (l1 != null) {
        i1 = l1.val;
        l1 = l1.next;
      } else {
        i1 = 0;
      }

      if (l2 != null) {
        i2 = l2.val;
        l2 = l2.next;
      } else {
        i2 = 0;
      }

      int num = 0;
      if (i1 + i2 + add >= 10) {
        num = (i1 + i2 + add) % 10;
        add = 1;
      } else {
        num = i1 + i2 + add;
        add = 0;
      }

      index.next = new ListNode(num);
      index = index.next;
    }

    if (add == 1){
      index.next = new ListNode(1);
    }

    return head.next;
  }

}
