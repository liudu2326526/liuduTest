package liudu.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Liu Du
 * @Date 2025/8/1
 */
public class Number118 {

  public static void main(String[] args) {
    Number118 number118 = new Number118();
    System.out.println(number118.generate(5));
  }

  public List<List<Integer>> generate(int numRows) {
    ArrayList<List<Integer>> lists = new ArrayList<>();
    ArrayList<Integer> newList1 = new ArrayList<>();
    newList1.add(1);
    lists.add(newList1);

    for (int i = 1; i < numRows; i++) {
      ArrayList<Integer> newList = new ArrayList<>(i);
      for (int j = 0; j <= i; j++) {
        if (j == 0 || j == i) {
          newList.add(1);
        } else {
          List<Integer> list = lists.get(i - 1);
          newList.add(list.get(j - 1) + list.get(j));
        }
      }
      lists.add(newList);
    }


    return lists;
  }


}
