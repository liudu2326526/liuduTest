package liudu.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * @author Liu Du
 * @Date 2025/8/4
 */
public class Number904 {

  public static void main(String[] args) {
    Number904 number904 = new Number904();
    int[] ints = {1, 0, 1, 4, 1, 4, 1, 2, 3};
    System.out.println(number904.totalFruit(ints));
  }

  public int totalFruit(int[] fruits) {
    HashMap<Integer, Integer> map = new HashMap<>();
    LinkedList<Integer> queue = new LinkedList<>();
    int n = fruits.length;
    int max = 0;
    for (int i = 0; i < n; i++) {
      if (!map.containsKey(fruits[i])) {
        //如果 queue 长度小于等于 2
        if (queue.size() >= 2) {
          //计算当前长度，并更新最大长度
          max = Math.max(max, i - queue.peek());
          //删除队列里之前的数据
          Integer poll = queue.poll();
          //如果 poll 的最后的数大于下一个最后的数，则将两个都弹出
          Integer index = map.get(fruits[poll]);

          if (index > map.get(fruits[queue.peek()])) {
            Integer secondPoll = queue.poll();
            queue.add(map.get(fruits[secondPoll]) + 1);
            map.remove(fruits[secondPoll]);
          } else {
            //如果不大于，则更新下一个数的位置
            map.remove(fruits[poll]);
            queue.poll();
            queue.add(index + 1);
          }
        }
        queue.add(i);
      }
      // 记录对应值的最后位置
      map.put(fruits[i], i);
    }

    max = Math.max(max, n - queue.peek());

    return max;
  }

}
