package liudu.test.tenxun;

public class FindMedianSortedArrays {

  public static void main(String[] args) {
    int[] x = {1, 3};
    int[] y = {2};

    System.out.println(new SolutionFindMedianSortedArrays().findMedianSortedArrays(x, y));
  }

}


class SolutionFindMedianSortedArrays {

  public double findMedianSortedArrays(int[] nums1, int[] nums2) {

    if (nums1.length > nums2.length) {
      return findMedianSortedArrays(nums2, nums1);
    }

    int m = nums1.length;
    int n = nums2.length;

    int totalLeft = (m + n + 1) / 2;

    // 在 nums1 的区间 [0,m] 中查找恰当的分割线
    // 使得 nums1[i-1] <= nums2[j] && num[i] >= nums2[j-1]
    int left = 0;
    int right = m;

    while (left < right) {
      int i = (left + right + 1) / 2;
      int j = totalLeft - i;
      System.out.println(i);
      System.out.println(j);
      if (nums1[i - 1] > nums2[j]) {
        right = i - 1;
      } else {
        left = i;
      }
    }

    int i = left;
    int j = totalLeft - i;
    int nums1LeftMax = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
    int nums2LeftMax = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
    int nums1LeftMin = i == m ? Integer.MAX_VALUE : nums1[i];
    int nums2LeftMin = j == n ? Integer.MAX_VALUE : nums2[j];

    if (((m + n) % 2) == 1) {
      return Math.max(nums1LeftMax, nums2LeftMax);
    } else {
      return (Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1LeftMin, nums2LeftMin)) * 1.0
          / 2;
    }

  }

  public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
    if (nums1.length > nums2.length) {
      int[] temp = nums1;
      nums1 = nums2;
      nums2 = temp;
    }

    int m = nums1.length;
    int n = nums2.length;

    // 分割线左边的所有元素需要满足的个数 m + (n - m + 1) / 2; 2
    int totalLeft = (m + n + 1) / 2;

    // 在 nums1 的区间 [0, m] 里查找恰当的分割线，
    // 使得 nums1[i - 1] <= nums2[j] && nums2[j - 1] <= nums1[i]
    int left = 0;
    int right = m;

    while (left < right) {
      int i = left + (right - left + 1) / 2;
      int j = totalLeft - i;
      System.out.println(i);
      System.out.println(j);
      if (nums1[i - 1] > nums2[j]) {
        // 下一轮搜索的区间 [left, i - 1]
        right = i - 1;
      } else {
        // 下一轮搜索的区间 [i, right]
        left = i;
      }
    }

    int i = left;
    int j = totalLeft - i;
    int nums1LeftMax = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
    int nums1RightMin = i == m ? Integer.MAX_VALUE : nums1[i];
    int nums2LeftMax = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
    int nums2RightMin = j == n ? Integer.MAX_VALUE : nums2[j];

    if (((m + n) % 2) == 1) {
      return Math.max(nums1LeftMax, nums2LeftMax);
    } else {
      return
          (double) ((Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin)))
              / 2;
    }
  }

}