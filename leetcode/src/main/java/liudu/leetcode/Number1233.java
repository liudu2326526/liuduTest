package liudu.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Liu Du
 * @Date 2025/7/21
 */
public class Number1233 {

  public static void main(String[] args) {
    Number1233 number1233 = new Number1233();
    String[] strings = {"/a", "/a/b", "/c/d", "/c/d/e", "/c/f"};
    System.out.println(number1233.removeSubfolders(strings));
  }

//  前缀树
  public List<String> removeSubfolders(String[] folder) {
    PreTreeNode root = new PreTreeNode("");
    for (int i = 0; i < folder.length; i++) {
      String[] split = folder[i].split("/");
      PreTreeNode tmp = root;
      for (int j = 0; j < split.length; j++) {
        if (!tmp.next.containsKey(split[j])) {
          PreTreeNode node = new PreTreeNode(split[j]);
          tmp.next.put(split[j], node);
        }
        tmp = tmp.next.get(split[j]);
        if (j == split.length - 1) {
          tmp.isFolder = true;
        }
      }
    }

    ArrayList<String> result = new ArrayList<>();
    ArrayList<String> strings = new ArrayList<>();

    format(root.next.get(""), strings, result);

    return result;
  }

  public void format(PreTreeNode node, List<String> strings, List<String> result) {
    strings.add(node.key);
    if (node.isFolder) {
      String join = String.join("/", strings);
      result.add(join);
    } else {
      for (PreTreeNode value : node.next.values()) {
        format(value, strings, result);
      }
    }
    strings.remove(strings.size() - 1);
  }

  static class PreTreeNode {

    HashMap<String, PreTreeNode> next;
    boolean isFolder;
    String key;

    public PreTreeNode(String key) {
      this.key = key;
      this.next = new HashMap<>();
      this.isFolder = false;
    }

  }

}

