package liudu.test;

public class EightDay01 {

  public static void main(String[] args) {
    System.out.println(new SolutionEightDay01().fib(50));
  }

}

class SolutionEightDay01 {

  public int fib(int n) {
    if (n == 0) {
      return 0;
    }
    if (n == 1) {
      return 1;
    }

    return fib(0, 1, n - 1);
  }

  public int fib(int x, int y, int num) {
    if (num == 0) {
      return y;
    }

    int tmp = (y + x) % 1000000007;
    x = y;

    return fib(x, tmp, num - 1);
  }
}