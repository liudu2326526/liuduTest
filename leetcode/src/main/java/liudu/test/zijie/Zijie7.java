package liudu.test.zijie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Zijie7 {

  public static void main(String[] args) {
    System.out.println(generateParenthesis(3));
  }

  static int length;

  public static List<String> generateParenthesis(int n) {
    length = n;
    ArrayList<String> strings = new ArrayList<>();

    generateParenthesis(new char[length * 2], 0, strings, 0);

    return strings;
  }


  public static void generateParenthesis(char[] current, int n, List<String> list, int balance) {
    if (balance < 0) {
      return;
    }
    if (n == length * 2) {
      System.out.println(balance);
      System.out.println(new String(current));
      if (balance == 0) {
        list.add(new String(current));
      }
    } else {
      current[n] = '(';

      generateParenthesis(current, n + 1, list, balance + 1);
      current[n] = ')';

      generateParenthesis(current, n + 1, list, balance - 1);
    }

  }

}
