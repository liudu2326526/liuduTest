package liudu.niuke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main2 {

  public static void main(String[] args) throws IOException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    String s1 = bf.readLine();
    String s2 = bf.readLine();
    String[] s = s2.split(" ");
    int[] ints = stringToInts(s);
    HashSet<Integer> targets = new HashSet<>();
    HashSet<Integer> result = new HashSet<>();

    for (int i = 0; i < ints.length; i++) {
      targets.add(ints[i]);
    }
    if (targets.contains(1)) {
      System.out.println(1);
    } else {
      for (int i = 2; i <= 100; i++) {
        HashSet<Integer> tmp = (HashSet<Integer>) targets.clone();
        for (Integer target : targets) {
          if (target % i == 0) {
            tmp.remove(target);
            result.add(i);
          }
        }
        targets = tmp;
      }

      System.out.println(result.size());

    }


  }

  public static int[] stringToInts(String[] s) {
    int[] ints = new int[s.length];
    for (int i = 0; i < ints.length; i++) {
      ints[i] = Integer.parseInt(s[i]);
    }
    return ints;
  }

  public static void minNumber(int i1, int i2, Set<Integer> set) {
    int min = Math.min(i1, i2);
    if (min == 1) {
      set.add(1);
    } else {
      for (int i = 2; i <= min; i++) {
        if (i1 % i == 0 && i2 % i == 0) {
          set.add(i);
          return;
        }
        if (i == min) {
          set.add(i1);
          set.add(i2);
        }
      }
    }
  }


}