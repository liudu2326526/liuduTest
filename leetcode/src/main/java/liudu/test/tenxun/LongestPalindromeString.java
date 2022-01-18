package liudu.test.tenxun;

public class LongestPalindromeString {

  public static void main(String[] args) {
    System.out.println(new SolutionLongestPalindromeString().longestPalindrome("babad"));
  }

}


class SolutionLongestPalindromeString {

  public String longestPalindrome(String s) {
    char[] charArray = s.toCharArray();
    int length = charArray.length;
    if (length < 2) {
      return s;
    }

    int begin = 0;
    int leng = 1;

    for (int i = 0; i < charArray.length - 1; i++) {
      int i1 = centerSpread(charArray, i, i + 1);
      int i2 = centerSpread(charArray, i, i);
      int max = Math.max(i1, i2);
      if (max > leng) {
        leng = max;
        begin = i - (max - 1) / 2;
      }
    }

    return s.substring(begin, begin + leng);
  }

  private boolean isPalindrome(char[] charArray, int left, int right) {
    while (left < right) {
      if (charArray[left] != charArray[right]) {
        return false;
      }
      left++;
      right--;
    }

    return true;
  }

  private int centerSpread(char[] charArray, int left, int right) {
    while (left >= 0 && right < charArray.length && charArray[left] == charArray[right]) {
      left--;
      right++;
    }
    return right - left - 1;
  }

}