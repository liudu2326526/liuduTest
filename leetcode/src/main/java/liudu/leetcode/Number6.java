package liudu.leetcode;

/**
 * @author liudu
 * @title: Number6
 * @projectName liuduTest
 * @description: 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * @date 2022/7/26下午4:55
 */
public class Number6 {

  public static void main(String[] args) {
    System.out.println(new Number6().convert("PAYPALISHIRING", 4));
  }

  public String convert(String s, int numRows) {
    int part = 2 * numRows - 2;
    if (numRows == 1){
      return s;
    }
    int length = (s.length() / part + 1) * (numRows - 1);
    char[][] chars = new char[numRows][length];
    char[] stringArray = s.toCharArray();
    for (int i = 0; i < stringArray.length; i++) {
      int position = i / part;
      int remainder = i % part;
      if (remainder < numRows) {
        chars[remainder][position * (numRows - 1)] = stringArray[i];
      } else {
        chars[numRows * 2 - 2 - remainder][position * (numRows - 1) + remainder + 1 - numRows] = stringArray[i];
      }
    }

    StringBuilder result = new StringBuilder();

    for (char[] aChar : chars) {
      for (char c : aChar) {
        if (c != 0) {
          result.append(c);
        }
      }
    }

    return result.toString();
  }

}
