package liudu.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Number77 {

  public static void main(String[] args) {
    System.out.println(new Number77().combine(4, 2));
  }

  public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> lists = new ArrayList<>();
    ArrayList<Integer> tmp = new ArrayList<>();
    combine(n, k, 1, lists, tmp);

    return lists;
  }

  public void combine(int n, int k, int begin, List<List<Integer>> results, List<Integer> tmp) {
    if (tmp.size() == k) {
      ArrayList<Integer> result = new ArrayList<>(tmp);
      results.add(result);
      return;
    }

    for (int i = begin; i <= n; i++) {
      tmp.add(i);
      combine(n, k, i + 1, results, tmp);
      tmp.remove(tmp.size() - 1);

    }


  }


}
