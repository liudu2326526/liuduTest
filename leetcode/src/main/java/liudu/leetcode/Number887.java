package liudu.leetcode;

public class Number887 {

  public int superEggDrop(int k, int n) {
    if (n ==2){
      return 2;
    }
    while (k > 1) {
      if (n % 2 == 0) {
        n = n / 2;
      } else {
        n = n / 2 + 1;
      }

      if (n == k) {
        break;
      }

      k--;
    }

    return k - 1 + n;
  }

}
