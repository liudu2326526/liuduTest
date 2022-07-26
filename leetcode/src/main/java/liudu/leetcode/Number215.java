package liudu.leetcode;

import java.util.Random;

/**
 * @author liudu
 * @title: Nmuber215
 * @projectName liuduTest
 * @date 2022/7/25下午5:25
 */
public class Number215 {

  public static void main(String[] args) {
    int[] ints = {3, 2, 1, 5, 6, 4};
    new Number215().findKthLargest(ints, 2);
  }

  public int findKthLargest(int[] nums, int k) {
    return getRankNumber(nums, 0, nums.length - 1, nums.length - 1 - k);
  }

  private int getRankNumber(int[] nums, int l, int r, int k) {
    int partition = randomPartition(nums, l, r);
    if (partition == k) {
      return nums[partition];
    } else if (partition > k) {
      return getRankNumber(nums, l, partition - 1, k);
    } else {
      return getRankNumber(nums, partition + 1, r, k);
    }
  }

  private int randomPartition(int[] nums, int l, int r) {
    int random = new Random().nextInt(r - l + 1) + l;
    swap(nums, random, r);
    return partition(nums, l, r);
  }

  private int partition(int[] nums, int l, int r) {
    int pivot = nums[r];
    int i = l - 1;
    for (int j = l; j < r; j++) {
      if (nums[j] < pivot) {
        i++;
        swap(nums, i, j);
      }
    }
    i++;
    swap(nums, i, r);
    return i;
  }


  private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

}
