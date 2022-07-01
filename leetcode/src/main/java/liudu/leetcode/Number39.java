package liudu.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//39. 组合总和
public class Number39 {

  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    //O(n*log(n))
    Arrays.sort(candidates);

    ArrayList<List<Integer>> result = new ArrayList<>();
    ArrayList<Integer> tmp = new ArrayList<>();
    combinationSum(candidates, target, result, tmp, 0);
    return result;
  }

  public void combinationSum(int[] candidates, int target, List<List<Integer>> result,
      List<Integer> tmp, int min) {
    if (target == 0) {
      result.add(new ArrayList<>(tmp));
    } else if (target > 0) {
      for (int candidate : candidates) {
        if (candidate < min) {
          continue;
        }
        tmp.add(candidate);
        combinationSum(candidates, target - candidate, result, tmp, candidate);
        tmp.remove(tmp.size() - 1);
      }
    }
  }
}
