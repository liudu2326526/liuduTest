package liudu.leetcode;

public class Number172 {

  public static void main(String[] args) {
    Number172 number172 = new Number172();
    System.out.println(number172.trailingZeroes(5));
  }

  public int trailingZeroes(int n) {
    int num = 0;
    for (int i = 5; i <= n; i += 5) {
      for (int j = i; j % 5 == 0; j /= 5) {
        num++;
      }
    }

    return num;
  }

}
