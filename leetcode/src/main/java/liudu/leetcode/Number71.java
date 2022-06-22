package liudu.leetcode;

import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;

public class Number71 {

  public static void main(String[] args) {
    LinkedBlockingDeque<Integer> deque = new LinkedBlockingDeque<>();
    deque.push(1);
    deque.push(2);
    deque.push(3);
    deque.push(4);
    System.out.println(deque.pollLast());
  }

  public String simplifyPath(String path) {
    LinkedBlockingDeque<String> deque = new LinkedBlockingDeque<>();
    String[] split = path.split("/");
    for (String s : split) {
      if (s.length() > 0) {
        if ("..".equals(s)) {
          if (deque.size() > 0) {
            deque.pop();
          }

        } else if (!".".equals(s)) {
          deque.push(s);
        }
      }
    }
    if (deque.size() == 0){
      return  "/";
    }

    StringBuilder sb = new StringBuilder();
    while (deque.size() > 0) {
      sb.append("/");
      sb.append(deque.pollLast());
    }
    return sb.toString();
  }

}
