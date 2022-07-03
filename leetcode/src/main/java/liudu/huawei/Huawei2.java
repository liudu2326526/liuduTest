package liudu.huawei;

import java.util.HashSet;

/**
 * @author liudu
 * @title: Huawei2
 * @projectName liuduTest
 * @date 2022/6/23下午3:22
 */
public class Huawei2 {

  //[["5","3",".",".","7",".",".",".","."]
//    ,["6",".",".","1","9","5",".",".","."]
//    ,[".","9","8",".",".",".",".","6","."]
//    ,["8",".",".",".","6",".",".",".","3"]
//    ,["4",".",".","8",".","3",".",".","1"]
//    ,["7",".",".",".","2",".",".",".","6"]
//    ,[".","6",".",".",".",".","2","8","."]
//    ,[".",".",".","4","1","9",".",".","5"]
//    ,[".",".",".",".","8",".",".","7","9"]]
  public static void main(String[] args) {
//    String[][] strings = {
//        {"5", "3", ".", ".", "7", ".", ".", ".", "."},
//        {"6", ".", ".", "1", "9", "5", ".", ".", "."},
//        {".", "9", "8", ".", ".", ".", ".", "6", "."},
//        {"8", ".", ".", ".", "6", ".", ".", ".", "3"},
//        {"4", ".", ".", "8", ".", "3", ".", ".", "1"},
//        {"7", ".", ".", ".", "2", ".", ".", ".", "6"},
//        {".", "6", ".", ".", ".", ".", "2", "8", "."},
//        {".", ".", ".", "4", "1", "9", ".", ".", "5"},
//        {".", ".", ".", ".", "8", ".", ".", "7", "9"},
//    };
    String[][] strings = {
        {"5", "3", ".", ".", "7", ".", ".", ".", "."},
        {"6", ".", ".", "1", "9", "5", ".", ".", "."},
        {".", "9", "8", ".", ".", ".", ".", "6", "."},
        {"8", ".", ".", ".", "6", ".", ".", ".", "3"},
        {"4", ".", ".", "8", ".", "3", ".", ".", "1"},
        {"7", ".", ".", ".", "2", ".", ".", ".", "6"},
        {".", "6", ".", ".", ".", ".", "2", "8", "."},
        {".", ".", ".", "4", "1", "9", ".", ".", "5"},
        {".", ".", ".", ".", "8", ".", ".", "7", "9"},
    };
    System.out.println(new Huawei2().verify(strings));
  }

  public boolean verify(String[][] board) {
    for (int i = 0; i < 9; i++) {
      HashSet<String> set = new HashSet<>();
      for (int j = 0; j < 9; j++) {
        if (".".equals(board[i][j])) {
          continue;
        }
        if (set.contains(board[i][j])) {
          return false;
        }
        set.add(board[i][j]);
      }
    }

    for (int i = 0; i < 9; i++) {
      HashSet<String> set = new HashSet<>();
      for (int j = 0; j < 9; j++) {
        if (".".equals(board[j][i])) {
          continue;
        }
        if (set.contains(board[j][i])) {
          return false;
        }
        set.add(board[j][i]);
      }
    }

    for (int i = 0; i < 9; i = i + 3) {
      for (int j = 0; j < 9; j = j + 3) {
        if (!verify(board, i, j)) {
          return false;
        }
      }
    }

    return true;
  }

  public boolean verify(String[][] board, int i, int j) {
    HashSet<String> set = new HashSet<>();
    for (int x = i; x < i + 3; x++) {
      for (int y = j; y < j + 3; y++) {
        if (".".equals(board[x][y])) {
          continue;
        }
        if (set.contains(board[x][y])) {
          return false;
        }
        set.add(board[x][y]);
      }
    }
    return true;
  }

}
