package liudu.leetcode;

//如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
//    x<2    1 -> 0

//如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
//    2<= x <=3    1 -> 1

//如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
//    x > 3    1 -> 0

//如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
//    x = 3    0 -> 1

import java.util.Arrays;

public class Number289 {

  public static void main(String[] args) {
    int[][] ints = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};

    Number289 number289 = new Number289();
    number289.gameOfLife(ints);

    System.out.println(Arrays.deepToString(ints));
  }

  public void gameOfLife(int[][] board) {

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] % 10 == 1) {
          addLive(board, i, j);
        }
      }
    }

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        int num = board[i][j] / 10;
        int original = board[i][j] % 10;
        if (original == 1) {
          if (num < 2 || num > 3) {
            board[i][j] = 0;
          } else {
            board[i][j] = 1;
          }
        } else {
          if (num == 3) {
            board[i][j] = 1;
          } else {
            board[i][j] = 0;
          }
        }
      }
    }


  }

  public void addLive(int[][] board, int i, int j) {
    for (int i1 = Math.max(0, i - 1); i1 <= Math.min(board.length - 1, i + 1); i1++) {
      for (int j1 = Math.max(0, j - 1); j1 <= Math.min(board[i].length - 1, j + 1); j1++) {
        if (i != i1 || j != j1) {
          board[i1][j1] += 10;
        }
      }
    }
  }

}
