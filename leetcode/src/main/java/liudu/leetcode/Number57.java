package liudu.leetcode;

import java.util.ArrayList;

public class Number57 {

  public static void main(String[] args) {
    Number57 number57 = new Number57();

    int[][] intss = {{1, 5}};
    int[] ints = {2, 3};
    number57.insert(intss, ints);
  }

  public int[][] insert(int[][] intervals, int[] newInterval) {
    int left = newInterval[0];
    int right = newInterval[1];
    boolean tag = true;
    ArrayList<int[]> ints = new ArrayList<>();
    if (intervals.length == 0) {
      ints.add(newInterval);
    }

    for (int[] interval : intervals) {
      if (tag) {
        if (right < interval[0]) {
          ints.add(new int[]{left, right});
          ints.add(interval);
          tag = false;
        } else if (left > interval[1]) {
          ints.add(interval);
        } else {
          left = Math.min(left, interval[0]);
          right = Math.max(right, interval[1]);
        }
      } else {
        ints.add(interval);
      }
    }

    if (intervals.length > 0 && tag){
      ints.add(new int[]{left, right});
    }

    int[][] ints1 = new int[ints.size()][];
    for (int i = 0; i < ints.size(); i++) {
      ints1[i] = ints.get(i);
    }

    return ints1;
  }

}
