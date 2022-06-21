package liudu.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Number140 {

  public static void main(String[] args) {
    ArrayList<String> strings = new ArrayList<>();
    strings.add("leet");
    strings.add("code");
    System.out.println(new Number140().wordBreak("leetcode", strings));

  }

  public List<String> wordBreak(String s, List<String> wordDict) {
    ArrayList<String> tmpList = new ArrayList<>();
    ArrayList<String> resultList = new ArrayList<>();
    HashSet<String> strings = new HashSet<>(wordDict);
    wordBreak(s, strings, 0, tmpList, resultList);

    return resultList;
  }

  public void wordBreak(String s, Set<String> wordDict, int index,
      List<String> tmpList, List<String> resultList) {
    if (index == s.length() ) {
      resultList.add(String.join(" ", tmpList));
    }

    for (int i = index + 1; i <= s.length(); i++) {
      String substring = s.substring(index, i);
      if (wordDict.contains(substring)) {
        tmpList.add(substring);
        wordBreak(s, wordDict, i, tmpList, resultList);
        tmpList.remove(tmpList.size() - 1);
      }
    }

  }


}
