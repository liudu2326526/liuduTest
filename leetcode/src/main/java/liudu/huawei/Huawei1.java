package liudu.huawei;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author liudu
 * @title: Huawei1
 * @projectName liuduTest
 * @date 2022/6/14下午3:13
 */
public class Huawei1 {

  public static void main(String[] args) {
    System.out.println(new Huawei1().solution("bbbb"));
  }

  public int solution(String s) {
    int max = 0;
    HashSet<Character> set = new HashSet<>();
    LinkedBlockingQueue<Character> queue = new LinkedBlockingQueue<>();


    char[] chars = s.toCharArray();
    for (char aChar : chars) {
      if (set.contains(aChar)) {
        while (queue.size() > 0 && queue.peek() != aChar) {
          set.remove(queue.poll());
        }
        set.remove(queue.poll());
      }
      set.add(aChar);
      queue.add(aChar);
      max = Math.max(queue.size(), max);
    }

    return max;
  }

}
