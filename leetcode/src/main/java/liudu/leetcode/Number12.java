package liudu.leetcode;

public class Number12 {

  public static void main(String[] args) {
    Number12 number12 = new Number12();
    System.out.println(number12.intToRoman(1994));
  }

  public String intToRoman(int num) {
    StringBuilder s = new StringBuilder();
//    处理千的位数
    int thousand = num / 1000;
    for (int i = 0; i < thousand; i++) {
      s.append("M");
    }
    num = num % 1000;
//    处理百的位数
    int hundred = num / 100;
    s.append(handleNumber("M", "D", "C", hundred));
    num = num % 100;
//    处理十的位数
    int ten = num / 10;
    s.append(handleNumber("C", "L", "X", ten));
    num = num % 10;
//    处理个位数
    int single = num % 10;
    s.append(handleNumber("X", "V", "I", single));

    return s.toString();

  }

  public String handleNumber(String s1, String s2, String s3, int num) {
    StringBuilder s = new StringBuilder();
    if (num == 9) {
      return s3 + s1;
    }
    if (num == 5) {
      return s2;
    }
    if (num == 4) {
      return s3 + s2;
    }
    if (num / 5 == 1) {
      s.append(s2);
    }
    s.append(String.valueOf(s3).repeat(Math.max(0, (num % 5))));
    return s.toString();
  }


}
