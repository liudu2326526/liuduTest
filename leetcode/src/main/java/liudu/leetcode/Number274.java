package liudu.leetcode;

import java.util.Arrays;

public class Number274 {

  public int hIndex(int[] citations) {

    Arrays.sort(citations);

    int num = citations.length;

    for (int citation : citations) {
      if (citation >= num){
        return Math.min(citation,num);
      } else {
        num--;
      }
    }


    return 0;
  }


}
