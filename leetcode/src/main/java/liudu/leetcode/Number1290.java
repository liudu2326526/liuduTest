package liudu.leetcode;

public class Number1290 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(0);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(0);
        head.next.next.next.next = new ListNode(1);
        Number1290 number1290 = new Number1290();
        System.out.println(number1290.getDecimalValue(head));
    }

    public int getDecimalValue(ListNode head) {
        int val = 0;
        while (head != null) {
            val = val * 2 + head.val;
            head = head.next;
        }

        return val;
    }
}
