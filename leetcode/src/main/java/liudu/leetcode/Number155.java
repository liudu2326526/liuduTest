package liudu.leetcode;

import java.util.LinkedList;
import java.util.Stack;

public class Number155 {

}

class MinStack {

  LinkedList<Integer> stack;
  LinkedList<Integer> minStack;

  public MinStack() {
    stack = new LinkedList<>();
    minStack = new LinkedList<>();
  }

  public void push(int val) {
    stack.push(val);

    if (minStack.isEmpty()){
      minStack.push(val);
    } else {
      minStack.push(Math.min(minStack.peek(),val));
    }


  }

  public void pop() {
    minStack.pop();
    stack.pop();
  }

  public int top() {
    return stack.peek();
  }

  public int getMin() {
    return minStack.peek();

  }
}

