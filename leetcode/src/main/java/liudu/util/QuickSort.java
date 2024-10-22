package liudu.util;

import java.util.Arrays;

public class QuickSort {

  public static void main(String[] args) {
    int[] ints = {2, 8, 4, 1, 3, 5, 6, 7};

    new QuickSort().sort(ints);

    System.out.println(Arrays.toString(ints));

  }

  public void sort(int[] ints) {
    sort(ints, 0, ints.length - 1);

  }

  public void sort(int[] ints, int left, int right) {
//    跳出循环条件
    if (right - left < 1) {
      return;
    }

//    选择数组里的一个随机数
    int randomIndex = (left + right) / 2;
    int base = ints[randomIndex];

//    将这个数放到最右边
    swap(ints, randomIndex, right);

//    创建 small 和 big 两个索引，
    int small = left - 1;
    int big = left;

//    遍历整个数组，直到 big 索引等于 right
    while (big != right) {
      if (ints[big] < base) {
        small++;
        swap(ints, big, small);
      }
      big++;
    }
//    交换 small + 1 和 right
    small++;
    swap(ints, small, right);

//    small + 1 为中间数进行下一次循环
    sort(ints, left, small - 1);
    sort(ints, small + 1, right);
  }

  private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }


}
