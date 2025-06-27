package liudu.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author Liu Du
 * @Date 2025/2/28
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 target 。
 * 返回和为 target 的 nums 子序列中，子序列 长度的最大值 。如果不存在和为 target 的子序列，返回 -1 。
 * 子序列 指的是从原数组中删除一些或者不删除任何元素后，剩余元素保持原来的顺序构成的数组。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3,4,5], target = 9
 * 输出：3
 * 解释：总共有 3 个子序列的和为 9 ：[4,5] ，[1,3,5] 和 [2,3,4] 。最长的子序列是 [1,3,5] 和 [2,3,4] 。所以答案为 3 。
 * 示例 2：
 * <p>
 * 输入：nums = [4,1,3,2,1,5], target = 7
 * 输出：4
 * 解释：总共有 5 个子序列的和为 7 ：[4,3] ，[4,1,2] ，[4,2,1] ，[1,1,5] 和 [1,3,2,1] 。最长子序列为 [1,3,2,1] 。所以答案为 4 。
 * 示例 3：
 * <p>
 * 输入：nums = [1,1,5,4,5], target = 3
 * 输出：-1
 * 解释：无法得到和为 3 的子序列。
 * <p>
 * 提示：
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 * 1 <= target <= 1000
 */
public class Number2915 {

  public static void main(String[] args) {
    ConcurrentSkipListMap<String,String> concurrentSkipListMap = new ConcurrentSkipListMap<>();
    concurrentSkipListMap.put("","");


    Number2915 number2915 = new Number2915();
    ArrayList<Integer> integers = new ArrayList<>();
    integers.add(1);
    integers.add(1);
    integers.add(5);
    integers.add(4);
    integers.add(5);
    System.out.println(number2915.lengthOfLongestSubsequence(integers, 3));
  }

  public int lengthOfLongestSubsequence(List<Integer> nums, int target) {
    HashMap<Integer, Integer> map = new HashMap<>();
    HashMap<Integer, Integer> tmp = new HashMap<>();

    map.put(0, 0);

    for (Integer num : nums) {

      for (Entry<Integer, Integer> entry : map.entrySet()) {
        Integer key = entry.getKey();
        Integer value = entry.getValue();
        if (key + num <= target) {
          tmp.put(key + num, value + 1);
        }
      }

      for (Entry<Integer, Integer> entry : tmp.entrySet()) {
        Integer key = entry.getKey();
        Integer value = entry.getValue();
        map.put(key, Math.max(value, map.getOrDefault(key, 0)));
      }

      tmp.clear();

    }

    return map.getOrDefault(target, -1);
  }

}
