package liudu.leetcode;

import java.util.Arrays;

public class Number88 {

  public static void main(String[] args) {
//    int[] nums1 = {2, 0};
    int[] nums1 = {4, 0, 0, 0, 0, 0};
//    int[] nums2 = {1};
    int[] nums2 = {1, 2, 3, 5, 6};
    new Number88().merge(
        nums1,
        nums1.length - nums2.length,
        nums2,
        nums2.length
    );

    System.out.println(Arrays.toString(nums1));
  }

  public void merge(int[] nums1, int m, int[] nums2, int n) {
    if (n == 0) {
      return;
    }
    if (m == 0) {
      System.arraycopy(nums2, 0, nums1, 0, nums1.length);
      return;
    }
    int[] ints = new int[m + n];
    int index1 = 0;
    int index2 = 0;

    for (int i = 0; i < ints.length; i++) {
      if (index2 < n && index1 < m && nums1[index1] < nums2[index2]) {
        ints[i] = nums1[index1];
        index1++;
      } else if (index2 >= n) {
        ints[i] = nums1[index1];
        index1++;
      } else if (index1 >= m) {
        ints[i] = nums2[index2];
        index2++;
      } else {
        ints[i] = nums2[index2];
        index2++;
      }
    }

    System.arraycopy(ints, 0, nums1, 0, nums1.length);


  }

}
