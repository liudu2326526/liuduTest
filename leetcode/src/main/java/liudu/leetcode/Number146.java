package liudu.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Number146 {

  public static void main(String[] args) {
//    LRUCache lruCache = new LRUCache(2);
//    lruCache.put(1,1);
//    lruCache.put(2,2);
//    System.out.println(lruCache.get(1));
//    lruCache.put(3,3);
//    System.out.println(lruCache.get(2));
//    lruCache.put(4,4);
//    System.out.println(lruCache.get(1));
//    System.out.println(lruCache.get(3));
//    System.out.println(lruCache.get(4));

    LRUCache lruCache = new LRUCache(2);
    lruCache.put(2, 1);
    lruCache.put(1, 1);
    lruCache.put(2, 3);
    lruCache.put(4, 1);
    System.out.println(lruCache.get(1));
    System.out.println(lruCache.get(2));

  }

}

class DLinkedNode {

  int key;
  int value;
  DLinkedNode prev;
  DLinkedNode next;

  public DLinkedNode() {
  }

  public DLinkedNode(int _key, int _value) {
    key = _key;
    value = _value;
  }

  public void addNext(DLinkedNode node) {
    DLinkedNode tmp = this.next;
    node.next = tmp;
    this.next = node;
    tmp.prev = node;
    node.prev = this;
  }

  public int removePrev() {
    DLinkedNode tmp = this.prev;
    this.prev = tmp.prev;
    tmp.prev.next = this;
    return tmp.key;
  }


}

class LRUCache {

  Map<Integer, DLinkedNode> map;
  int capacity;
  DLinkedNode head;
  DLinkedNode tail;

  public LRUCache(int capacity) {
    this.map = new HashMap<>();
    this.capacity = capacity;
    this.head = new DLinkedNode();
    this.tail = new DLinkedNode();
    this.head.next = this.tail;
    this.tail.prev = this.head;

  }

  public int get(int key) {
    if (!map.containsKey(key)) {
      return -1;
    } else {
      DLinkedNode dLinkedNode = map.get(key);
      dLinkedNode.prev.next = dLinkedNode.next;
      dLinkedNode.next.prev = dLinkedNode.prev;
      head.addNext(dLinkedNode);
      return dLinkedNode.value;
    }
  }

  public void put(int key, int value) {
    DLinkedNode dLinkedNode;
    if (map.containsKey(key)) {
      dLinkedNode = map.get(key);
      dLinkedNode.value = value;
      dLinkedNode.prev.next = dLinkedNode.next;
      dLinkedNode.next.prev = dLinkedNode.prev;
    } else {
      dLinkedNode = new DLinkedNode();
      dLinkedNode.key = key;
      dLinkedNode.value = value;
    }

    head.addNext(dLinkedNode);
    map.put(key, dLinkedNode);
    if (map.size() > capacity) {
      int i = tail.removePrev();
      map.remove(i);
    }

  }

}