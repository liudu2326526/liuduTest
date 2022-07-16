package liudu.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author liudu
 * @title: Number56
 * @projectName liuduTest
 * @description: 以数组 intervals 表示若干个区间的集合， 其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 * @date 2022/7/13下午4:23
 */
public class Number56 {

  // 双指针遍历
  public int[][] merge2(int[][] intervals) {
    Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
    ArrayList<int[]> ints = new ArrayList<>();
    int begin = intervals[0][0];
    int end = intervals[0][1];
    for (int i = 0; i < intervals.length; i++) {
      if (intervals[i][0] <= end) {
        end = Math.max(end, intervals[i][1]);
      } else {
        ints.add(new int[]{begin, end});
        end = intervals[i][1];
        begin = intervals[i][0];
      }
    }

    ints.add(new int[]{begin, end});

    return ints.toArray(new int[ints.size()][]);
  }

  public int[][] merge(int[][] intervals) {
    Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

    ArrayList<int[]> ints = new ArrayList<>();
    label:
    for (int i = 0; i < intervals.length; i++) {
      for (int j = 0; j < ints.size(); j++) {
        int[] tmp = merge(intervals[i], ints.get(j));
        if (tmp != null) {
          ints.set(j, tmp);
          continue label;
        }
      }
      ints.add(intervals[i]);
    }

    ints.toArray(new int[ints.size()][]);
    return ints.toArray(new int[ints.size()][]);
  }

  public int[] merge(int[] a, int[] b) {
    // a 在前
    if (a[0] > b[0]) {
      int[] tmp = a;
      a = b;
      b = tmp;
    }
    if (b[0] > a[1]) {
      return null;
    }

    return new int[]{a[0], Math.max(a[1], b[1])};
  }


}
