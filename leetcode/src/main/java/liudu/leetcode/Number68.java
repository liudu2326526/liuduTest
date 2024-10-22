package liudu.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Number68 {

  public static void main(String[] args) {
    String[] strings = {"ask", "not", "what", "your", "country", "can", "do", "for", "you", "ask",
        "what", "you", "can", "do", "for", "your", "country"};
    Number68 number68 = new Number68();
    System.out.println(number68.fullJustify(strings, 16));

    System.out.println("acknowledgment   ".length());
  }

  public List<String> fullJustify(String[] words, int maxWidth) {
    int num = 0;
    ArrayList<String> strings = new ArrayList<>();
    ArrayList<String> result = new ArrayList<>();

    for (String word : words) {
      if (num + word.length() > maxWidth) {
        result.add(handelArray(strings, maxWidth, num));
        strings.clear();
        num = 0;
      }
      num += word.length() + 1;
      strings.add(word);
    }

    StringBuilder last = new StringBuilder();

    for (String string : strings) {
      last.append(string).append(" ");
    }
    if (num > maxWidth){
      last.deleteCharAt(last.length()-1);
    } else {
      last.append(" ".repeat(Math.max(0, maxWidth - num)));
    }


    result.add(last.toString());

    return result;
  }


  public String handelArray(List<String> strings, int maxWidth, int allLength) {
    allLength = allLength - strings.size();
    StringBuilder result = new StringBuilder();
    result.append(strings.get(0));
    if (strings.size() > 1) {
      int blankNum = maxWidth - allLength;
      int num = blankNum / (strings.size() - 1);
      int leftNum = blankNum % (strings.size() - 1);

      StringBuilder blank = new StringBuilder();
      blank.append(" ".repeat(Math.max(0, num)));
      for (int i = 1; i < strings.size(); i++) {
        result.append(blank);
        if (i <= leftNum) {
          result.append(" ");
        }
        result.append(strings.get(i));
      }
    } else {
      result.append(" ".repeat(maxWidth - allLength));
    }

    return result.toString();
  }
}
