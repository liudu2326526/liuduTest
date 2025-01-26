package liudu.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import liudu.structure.Trie;

public class Number212 {

  public static void main(String[] args) {
    System.out.println("saafa" + 's');
    Number212 number212 = new Number212();
//    char[][] board = {
//        {'o', 'a', 'a', 'n'},
//        {'e', 't', 'a', 'e'},
//        {'i', 'h', 'k', 'r'},
//        {'i', 'f', 'l', 'v'}
//    };

    char[][] board = {
        {'a', 'b', 'c'},
        {'a', 'e', 'd'},
        {'a', 'f', 'g'}
    };

//    String[] words = {"oath", "pea", "eat", "rain"};
    String[] words = {"abcdefg", "gfedcbaaa", "eaabcdgfa", "befa", "dgc", "ade"};

    System.out.println(number212.findWords(board, words));

  }


  public List<String> findWords(char[][] board, String[] words) {
    HashSet<String> result = new HashSet<>();
//    int[] index = new int[words.length];
    Trie trie = new Trie();
    for (String word : words) {
      trie.insert(word);
    }
    int num = 0;

    boolean[][] isVisit = new boolean[board.length][board[0].length];

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        findWords(board, isVisit, i, j, trie, "", result);
        if (num<result.size()){
          num = result.size();
          trie = new Trie();
          for (String word : words) {
            if (!result.contains(word)){
              trie.insert(word);
            }

          }
        }

      }
    }

    return new ArrayList<>(result);
  }


  public void findWords(char[][] board, boolean[][] isVisit, int m, int n, Trie trie, String tmp,
      HashSet<String> result) {
//    判断边界 或者是 曾经遍历过的
    if (m < 0 || n < 0 || m >= board.length || n >= board[0].length || isVisit[m][n]) {
      return;
    }
//    标识已遍历
    isVisit[m][n] = true;
    char c = board[m][n];
    tmp = tmp + c;
//    如果是下个字符
    if (trie.startsWith(tmp)) {
      if (trie.search(tmp)) {
        result.add(tmp);
      }
      findWords(board, isVisit, m + 1, n, trie, tmp, result);
      findWords(board, isVisit, m - 1, n, trie, tmp, result);
      findWords(board, isVisit, m, n + 1, trie, tmp, result);
      findWords(board, isVisit, m, n - 1, trie, tmp, result);
    }

    isVisit[m][n] = false;

  }

//  public void findWords2(char[][] board, boolean[][] isVisit, int m, int n, int[] index,
//      HashSet<String> result, List<String> words) {
////    判断边界 或者是 曾经遍历过的
//    if (m < 0 || n < 0 || m >= board.length || n >= board[0].length || isVisit[m][n]) {
//      return;
//    }
////    标识已遍历
//    isVisit[m][n] = true;
//    char c = board[m][n];
////    继续遍历字符串
//    boolean flag = false;
//    for (int i = 0; i < words.size(); i++) {
//
//      if (index[i] >= 0 && index[i] < words.get(i).length() && c == words.get(i).charAt(index[i])) {
//        flag = true;
//        index[i]++;
//        if (index[i] == words.get(i).length()) {
//          result.add(words.get(i));
//        }
//      } else {
//        index[i] = -1;
//      }
//
//    }
//
////    如果可以继续遍历
//    if (flag) {
//      findWords(board, isVisit, m + 1, n, Arrays.copyOf(index, index.length), result, words);
//      findWords(board, isVisit, m - 1, n, Arrays.copyOf(index, index.length), result, words);
//      findWords(board, isVisit, m, n + 1, Arrays.copyOf(index, index.length), result, words);
//      findWords(board, isVisit, m, n - 1, Arrays.copyOf(index, index.length), result, words);
//    }
//
//    isVisit[m][n] = false;
//
//  }

}
