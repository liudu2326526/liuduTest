package liudu.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Number46 {

  public List<List<Integer>> permute(int[] nums) {
    HashSet<Integer> set = new HashSet<>();
    ArrayList<Integer> list = new ArrayList<>();
    ArrayList<List<Integer>> lists = new ArrayList<>();

    permute(nums, list, set, lists);
    return lists;
  }

  public void permute(int[] nums, List<Integer> list, Set<Integer> set,
      List<List<Integer>> result) {
    if (set.size() == nums.length) {
      result.add(new ArrayList<>(list));
      return;
    }
    for (int num : nums) {
      if (!set.contains(num)) {
        list.add(num);
        set.add(num);
        permute(nums, list, set, result);
        list.remove(list.size() - 1);
        set.remove(num);
      }
    }
  }


}
