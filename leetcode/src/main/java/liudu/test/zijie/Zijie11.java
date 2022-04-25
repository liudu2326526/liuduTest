package liudu.test.zijie;

public class Zijie11 {

  public static void main(String[] args) {
    int[] height = {1, 2, 4, 3};
    System.out.println(maxArea2(height));

  }


  /**
   * 暴力
   *
   * @param height
   * @return
   */
  public static int maxArea(int[] height) {
    int maxArea = 0;
    for (int i = 0; i < height.length - 1; i++) {
      for (int j = i + 1; j < height.length; j++) {
        int min = Math.min(height[i], height[j]);
        int length = j - i;
        maxArea = Math.max(maxArea, min * length);
      }
    }
    return maxArea;
  }

  public static int maxArea2(int[] height) {
    int l = 0;
    int r = height.length - 1;
    int maxArea = 0;
    while (l < r) {
      int tail = Math.min(height[l], height[r]);
      maxArea = Math.max(maxArea, (r - l) * tail);
      if (height[l] > height[r]) {
        r--;
      } else {
        l++;
      }
    }

    return maxArea;
  }
}
