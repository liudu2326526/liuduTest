package liudu.test;

import java.util.Stack;

public class OneDay02 {

  public static void main(String[] args) {
    MinStack minStack = new MinStack();
    minStack.push(-2);
    minStack.push(0);
    minStack.push(-1);
    System.out.println(minStack.min());
    System.out.println(minStack.top());
    minStack.pop();
    System.out.println(minStack.min());
  }

}


class MinStack {

  private Stack<Integer> dataStack;
  private Stack<Integer> minStack;
  private Stack<Integer> topStack;

  /**
   * initialize your data structure here.
   */
  public MinStack() {
    dataStack = new Stack<>();
    minStack = new Stack<>();
    topStack = new Stack<>();
  }

  public void push(int x) {
    dataStack.push(x);

    if (minStack.empty() || x <= minStack.peek()) {
      minStack.push(x);
    }

    if (topStack.empty() || x >= topStack.peek()) {
      topStack.push(x);
    }

  }

  public void pop() {
    Integer x = dataStack.pop();

    if (!minStack.empty() && x.equals(minStack.peek())) {
      minStack.pop();
    }

    if (!topStack.empty() && x.equals(topStack.peek())) {
      topStack.pop();
    }

  }

  public int top() {
    return topStack.peek();
  }

  public int min() {
    return minStack.peek();
  }
}