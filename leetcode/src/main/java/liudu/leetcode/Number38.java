package liudu.leetcode;

public class Number38 {

  public static void main(String[] args) {
    System.out.println(countAndSay(6));
//    System.out.println((int) '0');
  }

  public static String countAndSay(int n) {
    if (n == 1) {
      return "1";
    }
    String s = countAndSay(n - 1);
    char[] chars = s.toCharArray();
    char tmp = 0;
    int number = 0;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < chars.length; i++) {
      if (tmp < '0') {
        tmp = chars[i];
      }
      number++;
      if (i == chars.length - 1) {
        sb.append(number);
        sb.append(chars[i]);
        continue;
      }
      if (tmp != chars[i + 1]) {
        sb.append(number);
        sb.append(chars[i]);
        tmp = chars[i+1];
        number = 0;
      }

    }
    return sb.toString();
  }


}
