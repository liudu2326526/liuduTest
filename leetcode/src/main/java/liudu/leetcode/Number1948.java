package liudu.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Liu Du
 * @Date 2025/7/21
 */
public class Number1948 {

  public static void main(String[] args) {

    Number1948 number1948 = new Number1948();

//    List<List<String>> list = List.of(
//        List.of("a"),
//        List.of("a", "x"),
//        List.of("a", "x", "y"),
//        List.of("a", "z"),
//        List.of("b"),
//        List.of("b", "x"),
//        List.of("b", "x", "y"),
//        List.of("b", "z"),
//        List.of("b", "w")
//    );
//    List<List<String>> list = List.of(
//        List.of("a"),
//        List.of("c"),
//        List.of("a", "b"),
//        List.of("c", "b"),
//        List.of("a", "b", "x"),
//        List.of("a", "b", "x", "y"),
//        List.of("w"),
//        List.of("w", "y")
//    );

    List<List<String>> list = List.of(
        List.of("a"),
        List.of("b"),
        List.of("a", "b"),
        List.of("a", "d"),
        List.of("b", "a"),
        List.of("b", "e")
    );
    System.out.println(number1948.deleteDuplicateFolder(list));
  }

  class Trie {

    String serial; // 当前节点结构的序列化表示
    Map<String, Trie> children = new HashMap<>(); // 当前节点的子节点
  }

  public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
    Trie root = new Trie(); // 根节点

    // 构建字典树
    for (List<String> path : paths) {
      Trie cur = root;
      for (String node : path) {
        cur.children.putIfAbsent(node, new Trie());
        cur = cur.children.get(node);
      }
    }

    Map<String, Integer> freq = new HashMap<>(); // 哈希表记录每一种序列化表示的出现次数
    // 基于深度优先搜索的后序遍历，计算每一个节点结构的序列化表示
    construct(root, freq);
    List<List<String>> ans = new ArrayList<>();
    List<String> path = new ArrayList<>();
    // 操作字典树，删除重复文件夹
    operate(root, freq, path, ans);
    return ans;
  }

  private void construct(Trie node, Map<String, Integer> freq) {
    if (node.children.isEmpty()) {
      return; // 如果是叶节点，无需操作
    }

    List<String> v = new ArrayList<>();
    for (Map.Entry<String, Trie> entry : node.children.entrySet()) {
      construct(entry.getValue(), freq);
      v.add(entry.getKey() + "(" + entry.getValue().serial + ")");
    }

    Collections.sort(v);
    StringBuilder sb = new StringBuilder();
    for (String s : v) {
      sb.append(s);
    }
    node.serial = sb.toString();
    freq.put(node.serial, freq.getOrDefault(node.serial, 0) + 1);
  }

  private void operate(Trie node, Map<String, Integer> freq, List<String> path,
      List<List<String>> ans) {
    if (freq.getOrDefault(node.serial, 0) > 1) {
      return; // 如果序列化表示出现超过1次，需要删除
    }

    if (!path.isEmpty()) {
      ans.add(new ArrayList<>(path));
    }

    for (Map.Entry<String, Trie> entry : node.children.entrySet()) {
      path.add(entry.getKey());
      operate(entry.getValue(), freq, path, ans);
      path.remove(path.size() - 1);
    }
  }


}

