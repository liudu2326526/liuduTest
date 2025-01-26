package liudu.leetcode;

public class Number130 {

  public static void main(String[] args) {
    Number130 number130 = new Number130();
//    char[][] chars = {
//        {'X', 'X', 'X', 'X'},
//        {'X', 'O', 'O', 'X'},
//        {'X', 'X', 'O', 'X'},
//        {'X', 'O', 'X', 'X'}
//    };
    char[][] chars = {
        {'X', 'O', 'X'},
        {'O', 'X', 'O'},
        {'X', 'O', 'X'}
    };
    number130.solve(chars);

  }


  public void solve(char[][] board) {
    int high = board.length;
    int wight = board[0].length;
    char[][] chars = new char[high][wight];

//    遍历四条边
    for (int i = 0; i < board[0].length; i++) {
      if (board[0][i] == 'O' && chars[0][i] != 'O') {
        tag(board, chars, i, 0);
      }
    }
    if (high > 1) {
      for (int i = 0; i < board[0].length; i++) {
        if (board[high - 1][i] == 'O' && chars[high - 1][i] != 'O') {
          tag(board, chars, i, high - 1);
        }
      }
    }

    for (int i = 1; i < board.length - 1; i++) {
      if (board[i][0] == 'O' && chars[i][0] != 'O') {
        tag(board, chars, 0, i);
      }
    }

    if (wight > 1) {
      for (int i = 1; i < board.length - 1; i++) {
        if (board[i][wight - 1] == 'O' && chars[i][wight - 1] != 'O') {
          tag(board, chars, wight - 1, i);
        }
      }
    }

    for (int i = 0; i < chars.length; i++) {
      for (int j = 0; j < chars[i].length; j++) {
        if (chars[i][j] != 'O') {
          board[i][j] = 'X';
        } else {
          board[i][j] = 'O';
        }
      }
    }


  }

  public void tag(char[][] board, char[][] tag, int x, int y) {
    if (y < 0 || y >= board.length || x < 0 || x >= board[0].length) {
      return;
    }
    if (board[y][x] == 'O' && tag[y][x] != 'O') {
      tag[y][x] = 'O';
      tag(board, tag, x + 1, y);
      tag(board, tag, x - 1, y);
      tag(board, tag, x, y + 1);
      tag(board, tag, x, y - 1);
    }
  }

}
