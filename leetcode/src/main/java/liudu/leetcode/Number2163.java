package liudu.leetcode;

import java.util.PriorityQueue;

/**
 * @author Liu Du
 * @Date 2025/7/18
 */
public class Number2163 {

  public static void main(String[] args) {
    Number2163 number2163 = new Number2163();
//    int[] ints = {7, 9, 5, 8, 1, 3};
    int[] ints = {3, 1, 2};
    System.out.println(number2163.minimumDifference(ints));
  }


  public long minimumDifference(int[] nums) {
    int l = nums.length;
    int n = l / 3;
    long[] minN = new long[n + 1];
    long[] maxN = new long[n + 1];
    long tmp = 0;

//  找到 index 前 n 个和最小的数
    PriorityQueue<Integer> maxQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
    for (int i = 0; i < n; i++) {
      maxQueue.add(nums[i]);
      tmp += nums[i];
    }

    minN[0] = tmp;

    for (int i = n; i < 2 * n; i++) {

      if (maxQueue.peek() > nums[i]) {
        Integer poll = maxQueue.poll();
        maxQueue.add(nums[i]);
        tmp = tmp - poll + nums[i];
      }
      minN[i - n + 1] = tmp;
    }

    PriorityQueue<Integer> minQueue = new PriorityQueue<>();

    tmp = 0;
    for (int i = 3 * n - 1; i >= 2 * n; i--) {
      minQueue.add(nums[i]);
      tmp += nums[i];
    }

    maxN[n] = tmp;

    for (int i = 2 * n - 1; i >= n; i--) {

      if (minQueue.peek() < nums[i]) {
        Integer poll = minQueue.poll();
        minQueue.add(nums[i]);
        tmp = tmp - poll + nums[i];
      }
      maxN[i - n] = tmp;
    }

    long result = Long.MAX_VALUE;

    for (int i = 0; i < n + 1; i++) {
      result = Math.min(result, minN[i] - maxN[i]);
    }

    return result;
  }

}
