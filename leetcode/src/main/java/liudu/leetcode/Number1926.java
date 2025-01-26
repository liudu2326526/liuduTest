package liudu.leetcode;

import java.util.LinkedList;

public class Number1926 {

  public static void main(String[] args) {
    Number1926 number1926 = new Number1926();
    char[][] chars = {
        {'+', '.', '+', '+', '+', '+', '+'},
        {'+', '.', '+', '.', '.', '.', '+'},
        {'+', '.', '+', '.', '+', '.', '+'},
        {'+', '.', '.', '.', '.', '.', '+'},
        {'+', '+', '+', '+', '.', '+', '.'}
    };

    int[] ints = {0, 1};

    System.out.println(number1926.nearestExit(chars, ints));

  }

  public int nearestExit(char[][] maze, int[] entrance) {
    int high = maze.length;
    int length = maze[0].length;
    boolean[][] visible = new boolean[high][length];


    LinkedList<int[]> linkedList = new LinkedList<>();
    linkedList.addFirst(new int[]{entrance[0], entrance[1], 0});

    while (!linkedList.isEmpty()) {
      int[] ints = linkedList.removeLast();
      if (ints[1] < 0 || ints[0] < 0 || ints[0] > maze.length - 1 || ints[1] > maze[0].length - 1
          || maze[ints[0]][ints[1]] == '+' || visible[ints[0]][ints[1]]) {
        continue;
      } else if ((ints[1] == 0 || ints[0] == 0 || ints[0] == maze.length - 1
          || ints[1] == maze[0].length - 1) && ints[2] > 0) {
        return ints[2];
      } else {
        visible[ints[0]][ints[1]] = true;
        linkedList.addFirst(new int[]{ints[0] + 1, ints[1], ints[2] + 1});
        linkedList.addFirst(new int[]{ints[0] - 1, ints[1], ints[2] + 1});
        linkedList.addFirst(new int[]{ints[0], ints[1] + 1, ints[2] + 1});
        linkedList.addFirst(new int[]{ints[0], ints[1] - 1, ints[2] + 1});
      }

    }

    return -1;
  }


}
