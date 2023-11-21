package liudu.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Number40 {

  public static void main(String[] args) {
//    int[] candidates = {10, 1, 2, 7, 6, 1, 5};
    int[] candidates = {2, 5, 2, 1, 2};
    int target = 5;
//    int target = 8;
    Number40 number40 = new Number40();
    System.out.println(number40.combinationSum2(candidates, target));

  }

  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    Arrays.sort(candidates);
    ArrayList<List<Integer>> lists = new ArrayList<>();
    ArrayList<Integer> integers = new ArrayList<>();
    combination(candidates, target, integers, 0, lists);

    return lists;
  }


  public void combination(int[] candidates, int target, List<Integer> list, int index,
      List<List<Integer>> lists) {
    if (target == 0) {
      ArrayList<Integer> integers = new ArrayList<>(list);
      lists.add(integers);
      return;
    }

    if (index > candidates.length - 1) {
      return;
    }

    if (target >= candidates[index]) {
      for (int i = index; i < candidates.length; i++) {
        list.add(candidates[i]);
        combination(candidates, target - candidates[i], list, i + 1, lists);
        list.remove(list.size() - 1);
        while (i + 1 < candidates.length && candidates[i] == candidates[i + 1]) {
          i++;
        }
      }
    }
  }
}
