package liudu.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Number47 {

  public static void main(String[] args) {
    List<List<Integer>> permute = new Number47().permuteUnique(new int[]{1, 1, 2});
    System.out.println(permute);
  }

  public List<List<Integer>> permuteUnique(int[] nums) {
    HashSet<Integer> set = new HashSet<>();
    ArrayList<Integer> list = new ArrayList<>();
    HashSet<List<Integer>> sets = new HashSet<>();

    permute1(nums, list, set, sets);

    return new ArrayList<>(sets);
  }

  public void permute1(int[] nums, List<Integer> list, Set<Integer> set,
      Set<List<Integer>> result) {
    if (set.size() == nums.length) {
      result.add(new ArrayList<>(list));
      return;
    }
    HashSet<Integer> repetition = new HashSet<>();
    for (int i = 0; i < nums.length; i++) {

      if (!set.contains(i)) {
        if (repetition.contains(nums[i])){
          continue;
        } else {
          repetition.add(nums[i]);
        }
        list.add(nums[i]);
        set.add(i);
        permute1(nums, list, set, result);
        list.remove(list.size() - 1);
        set.remove(i);
      }
    }

  }
}
