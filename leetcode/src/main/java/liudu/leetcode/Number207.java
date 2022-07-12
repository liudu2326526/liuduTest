package liudu.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 207. 课程表
 * <p>
 * 你这个学期必须选修 numCourses 门课程，记为0到numCourses - 1 。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组prerequisites 给出，其中prerequisites[i] = [ai, bi] ，表示如果要学习课程
 * ai 则 必须 先学习课程 bi 。
 * <p>
 * 例如，先修课程对[0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 */
public class Number207 {

  public static void main(String[] args) {
    int[][] ints = {{1, 0}, {0, 1}};
    new Number207().canFinish(2, ints);
  }


  public boolean canFinish(int numCourses, int[][] prerequisites) {
    // 需要一个数组记录所有节点，以及节点的依赖
    HashMap<Integer, Set<Integer>> map = new HashMap<>();
    for (int i = 0; i < prerequisites.length; i++) {
      for (int j = 0; j < prerequisites[i].length; j++) {
        if (!map.containsKey(prerequisites[i][j])) {
          HashSet<Integer> set = new HashSet<>();
          if (j > 0) {
            set.add(prerequisites[i][j - 1]);
          }
          map.put(prerequisites[i][j], set);
        } else {
          if (j > 0) {
            map.get(prerequisites[i][j]).add(prerequisites[i][j - 1]);
          }
        }
      }
    }

    // 需要一个队列记录能够被学习的节点
    LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
    while (!map.isEmpty()) {
      for (Entry<Integer, Set<Integer>> integerSetEntry : map.entrySet()) {
        if (integerSetEntry.getValue().isEmpty()) {
          try {
            queue.put(integerSetEntry.getKey());
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        // bug

      }
      if (queue.isEmpty()) {
        return false;
      }
      while (!queue.isEmpty()) {
        Integer poll = queue.poll();
        map.remove(poll);
        for (Entry<Integer, Set<Integer>> integerSetEntry : map.entrySet()) {
          integerSetEntry.getValue().remove(poll);
        }
      }

    }

    return true;
  }
}
