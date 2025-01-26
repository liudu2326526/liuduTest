package liudu.leetcode;

public class Number279 {

  public static void main(String[] args) {
    Number279 number279 = new Number279();
    System.out.println(number279.numSquares(12));
  }

  public int numSquares(int n) {
    if (n == 0) {
      return 0;
    }

    double sqrt = Math.sqrt(n);
    double pow = Math.pow((int) sqrt, 2);
    int sub = (int) (n - pow);

    return numSquares(sub) + 1;
  }

}
