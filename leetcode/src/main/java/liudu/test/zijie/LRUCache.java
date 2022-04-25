package liudu.test.zijie;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

  int capacity;
  int length;
  Map<Integer, Node> map;
  Node head;
  Node tail;

  public LRUCache(int capacity) {
    this.map = new HashMap<>();
    this.capacity = capacity;
    this.head = new Node();
    this.tail = new Node();
    this.head.tail = this.tail;
    this.tail.head = this.head;
  }

  public int get(int key) {
    if (map.containsKey(key)) {
      moveNodeHead(map.get(key));
      return map.get(key).value;
    }
    return -1;
  }

  public void put(int key, int value) {

    if (map.containsKey(key)) {
      Node node = map.get(key);
      node.value = value;
      moveNodeHead(node);
    } else {
      length++;
      Node node = new Node(key, value);
      map.put(key, node);
      moveNewNodeHead(node);
    }
    if (length > capacity) {
      Node node = removeTail();
      map.remove(node.key);
    }

  }

  private void moveNewNodeHead(Node node) {
    node.tail = head.tail;
    node.head = head;
    head.tail.head = node;
    head.tail = node;
  }

  private void moveNodeHead(Node node) {
    node.tail.head = node.head;
    node.head.tail = node.tail;
    moveNewNodeHead(node);
  }

  private Node removeTail() {
    Node head = tail.head;
    tail.head.head.tail = tail;
    tail.head = tail.head.head;
    length--;
    return head;
  }


}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

class Node {

  Integer key;
  Integer value;
  Node head;
  Node tail;

  public Node() {
  }

  public Node(Integer key, Integer value) {
    this.key = key;
    this.value = value;
  }
}