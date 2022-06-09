package liudu.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author liudu
 * @title: Number17
 * @projectName liuduTest
 * @description: TODO
 * @date 2022/6/9下午7:10
 */
public class Number17 {

  static HashMap<Character, String> map = new HashMap<>();

  static {
    map.put('2', "abc");
    map.put('3', "def");
    map.put('4', "ghi");
    map.put('5', "jkl");
    map.put('6', "mno");
    map.put('7', "pqrs");
    map.put('8', "tuv");
    map.put('9', "wxyz");
  }


  public List<String> letterCombinations(String digits) {
    ArrayList<String> strings = new ArrayList<>();
    if (digits.length() == 0){
      return strings;
    }
    char[] chars = new char[digits.length()];

    letterCombinations(digits,0,chars,strings);

    return strings;
  }

  public void letterCombinations(String digits, int index, char[] charArray, List<String> result) {
    if (index == digits.length()) {
      result.add(String.copyValueOf(charArray));
    } else {
      String s = map.get(digits.charAt(index));
      for (char c : s.toCharArray()) {
        charArray[index] = c;
        letterCombinations(digits, index + 1, charArray, result);
      }
    }
  }


}
