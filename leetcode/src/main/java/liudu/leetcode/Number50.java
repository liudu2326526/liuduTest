package liudu.leetcode;

public class Number50 {

  public static void main(String[] args) {
    System.out.println(new Number50().myPow(2.0, 10));

  }

  public double myPow(double x, int n) {
    return n > 0 ? quickMul(x, n) : 1 / quickMul(x, -n);
  }

  public double quickMul(double x, int n) {
    if (n == 0) {
      return 1;
    }
    double y = quickMul(x, n / 2);
    return n % 2 == 0 ? y * y : y * y * x;
  }

}
