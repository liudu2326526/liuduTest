package liudu.leetcode;

public class Number306 {

  public static void main(String[] args) {

    Number306 number306 = new Number306();
    System.out.println(number306.isAdditiveNumber("011112"));

//    System.out.println("12345".substring(0, 0));

  }

  public boolean isAdditiveNumber(String num) {
    if (num.length() < 3) {
      return false;
    }

    flag:
    for (int i = 1; i < num.length() / 3 * 2 + 2; i++) {
      for (int j = i + 1; j < num.length() / 3 * 2 + 2; j++) {

        if (isAdditiveNumber(num, i, j)) {
          return true;
        }
        if (num.charAt(i) == '0') {
          continue flag;
        }

      }

      if (num.charAt(0) == '0') {
        break;
      }
    }

    return false;
  }

  public boolean isAdditiveNumber(String num, int index1, int index2) {

    long num1 = Long.parseLong(num.substring(0, index1));
    long num2 = Long.parseLong(num.substring(index1, index2));
    long sum = num1 + num2;
    num = num.substring(index2);

    while (num.length() != 0) {
      String s = Long.toString(sum);
      if (num.startsWith("0") && sum != 0) {
        return false;
      }

      if (!num.startsWith(s)) {
        return false;
      }

      num1 = num2;
      num2 = sum;
      sum = num1 + num2;
      num = num.substring(s.length());
    }

    return true;
  }
}
