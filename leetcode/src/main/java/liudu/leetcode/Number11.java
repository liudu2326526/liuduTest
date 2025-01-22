package liudu.leetcode;

/**
 * @author Liu Du
 * @Date 2024/11/8
 */
public class Number11 {


  public int maxArea(int[] height) {
    int[] left = new int[height.length];
    int[] right = new int[height.length];
    int max = 0;
    for (int i = 0; i < height.length; i++) {
      if (max < height[i]) {
        left[i] = height[i];
        max = height[i];
      } else {
        left[i] = max;
      }
    }
    max = 0;
    for (int i = height.length - 1; i >= 0; i--) {
      if (max < height[i]) {
        right[i] = height[i];
        max = height[i];
      } else {
        right[i] = max;
      }
    }

    int l = 0;
    int r = height.length -1;



    return 0;
  }

  public int maxArea1(int[] height) {
    int max = 0;
    int tmp = 0;
    for (int i = 0; i < height.length; i++) {
      if (tmp < height[i]) {
        for (int j = i + 1; j < height.length; j++) {
          max = Math.max((j - i) * Math.min(height[i], height[j]), max);
        }
        tmp = height[i];
      }
    }

    return max;
  }

}
