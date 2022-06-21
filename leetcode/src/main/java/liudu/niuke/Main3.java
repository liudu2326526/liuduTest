package liudu.niuke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Main3 {

  public static void main(String[] args) throws IOException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    String s1 = bf.readLine();
    String s2 = bf.readLine();

    HashMap<String, StringListNode> map = new HashMap<>();

    String[] split1 = s1.split(",");
    for (String s : split1) {
      String[] sp = s.split("-");
      if (!map.containsKey(sp[0])) {
        map.put(sp[0], new StringListNode(sp[0]));
      }
      if (!map.containsKey(sp[1])) {
        map.put(sp[1], new StringListNode(sp[1]));
      }
      map.get(sp[1]).next = map.get(sp[0]);
    }

    String[] split2 = s2.split(",");
    for (String s : split2) {
      if (map.containsKey(s)) {
        StringListNode node = map.get(s);
        HashSet<String> strings = new HashSet<>();
        while (node != null && !strings.contains(node.val)) {
          strings.add(node.val);
          map.remove(node.val);
          node = node.next;
        }
      }
    }

    if (map.size() == 0) {
      System.out.println(",");
    } else {
      Set<String> strings = map.keySet();
      StringBuilder sb = new StringBuilder();
      for (String s : split1) {
        String[] sp = s.split("-");
        if (strings.contains(sp[0])){
          sb.append(sp[0]);
          sb.append(",");
        }
        if (strings.contains(sp[1])){
          sb.append(sp[1]);
          sb.append(",");
        }
      }
      sb.deleteCharAt(sb.length() - 1);
      System.out.println(sb);
    }

  }

  public static int[] stringToInts(String[] s) {
    int[] ints = new int[s.length];
    for (int i = 0; i < ints.length; i++) {
      ints[i] = Integer.parseInt(s[i]);
    }
    return ints;
  }


}

class StringListNode {

  String val;
  StringListNode next;

  StringListNode() {
  }

  StringListNode(String val) {
    this.val = val;
  }

  StringListNode(String val, StringListNode next) {
    this.val = val;
    this.next = next;
  }
}