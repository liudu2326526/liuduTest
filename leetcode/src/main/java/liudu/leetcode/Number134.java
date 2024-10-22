package liudu.leetcode;

public class Number134 {

  public static void main(String[] args) {
    Number134 number134 = new Number134();
    int[] gas = {1, 2, 3, 4, 5};
//    int[] gas = {5, 8, 2, 8};
    int[] cost = {3, 4, 5, 1, 2};
//    int[] cost = {6, 5, 6, 6};

    System.out.println(number134.canCompleteCircuit2(gas, cost));

  }

  public int canCompleteCircuit(int[] gas, int[] cost) {
    int sum = 0;
    int max = Integer.MIN_VALUE;
    int maxIndex = 0;

    for (int i = 0; i < gas.length; i++) {
      int sub = gas[i] - cost[i];
      sum += sub;
      if (sub > max) {
        max = sub;
        maxIndex = i;
      }
    }

    if (sum < 0) {
      return -1;
    } else {
      return maxIndex;
    }

  }

  public int canCompleteCircuit2(int[] gas, int[] cost) {
    int i = 0;
    int n = gas.length;

    while (i < n) {
      int sumGas = 0;
      int sumCost = 0;
      int num = 0;
      //      判断从当前开始是否可以走完循环
      while (num < n) {
        int j = (i + num) % n;
        sumCost += cost[j];
        sumGas += gas[j];
        if (sumCost > sumGas) {
          break;
        }
        num++;
      }
      if (num == n) {
        return i;
      } else {
        i = i + num + 1;
      }


    }

    return -1;
  }

}
