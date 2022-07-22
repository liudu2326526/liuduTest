package liudu.leetcode;

/**
 * @author liudu
 * @title: Number42
 * @projectName liuduTest
 * @description: 42. 接雨水 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * @date 2022/7/22下午2:17
 */
public class Number42 {

  public int trap(int[] height) {
    int n = height.length;
    int[] left = new int[n];
    int[] right = new int[n];

    left[0] = height[0];
    right[n - 1] = height[n - 1];

    for (int i = 1; i < n; i++) {
      left[i] = Math.max(left[i - 1], height[i]);
    }

    for (int i = n - 2; i >= 0; i--) {
      right[i] = Math.max(right[i + 1], height[i]);
    }

    int sum = 0;

    for (int i = 0; i < n - 1; i++) {
      sum += Math.min(left[i], right[i]) - height[i];
    }

    return sum;
  }

  public int trap1(int[] height) {
    int n = height.length;
    int left = 0;
    int right = n - 1;
    int leftMax = height[left];
    int rightMax = height[right];

    int sum = 0;

    while (left <= right) {
      if (leftMax > rightMax) {
        sum += rightMax - height[right];
        right--;
        rightMax = Math.max(rightMax, height[right]);
      } else {
        sum += leftMax - height[left];
        left++;
        if (left > n - 1) {
          break;
        }
        leftMax = Math.max(leftMax, height[left]);
      }
    }

    return sum;
  }

}
