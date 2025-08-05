package liudu.leetcode;

import java.util.Arrays;

/**
 * @author Liu Du
 * @Date 2025/8/5
 */
public class Number3477 {

  public static void main(String[] args) {
    Number3477 number3477 = new Number3477();
    int[] fruits = {4, 2, 5};
    int[] baskets = {3, 5, 4};
    System.out.println(number3477.numOfUnplacedFruits(fruits, baskets));
  }

  public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
    int n = fruits.length;
    int num = 0;
    for (int i = 0; i < n; i++) {
      boolean tag = false;
      for (int j = 0; j < baskets.length; j++) {
        if (fruits[i] <= baskets[j]) {
          baskets[j] = 0;
          tag = true;
          break;
        }
      }
      if (!tag) {
        num++;
      }

    }

    return num;
  }
}
