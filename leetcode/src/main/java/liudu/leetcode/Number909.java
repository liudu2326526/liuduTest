package liudu.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Number909 {

  public static void main(String[] args) {
//    System.out.println((-6) / 6);

//    int[][] ints = {
//        {-1, -1, -1, -1, -1, -1},
//        {-1, -1, -1, -1, -1, -1},
//        {-1, -1, -1, -1, -1, -1},
//        {-1, 35, -1, -1, 13, -1},
//        {-1, -1, -1, -1, -1, -1},
//        {-1, 15, -1, -1, -1, -1}
//    };
//    int[][] ints = {
//        {-1, -1},
//        {-1, 3}
//    };
//    int[][] ints = {
//        {-1, 4, -1},
//        {6, 2, 6},
//        {-1, 3, -1}
//    };
    int[][] ints = {
        {-1, 15, 9, 1, -1},
        {-1, -1, 10, 5, 19},
        {18, -1, 23, 9, -1},
        {1, 13, -1, 16, 20},
        {-1, 10, 10, 25, 13}
    };

    Number909 number909 = new Number909();
    System.out.println(number909.snakesAndLadders(ints));

  }
  public int snakesAndLadders(int[][] board) {
    int n = board.length;
    boolean[] vis = new boolean[n * n + 1];
    Queue<int[]> queue = new LinkedList<int[]>();
    queue.offer(new int[]{1, 0});
    while (!queue.isEmpty()) {
      int[] p = queue.poll();
      for (int i = 1; i <= 6; ++i) {
        int nxt = p[0] + i;
        if (nxt > n * n) { // 超出边界
          break;
        }
        int[] rc = id2rc(nxt, n); // 得到下一步的行列
        if (board[rc[0]][rc[1]] > 0) { // 存在蛇或梯子
          nxt = board[rc[0]][rc[1]];
        }
        if (nxt == n * n) { // 到达终点
          return p[1] + 1;
        }
        if (!vis[nxt]) {
          vis[nxt] = true;
          queue.offer(new int[]{nxt, p[1] + 1}); // 扩展新状态
        }
      }
    }
    return -1;
  }

  public int[] id2rc(int id, int n) {
    int r = (id - 1) / n, c = (id - 1) % n;
    if (r % 2 == 1) {
      c = n - 1 - c;
    }
    return new int[]{n - 1 - r, c};
  }


  public int snakesAndLadders1(int[][] board) {
    int num = 1;
    HashMap<Integer, Node1> map = new HashMap<>();
    for (int i = board.length - 1; i >= 0; i--) {
      if ((board.length - i) % 2 == 1) {
        for (int j = 0; j < board[i].length; j++) {
          Node1 node1;
          if (map.containsKey(num)) {
            node1 = map.get(num);
            map.get(num - 1).next = node1;
          } else {
            node1 = new Node1();
            if (num > 1) {
              map.get(num - 1).next = node1;
            }
            map.put(num, node1);
          }
          if (board[i][j] > 0) {
            if (map.containsKey(board[i][j])) {
              node1.link = map.get(board[i][j]);
            } else {
              Node1 linkNode = new Node1();
              map.put(board[i][j], linkNode);
              node1.link = linkNode;
            }
          }
          num++;

        }
      } else {
        for (int j = board[i].length - 1; j >= 0; j--) {
          Node1 node1;
          if (map.containsKey(num)) {
            node1 = map.get(num);
            map.get(num - 1).next = node1;
          } else {
            node1 = new Node1();
            map.get(num - 1).next = node1;
            map.put(num, node1);
          }
          if (board[i][j] > 0) {
            if (map.containsKey(board[i][j])) {
              node1.link = map.get(board[i][j]);
            } else {
              Node1 linkNode = new Node1();
              map.put(board[i][j], linkNode);
              node1.link = linkNode;
            }
          }
          num++;

        }
      }

    }

    ergodic(map.get(1), 0, map);

    if (map.get(num - 1).value < Integer.MAX_VALUE) {
      return map.get(num - 1).value;
    } else {
      return -1;
    }

  }


  public void ergodic(Node1 node, int add, HashMap<Integer, Node1> nodeMap) {
    int num = 5;
    Node1 tag = null;
    while (node != null && num < 12) {
      int step = num / 6 + add;

      if (node.value < step) {
        break;
      }

      node.value = step;

      if (node.link != null) {
        if (node.link != node) {
          ergodic(node.link, step, nodeMap);
        }
      }

      if (node.link == null && num != 5) {
        tag = node;
      }

      node = node.next;
      num++;
    }

    if (tag != null) {
      ergodic(tag, tag.value, nodeMap);
    }


  }

}

class Node1 {

  Node1 next;
  Node1 link;
  int value = Integer.MAX_VALUE;
}
