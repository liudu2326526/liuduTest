package liudu.test;

import java.util.ArrayList;
import java.util.Stack;

public class TwoDay01 {

  public static void main(String[] args) {

  }

}


/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode next; ListNode(int
 * x) { val = x; } }
 */
class SolutionTwoDay01 {
  public int[] reversePrint(ListNode head) {
    Stack<ListNode> stack = new Stack<ListNode>();
    ListNode temp = head;
    while (temp != null) {
      stack.push(temp);
      temp = temp.next;
    }
    int size = stack.size();
    int[] print = new int[size];
    for (int i = 0; i < size; i++) {
      print[i] = stack.pop().val;
    }
    return print;
  }
}


class ListNode {

  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
  }
}