package liudu.test;

import java.util.HashSet;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class ShenXinFu {

  public static void main(String[] args) throws InterruptedException {
    System.out.println(new ShenXinFu().longgestSubStr("abcssdas"));
  }

  public int longgestSubStr(String str) throws InterruptedException {
    HashSet<Character> set = new HashSet<>();
    LinkedBlockingQueue<Character> queue = new LinkedBlockingQueue<>();
    int max = 0;
    char[] chars = str.toCharArray();

    for (char aChar : chars) {
      while (set.contains(aChar)) {
        set.remove(queue.poll());
      }
      set.add(aChar);
      queue.put(aChar);
      max = Math.max(max,queue.size());
    }

    return max;

  }
}


