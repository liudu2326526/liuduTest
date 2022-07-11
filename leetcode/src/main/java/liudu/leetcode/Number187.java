package liudu.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * @author liudu
 * @title: Number184
 * @projectName liuduTest
 * @description: DNA序列 由一系列核苷酸组成，缩写为 'A', 'C', 'G' 和 'T'.。
 * <p>
 * 例如，"ACGAATTCCG" 是一个 DNA序列 。 在研究 DNA 时，识别 DNA 中的重复序列非常有用。
 * <p>
 * 给定一个表示 DNA序列 的字符串 s ，返回所有在 DNA 分子中出现不止一次的 长度为 10 的序列(子字符串)。你可以按 任意顺序 返回答案。
 * @date 2022/7/6下午3:27
 */
public class Number187 {

  public static void main(String[] args) {
    System.out
        .println(new Number187().findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
  }

  public List<String> findRepeatedDnaSequences(String s) {
    HashMap<String, Integer> map = new HashMap<>();
    StringBuilder sb = new StringBuilder();
    char[] chars = s.toCharArray();
    ArrayList<String> result = new ArrayList<>();
    if (chars.length < 10) {
      return result;
    }
    sb.append(s, 0, 10);
    map.put(sb.toString(), 1);
    for (int i = 10; i < chars.length; i++) {
      sb.deleteCharAt(0);
      sb.append(chars[i]);
      String s1 = sb.toString();
      if (map.containsKey(s1)) {
        map.put(s1, map.get(s1) + 1);
      } else {
        map.put(s1, 1);
      }
    }

    for (Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
      if (stringIntegerEntry.getValue() > 1) {
        result.add(stringIntegerEntry.getKey());
      }
    }

    return result;
  }

}
