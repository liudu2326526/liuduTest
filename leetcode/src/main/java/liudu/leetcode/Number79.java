package liudu.leetcode;

//79. 单词搜索
public class Number79 {

  public static void main(String[] args) {
    char[][] chars = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'E', 'S'}, {'A', 'D', 'E', 'E'}};
    System.out.println(new Number79().exist(chars, "ABCESEEEFS"));
  }


  public boolean exist(char[][] board, String word) {

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        int[][] ints = new int[board.length][board[0].length];
        if (exist(board, word, i, j, 0, ints)) {
          return true;
        }
      }
    }
    return false;
  }

  public boolean exist(char[][] board, String word, int i, int j, int index, int[][] path) {
    if (index > word.length() - 1) {
      return true;
    }
    if (i < 0 || i > board.length - 1) {
      return false;
    }
    if (j < 0 || j > board[0].length - 1) {
      return false;
    }
    if (board[i][j] != word.charAt(index)) {
      return false;
    }
    if (path[i][j] == 1) {
      return false;
    }
    path[i][j] = 1;
    if (exist(board, word, i + 1, j, index + 1, path) ||
        exist(board, word, i - 1, j, index + 1, path) ||
        exist(board, word, i, j + 1, index + 1, path) ||
        exist(board, word, i, j - 1, index + 1, path)) {
      return true;
    }
    path[i][j] = 0;
    return false;
  }
}
