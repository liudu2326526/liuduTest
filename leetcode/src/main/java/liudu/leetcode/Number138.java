package liudu.leetcode;

import java.util.HashMap;

/**
 * 138. 复制带随机指针的链表
 */
public class Number138 {

  public RandomNode copyRandomList(RandomNode head) {
    HashMap<RandomNode, RandomNode> map = new HashMap<>();
    RandomNode index = head;
    while (index != null) {
      RandomNode randomNode = new RandomNode(index.val);
      map.put(index, randomNode);
      index = index.next;
    }

    index = head;
    while (index != null) {
      RandomNode randomNode = map.get(index);
      if (index.next != null) {
        randomNode.next = map.get(index.next);
      }
      if (index.random != null) {
        randomNode.random = map.get(index.random);
      }
      index = index.next;
    }

    return map.get(head);
  }

}
