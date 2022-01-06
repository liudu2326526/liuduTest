package liudu.test;

public class EightDay03 {

}

class SolutionEightDay03 {

  public int maxProfit(int[] prices) {
    if (prices.length == 0){
      return 0;
    }

    int result = 0;

    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;
    for (int price : prices) {
      if (price < min){
        min = price;
        max = min;
      }
      if (price > max){
        max = price;
      }
      result = Math.max(result,max - min);
    }

    return result;
  }
}