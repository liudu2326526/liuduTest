package liudu.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Number841 {

  public static void main(String[] args) {
    Number841 number841 = new Number841();
    ArrayList<List<Integer>> rooms = new ArrayList<>();
    ArrayList<Integer> integers1 = new ArrayList<>();
    ArrayList<Integer> integers2 = new ArrayList<>();
    ArrayList<Integer> integers3 = new ArrayList<>();
    ArrayList<Integer> integers4 = new ArrayList<>();
    integers1.add(1);
    integers2.add(2);
    integers3.add(3);

    rooms.add(integers1);
    rooms.add(integers2);
    rooms.add(integers3);
    rooms.add(integers4);

    System.out.println(number841.canVisitAllRooms(rooms));


  }

  public boolean canVisitAllRooms(List<List<Integer>> rooms) {

    LinkedList<Integer> integers = new LinkedList<>();

    boolean[] visible = new boolean[rooms.size()];

    for (int i = 0; i < rooms.get(0).size(); i++) {
      integers.addFirst(rooms.get(0).get(i));
    }
    visible[0] = true;

    while (!integers.isEmpty()) {
      Integer integer = integers.removeLast();
      if (!visible[integer]) {
        for (int i = 0; i < rooms.get(integer).size(); i++) {
          Integer i1 = rooms.get(integer).get(i);
          if (!visible[i1]){
            integers.addFirst(i1);
          }
        }
        visible[integer] = true;
      }
    }

    for (boolean b : visible) {
      if (!b) {
        return false;
      }

    }

    return true;
  }

}
