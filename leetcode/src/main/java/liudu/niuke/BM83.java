package liudu.niuke;

import java.util.Arrays;

public class BM83 {

  public static void main(String[] args) {
    String this_is_a_sample = new BM83().trans("This is a sample", 1);
    System.out.println(this_is_a_sample);
  }

  public String trans(String s, int n) {
    // write code here
    String[] s1 = s.split(" ");
    String[] strings = new String[s1.length];
    for (int i = 0; i < s1.length; i++) {
      s1[i] = revers(s1[i]);
      strings[s1.length - 1 - i] = s1[i];
    }



    return String.join(" ", strings);
  }

  public String revers(String s) {
    int a = 'A' - 'a';
    char[] chars = s.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      if (chars[i] < 'a') {
        chars[i] = (char) (chars[i] - a);
      } else {
        chars[i] = (char) (chars[i] + a);
      }
    }

    return String.valueOf(chars);
  }

}
