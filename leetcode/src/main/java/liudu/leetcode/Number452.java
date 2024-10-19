package liudu.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Liu Du
 * @Date 2024/10/19
 */

/*
有一些球形气球贴在一堵用 XY 平面表示的墙面上。墙面上的气球记录在整数数组 points ，其中points[i] = [xstart, xend]
表示水平直径在 xstart 和 xend之间的气球。你不知道气球的确切 y 坐标。
一支弓箭可以沿着 x 轴从不同点 完全垂直 地射出。在坐标 x 处射出一支箭，
若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被 引爆 。可以射出的弓箭的数量 没有限制 。
弓箭一旦被射出之后，可以无限地前进。

给你一个数组 points ，返回引爆所有气球所必须射出的 最小 弓箭数 。

示例 1：

输入：points = [[10,16],[2,8],[1,6],[7,12]]
输出：2
解释：气球可以用2支箭来爆破:
-在x = 6处射出箭，击破气球[2,8]和[1,6]。
-在x = 11处发射箭，击破气球[10,16]和[7,12]。
示例 2：

输入：points = [[1,2],[3,4],[5,6],[7,8]]
输出：4
解释：每个气球需要射出一支箭，总共需要4支箭。
示例 3：

输入：points = [[1,2],[2,3],[3,4],[4,5]]
输出：2
解释：气球可以用2支箭来爆破:
- 在x = 2处发射箭，击破气球[1,2]和[2,3]。
- 在x = 4处射出箭，击破气球[3,4]和[4,5]。

提示:

1 <= points.length <= 105
points[i].length == 2
-231 <= xstart < xend <= 231 - 1
 */
public class Number452 {

  public static void main(String[] args) {
    int[][] ints = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
//    int[][] ints = {{1, 2}, {3, 4}, {5, 6}, {7, 8}};
    Number452 number452 = new Number452();
    System.out.println(number452.findMinArrowShots(ints));

  }


  public int findMinArrowShots(int[][] points) {
    List<int[]> collect = Arrays.stream(points).sorted(Comparator.comparingInt(s -> s[0]))
        .collect(Collectors.toList());

    ArrayList<int[]> arrayLists = new ArrayList<>();

    int num = 0;

    for (int[] ints : collect) {
      if (arrayLists.size() == 0) {
        arrayLists.add(ints);
        num++;
      } else {
        int[] last = arrayLists.get(arrayLists.size() - 1);
        int[] sameArrow = findSameArrow(last, ints);
        if (sameArrow == null) {
          arrayLists.add(ints);
          num++;
        } else {
          arrayLists.set(arrayLists.size() - 1, sameArrow);
        }
      }
    }
    return num;
  }


  public int findMinArrowShots2(int[][] points) {
    List<int[]> collect = Arrays.stream(points).sorted(Comparator.comparingInt(s -> s[0]))
        .collect(Collectors.toList());

    ArrayList<int[]> arrayLists = new ArrayList<>();

    int num = 0;

    for (int[] ints : collect) {
      if (arrayLists.size() == 0) {
        arrayLists.add(ints);
        num++;
      } else {
        for (int i = 0; i < arrayLists.size(); i++) {
          int[] sameArrow = findSameArrow(arrayLists.get(i), ints);
          if (sameArrow == null && i == arrayLists.size() - 1) {
            arrayLists.add(ints);
            num++;
            break;
          }
          if (sameArrow != null) {
            arrayLists.set(i, sameArrow);
            break;
          }
        }
      }


    }

    return num;
  }

  public int[] findSameArrow(int[] ints1, int[] ints2) {
    int[] clone1 = ints1.clone();
    int[] clone2 = ints2.clone();

    if (ints1[0] > ints2[0]) {
      int[] tmp = clone1;
      clone1 = clone2;
      clone2 = tmp;
    }

    int[] ints;
    if (clone1[1] < clone2[0]) {
      return null;
    } else {
      ints = new int[2];
      ints[0] = clone2[0];
      ints[1] = Math.min(clone1[1], clone2[1]);
    }

    return ints;

  }

}
