package liudu.test;

import java.util.Stack;

public class OneDay01 {

  public static void main(String[] args) {
    CQueue cQueue = new CQueue();

    cQueue.appendTail(1);
    cQueue.appendTail(2);
    cQueue.appendTail(3);
    System.out.println(cQueue.deleteHead());
    System.out.println(cQueue.deleteHead());
    cQueue.appendTail(4);
    System.out.println(cQueue.deleteHead());
    System.out.println(cQueue.deleteHead());
    System.out.println(cQueue.deleteHead());

  }

}

/**
 * Your CQueue object will be instantiated and called as such: CQueue obj = new CQueue();
 * obj.appendTail(value); int param_2 = obj.deleteHead();
 */

/**
 * 1.导入的时候另一个栈中不能有数 2.导出后要再倒回来
 */
class CQueue {

  private Stack<Integer> popStack;
  private Stack<Integer> pushStack;

  public CQueue() {
    popStack = new Stack<>();
    pushStack = new Stack<>();
  }

  public void appendTail(int value) {
    while (!popStack.empty() && !popStack.empty()) {
      pushStack.push(popStack.pop());
    }
    pushStack.push(value);
  }

  public int deleteHead() {
    if (pushStack.empty() && popStack.empty()) {
      return -1;
    }
    while (!pushStack.empty()) {
      popStack.push(pushStack.pop());
    }
    return popStack.pop();
  }
}