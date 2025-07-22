package liudu.leetcode;

/**
 * @author Liu Du
 * @Date 2025/7/21
 */
public class Number1957 {

  public static void main(String[] args) {
    Number1957 number1957 = new Number1957();
    System.out.println(number1957.makeFancyString("leeetcode"));

  }

  public String makeFancyString(String s) {
    StringBuilder sb = new StringBuilder();

    int num = 1;
    char pre = s.charAt(0);
    sb.append(s.charAt(0));
    for (int i = 1; i < s.length(); i++) {
      if (s.charAt(i) == pre) {
        if (num <= 1) {
          sb.append(s.charAt(i));
          num++;
        }
      } else {
        num = 1;
        sb.append(s.charAt(i));
      }

      pre = s.charAt(i);

    }

    return sb.toString();
  }

}
