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

  /**
   /**
    * 使用双端队列（Deque）实现滑动窗口最大值
    * 
    * 算法思路：
    * 1. 维护一个单调递减的双端队列，队列中存储的是数组元素的索引
    * 2. 队列头部始终是当前窗口内的最大值索引
    * 3. 当新元素进入时，从队列尾部开始移除所有小于新元素的索引
    * 4. 当队列头部索引超出窗口范围时，移除头部元素
    * 
    * 时间复杂度：O(n)，每个元素最多入队出队一次
    * 空间复杂度：O(k)，队列最多存储k个元素
    * 
    * @param nums 输入数组
    * @param k 滑动窗口大小
    * @return 每个滑动窗口的最大值数组
    */ 
  public int[] maxSlidingWindow2(int[] nums, int k) {
    // 创建一个大顶堆优先队列，用于维护窗口内的最大值
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());

    // 创建结果数组，长度为原数组长度减去窗口大小加1
    int[] ints = new int[nums.length - k + 1];

    // 将前k个元素加入优先队列，构建初始窗口
    for (int i = 0; i < k; i++) {
      priorityQueue.add(nums[i]);
    }

    // 第一个窗口的最大值就是队列的顶部元素
    ints[0] = priorityQueue.peek();

    // 滑动窗口，每次移动一位
    for (int i = k; i < nums.length; i++) {
      // 移除窗口最左边的元素（窗口左边界）
      priorityQueue.remove(nums[i - k]);
      // 添加窗口最右边的新元素（窗口右边界）
      priorityQueue.add(nums[i]);

      // 当前窗口的最大值就是队列的顶部元素
      ints[i - k + 1] = priorityQueue.peek();
    }

    // 返回所有窗口的最大值数组
    return ints;
  }

}
