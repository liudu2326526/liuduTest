package liudu.leetcode;

public class Number718 {

  public static void main(String[] args) {
    Number718 number718 = new Number718();
    int[] nums1 = {0, 0, 0, 0, 0};
    int[] nums2 = {0, 0, 0, 0, 0};
    System.out.println(number718.findLength(nums1, nums2));

  }

  public int findLength(int[] nums1, int[] nums2) {
//动态规划法
    int res = 0;

    int[][] dp = new int[nums1.length][nums2.length];

    for (int i = 0; i < dp.length; i++) {
      if (nums1[i] == nums2[nums2.length - 1]) {
        dp[i][nums2.length - 1] = 1;
        res = 1;
      }
    }

    for (int i = 0; i < dp[0].length; i++) {
      if (nums2[i] == nums1[nums1.length - 1]) {
        dp[nums1.length - 1][i] = 1;
        res = 1;
      }
    }

    for (int i = dp.length - 2; i >= 0; i--) {
      for (int j = dp[i].length - 2; j >= 0; j--) {
        if (nums1[i] == nums2[j]) {
          dp[i][j] = dp[i + 1][j + 1] + 1;
          res = Math.max(res, dp[i][j]);
        }
      }
    }

    return res;
  }

  public int findLength1(int[] nums1, int[] nums2) {
    return Math.max(findLength1(nums1, nums2, false), findLength1(nums1, nums2, true));
  }

  public int findLength1(int[] nums1, int[] nums2, boolean flag) {
//滑动窗口法
    int res = 0;
    if (flag) {
      int[] tmp = nums1;
      nums1 = nums2;
      nums2 = tmp;
    }

    int num = 0;
    for (int i = 0; i < nums1.length; i++) {

      for (int j = 0; j < Math.min(nums2.length, nums1.length - i); j++) {
        if (nums2[j] == nums1[i + j]) {
          num++;
        } else {
          res = Math.max(res, num);
          num = 0;
        }
      }
      res = Math.max(res, num);
      num = 0;
    }
    return res;
  }

}
