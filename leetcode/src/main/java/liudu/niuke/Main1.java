package liudu.niuke;

import java.io.IOException;

public class Main1 {

  public static void main(String[] args) throws IOException {
//    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//    String s1 = bf.readLine();
//    String s2 = bf.readLine();
//    String s3 = bf.readLine();
//    int[] ints1 = stringToInts(s1.split(" "));
//    int[] ints2 = stringToInts(s2.split(" "));
//    int[] ints3 = stringToInts(s3.split(" "));
    int[] ints1 = new int[]{1, 6, 4, 4};
    int[] ints2 = new int[]{3, 5, 3, 4};
    int[] ints3 = new int[]{0, 3, 7, 3};

    int[] sameArea = getSameArea(ints1, ints2);
    if (sameArea == null) {
      System.out.println(0);
    } else {
      int[] sameArea1 = getSameArea(sameArea, ints3);
      if (sameArea1 == null) {
        System.out.println(0);
      } else {
        System.out.println(sameArea1[2] * sameArea1[3]);
      }
    }


  }

  public static int[] stringToInts(String[] s) {
    int[] ints = new int[s.length];
    for (int i = 0; i < ints.length; i++) {
      ints[i] = Integer.parseInt(s[i]);
    }
    return ints;
  }

  public static int[] getSameArea(int[] i1, int[] i2) {
    //把 i1 放在左边
    int[] result = new int[4];
    if (i1[0] > i2[0]) {
      int[] tmp = i1;
      i1 = i2;
      i2 = tmp;
    }
    // 左右没交集的情况
    if (i1[0] + i1[2] <= i2[0]) {
      return null;
    } else {
      result[0] = i2[0];
      result[2] = Math.min(i2[2], i1[0] + i1[2] - i2[0]);
    }

    //把 i1 放在上面
    if (i1[1] < i2[1]) {
      int[] tmp = i1;
      i1 = i2;
      i2 = tmp;
    }

    // 上下没交集的情况
    if (i1[1] - i1[3] >= i2[1]) {
      return null;
    } else {
      result[1] = i2[1];
      result[3] = Math.min(i2[3], i2[1] - i1[1] + i1[3]);
    }
    return result;
  }

}