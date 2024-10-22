package liudu.leetcode;

import java.util.HashSet;
import java.util.LinkedList;

public class Number150 {

  public static void main(String[] args) {

    String[] strings = {"4", "13", "5", "/", "+"};
    Number150 number150 = new Number150();
    System.out.println(number150.evalRPN(strings));

  }


  public int evalRPN(String[] tokens) {

    LinkedList<Integer> integers = new LinkedList<>();

    HashSet<String> strings = new HashSet<>();

    strings.add("+");
    strings.add("-");
    strings.add("*");
    strings.add("/");

    for (String token : tokens) {
      if (!strings.contains(token)) {
        integers.add(Integer.parseInt(token));
      } else {
        Integer i1 = integers.removeLast();
        Integer i2 = integers.removeLast();
        switch (token) {
          case "+":
            integers.add(i2 + i1);
            break;
          case "-":
            integers.add(i2 - i1);
            break;
          case "*":
            integers.add(i2 * i1);
            break;
          case "/":
            integers.add(i2 / i1);
            break;
        }

      }


    }

    return integers.peek();
  }

}
