package liudu.leetcode;

import java.util.Arrays;

public class Number164 {

  public static void main(String[] args) {
    int[] ints = {13, 36,39, 29, 51};
    System.out.println(new Number164().maximumGap(ints));
  }


  public int maximumGap(int[] nums) {
    int n = nums.length;
    if (n < 2) {
      return 0;
    }
    long exp = 1;
    int[] buf = new int[n];
    int maxVal = Arrays.stream(nums).max().getAsInt();

    while (maxVal >= exp) {
      int[] cnt = new int[10];
      for (int i = 0; i < n; i++) {
        int digit = (nums[i] / (int) exp) % 10;
        cnt[digit]++;
      }
      for (int i = 1; i < 10; i++) {
        cnt[i] += cnt[i - 1];
      }
      for (int i = n - 1; i >= 0; i--) {
        int digit = (nums[i] / (int) exp) % 10;
        buf[cnt[digit] - 1] = nums[i];
        cnt[digit]--;
      }
      System.arraycopy(buf, 0, nums, 0, n);
      exp *= 10;
    }

    int ret = 0;
    for (int i = 1; i < n; i++) {
      ret = Math.max(ret, nums[i] - nums[i - 1]);
    }
    return ret;
  }

}
