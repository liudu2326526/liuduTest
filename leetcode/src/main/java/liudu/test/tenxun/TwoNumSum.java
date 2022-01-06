package liudu.test.tenxun;

public class TwoNumSum {

  public static void main(String[] args) {

  }

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    addTwoNumbers(l1, l2, 0);
    return l1;
  }

  public void addTwoNumbers(ListNode l1, ListNode l2, int x) {

    int sum = l1.val + l2.val + x;
    l1.val = sum % 10;
    if (l1.next == null && l2.next != null) {
      l1.next = l2.next;
      modifyValue(l1.next, sum / 10);
      return;
    } else if (l2.next == null && l1.next != null) {
      modifyValue(l1.next, sum / 10);
      return;
    } else if (l1.next == null) {
      if (sum >= 10) {
        l1.next = new ListNode(1);
      }
      return;
    }

    addTwoNumbers(l1.next, l2.next, sum / 10);


  }

  public void modifyValue(ListNode l1, int x) {
    int sum = l1.val + x;
    if (l1.next != null) {
      l1.val = sum % 10;
      modifyValue(l1.next, sum / 10);
    } else {
      l1.val = sum % 10;
      if (sum >= 10) {
        l1.next = new ListNode(1);
      }
    }
  }

}
