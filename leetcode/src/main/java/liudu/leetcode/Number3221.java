package liudu.leetcode;

/**
 * @author Liu Du
 * @Date 2025/2/18
 * 给定一个数组 nums，你必须从索引 0 开始跳跃，直到到达数组的最后一个元素，使得获取 最大 分数。
 * 每一次 跳跃 中，你可以从下标 i 跳到一个 j > i 的下标，并且可以得到 (j - i) * nums[j] 的分数。
 * 返回你能够取得的最大分数。
 * <p>
 * 示例 1：
 * 输入：nums = [1,5,8]
 * 输出：16
 * 解释：
 * 有两种可能的方法可以到达最后一个元素：
 * <p>
 * 0 -> 1 -> 2 得分为 (1 - 0) * 5 + (2 - 1) * 8 = 13。
 * 0 -> 2 得分为 (2 - 0) * 8 = 16。
 * 示例 2：
 * 输入：nums = [4,5,2,8,9,1,3]
 * 输出：42
 * 解释：
 * 我们可以按 0 -> 4 -> 6 进行跳跃，得分为 (4 - 0) * 9 + (6 - 4) * 3 = 42。
 * 提示：
 * 2 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 */
public class Number3221 {

  public long maxScore(int[] nums) {
    int n = nums.length;
//    int[] high = new int[n];

    int max = 0;
    long result = 0;
    for (int i = n - 1; i >= 1; i--) {
      max = Math.max(max, nums[i]);
      result += max;
    }

    return result;
  }

}
