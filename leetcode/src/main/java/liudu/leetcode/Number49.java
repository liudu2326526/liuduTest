package liudu.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Number49 {

  public List<List<String>> groupAnagrams(String[] strs) {

    HashMap<Map<Character, Integer>, List<String>> map = new HashMap<>();

    for (String str : strs) {
      char[] chars = str.toCharArray();
      HashMap<Character, Integer> map1 = new HashMap<>();
      for (char aChar : chars) {
        if (map1.containsKey(aChar)) {
          map1.put(aChar, map1.get(aChar) + 1);
        } else {
          map1.put(aChar, 1);
        }
      }
      if (map.containsKey(map1)) {
        map.get(map1).add(str);
      } else {
        ArrayList<String> strings = new ArrayList<>();
        strings.add(str);
        map.put(map1, strings);
      }
    }

    return new ArrayList<>(map.values());
  }

}
