package liudu.leetcode;

public class Number125 {

  public static void main(String[] args) {
    Number125 number125 = new Number125();
    System.out.println(number125.isPalindrome("0P"));
//    System.out.println(number125.isPalindrome("A man, a plan, a canal: Panama"));
    System.out.println('A' - 'a');
    System.out.println('0'-'A');
    System.out.println('O'-'A');
    System.out.println('P'-'A');
    System.out.println(Character.isLetterOrDigit('0'));
    System.out.println(Character.isLetterOrDigit('A'));
  }

  public boolean isPalindrome(String s) {
    int l = 0, r = s.length() - 1;  //初始化
    while (l < r) {
      while (l < r && !Character.isLetterOrDigit(s.charAt(l)))    //去掉左边非字母
        ++l;
      while (l < r && !Character.isLetterOrDigit(s.charAt(r)))    //去掉右边非字母
        --r;
      if (l < r) {
        if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) //都转换成小写比较
          return false;
        ++l;
        --r;
      }
    }
    return true;
  }

}
