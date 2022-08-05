package liudu.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liudu
 * @title: Number93
 * @projectName liuduTest
 * @description: 93. 复原 IP 地址 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * <p>
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效
 * IP 地址。 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。 你 不能 重新排序或删除 s
 * 中的任何数字。你可以按 任何 顺序返回答案。
 * @date 2022/8/5下午2:40
 */
public class Number93 {

  public static void main(String[] args) {
    System.out.println(new Number93().restoreIpAddresses("25525511135"));
  }

  public List<String> restoreIpAddresses(String s) {
    ArrayList<String> result = new ArrayList<>();
    ArrayList<String> tmp = new ArrayList<>();
    restoreIpAddresses(s, tmp, result, 0, 0);

    return result;
  }

  public boolean parseStr(String s, int begin, int end) {
    if (begin == end || end > s.length()) {
      return false;
    }
    String substring = s.substring(begin, end);
    int i = Integer.parseInt(substring);
    return i >= 0 && i < 256 && substring.equals(i + "");
  }

  public void restoreIpAddresses(String s, List<String> tmp, List<String> result, int begin,
      int end) {
    if (parseStr(s, begin, end)) {
      tmp.add(s.substring(begin, end));
    } else if (begin != end) {
      return;
    }

    if ((tmp.size() == 4 && end < s.length()) || end > s.length()) {
      tmp.remove(tmp.size() - 1);
      return;
    }
    if (tmp.size() == 4) {
      result.add(String.join(".", tmp));
      tmp.remove(tmp.size() - 1);
      return;
    }

    for (int i = 1; i < 4; i++) {
      restoreIpAddresses(s, tmp, result, end, end + i);
    }
    if (tmp.size() > 0) {
      tmp.remove(tmp.size() - 1);
    }
  }

}
