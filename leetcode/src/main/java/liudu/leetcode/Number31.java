package liudu.leetcode;

/**
 * @author liudu
 * @title: Number31
 * @projectName liuduTest
 * @description: 31. 下一个排列 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
 * <p>
 * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。 整数数组的 下一个排列
 * 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中， 那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
 * <p>
 * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。 而 arr = [3,2,1] 的下一个排列是
 * [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。 给你一个整数数组 nums ，找出 nums 的下一个排列。
 * <p>
 * 必须 原地 修改，只允许使用额外常数空间。
 * @date 2022/7/1上午9:29
 */
public class Number31 {

  public static void main(String[] args) {
    int[] i = {1, 1};
    new Number31().nextPermutation(i);
  }

  public void nextPermutation(int[] nums) {
    int n = nums.length;
    int i = n - 1;
    int pre = nums[i];
    for (; i >= 0; i--) {
      if (pre > nums[i]) {
        break;
      }
      pre = nums[i];
    }
    // 如果遍历完数组，则 i = -1,这个时候直接翻转数组
    int tmp;
    int j = n - 1;
    if (i >= 0) {
      for (; j >= 0; j--) {
        if (nums[j] > nums[i]) {
          break;
        }
      }
      tmp = nums[i];
      nums[i] = nums[j];
      nums[j] = tmp;
    }

    for (int k = i + 1; k <= n - 1; k++, n--) {
      tmp = nums[k];
      nums[k] = nums[n - 1];
      nums[n - 1] = tmp;
    }

  }

  public void revers(int[] ints, int left, int right) {

  }

}
