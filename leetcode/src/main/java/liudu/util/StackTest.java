package liudu.util;

import java.util.LinkedList;

public class StackTest {

  public static void main(String[] args) {
    LinkedList<Integer> integers = new LinkedList<>();
    integers.push(1);
    integers.push(2);
    integers.push(3);
    integers.push(4);
    integers.add(5);

    System.out.println(integers.poll());

    System.out.println(integers.peek());

    System.out.println(integers);
  }

}
