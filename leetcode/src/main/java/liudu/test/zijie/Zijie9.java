package liudu.test.zijie;

public class Zijie9 {

  public boolean isValidSudoku(char[][] board) {
    int[][] column = new int[9][9];
    int[][] row = new int[9][9];
    int[][][] ints = new int[3][3][9];

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        char target = board[i][j];
        if (target != '.') {
          int index = target - '1';
          row[i][index]++;
          column[j][index]++;
          ints[i / 3][j / 3][index]++;
          if (row[i][index] > 1 || column[j][index] > 1 || ints[i / 3][j / 3][index] > 1) {
            return false;
          }
        }


      }
    }

    return true;

  }

}
