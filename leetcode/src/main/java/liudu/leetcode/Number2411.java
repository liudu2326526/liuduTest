package liudu.leetcode;

import java.util.Arrays;

/**
 * @author Liu Du
 * @Date 2025/7/29
 */
public class Number2411 {

  public static void main(String[] args) {
    Number2411 number2411 = new Number2411();
    int[] ints = {1, 0, 2, 1, 3};
    System.out.println(Arrays.toString(number2411.smallestSubarrays(ints)));
  }

  public int[] smallestSubarrays(int[] nums) {
    int n = nums.length;
    int[] ans = new int[n];
    ans[n - 1] = 1;
    if (n == 1) {
      return ans;
    }

    // 保证栈中至少有两个数，方便判断窗口右端点是否要缩小
    nums[n - 1] |= nums[n - 2];
    int leftOr = 0, right = n - 1, bottom = n - 2;
    for (int left = n - 2; left >= 0; left--) {
      leftOr |= nums[left];
      // 子数组 [left,right] 的或值 = 子数组 [left,right-1] 的或值，说明窗口右端点可以缩小
      while (right > left && (leftOr | nums[right]) == (leftOr | nums[right - 1])) {
        right--;
        // 栈中只剩一个数
        if (bottom >= right) {
          // 重新构建一个栈，栈底为 left，栈顶为 right
          // 通过更新“栈底”指针 + 栈重建操作，避免无意义扩展；
          for (int i = left + 1; i <= right; i++) {
            // 用 nums 数组本身作为缓存栈来存储 or 前缀值，避免重复计算；
            nums[i] |= nums[i - 1];
          }
          bottom = left;
          leftOr = 0;
        }
      }
      ans[left] = right - left + 1;
    }
    return ans;
  }

}
