package liudu.leetcode;

/**
 * @author Liu Du
 * @Date 2025/7/17
 */
public class Number3202 {

  public static void main(String[] args) {




  }


//  如果当前的数是 2，那么他的长度是 2、x 结尾的子序列的长度加 1，然后这个子序列变成 x、2 结尾的子序列
  public int maximumLength(int[] nums,int k) {
    int ans = 0;
    int[][] f = new int[k][k];
    for (int x : nums) {
      x %= k;
      for (int y = 0; y < k; y++) {
        f[y][x] = f[x][y] + 1;
        ans = Math.max(ans, f[y][x]);
      }
    }
    return ans;
  }

}
