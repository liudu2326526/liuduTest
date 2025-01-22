package liudu.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author Liu Du
 * @Date 2025/1/22
 */
public class Number239 {

  public static void main(String[] args) {
//    int[] i = {1, 3, -1, -3, 5, 3, 6, 7};
    int[] i = {1, -1};
    Number239 number239 = new Number239();
    System.out.println(Arrays.toString(number239.maxSlidingWindow(i, 1)));
  }


  // 双端队列，新数据进来之后淘汰掉数据量小的队列
  public int[] maxSlidingWindow(int[] nums, int k) {
    int[] ints = new int[nums.length - k + 1];
    LinkedList<Integer> queue = new LinkedList<>();

    for (int i = 0; i < nums.length; i++) {
      if (!queue.isEmpty() && queue.getFirst() < i - k + 1) {
        queue.removeFirst();
      }

      while (!queue.isEmpty() && nums[queue.getLast()] < nums[i]) {
        queue.removeLast();
      }
      queue.addLast(i);

      if (i >= k - 1) {
        ints[i - k + 1] = nums[queue.getFirst()];
      }

    }

    return ints;
  }

  //  以下是基于最小堆（Min-Heap）的优先队列实现，其中优先级较高的元素在堆顶，值较小的元素优先出队。
  public int[] maxSlidingWindow2(int[] nums, int k) {
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());

    int[] ints = new int[nums.length - k + 1];

    for (int i = 0; i < k; i++) {
      priorityQueue.add(nums[i]);
    }

    ints[0] = priorityQueue.peek();

    for (int i = k; i < nums.length; i++) {
      priorityQueue.remove(nums[i - k]);
      priorityQueue.add(nums[i]);

      ints[i - k + 1] = priorityQueue.peek();
    }

    return ints;
  }

}
