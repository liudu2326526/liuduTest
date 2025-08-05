package liudu.leetcode;

/**
 * @author Liu Du
 * @Date 2025/8/5
 */
public class Number3479 {

  public static void main(String[] args) {
    Number3479 number3479 = new Number3479();
    int[] fruits = {4};
    int[] baskets = {4};
    System.out.println(number3479.numOfUnplacedFruits(fruits, baskets));
  }

  public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
    SegmentTree segmentTree = new SegmentTree(baskets);
    int num = 0;
    int n = fruits.length;
    for (int fruit : fruits) {
      int index = segmentTree.queryLeftMostGreaterThan(0, n - 1, fruit);
      if (index >= 0) {
        segmentTree.update(index, 0);
      } else {
        num++;
      }

    }

    return num;
  }

  /**
   * 最大值的线段树
   */
  class SegmentTree {

    //二叉树的数组表现方式
    //左孩子：2*i + 1（如果存在）
    //右孩子：2*i + 2
    //父节点：(i - 1) / 2（向下取整）
    int[] maxTree;    // 线段树，记录每个区间的最大值
    int[] nums;

    public SegmentTree(int[] nums) {
      this.nums = nums;
      int n = nums.length;
      maxTree = new int[4 * n];  // 足够大
      build(0, 0, n - 1);
    }

    public void build(int node, int l, int r) {
      if (l == r) {
        maxTree[node] = nums[l];
      } else {
        int mid = (l + r) / 2;
        build(2 * node + 1, l, mid);
        build(2 * node + 2, mid + 1, r);
        maxTree[node] = Math.max(maxTree[2 * node + 1], maxTree[2 * node + 2]);
      }
    }

    public int queryLeftMostGreaterThan(int ql, int qr, int val) {
      return queryLeftMostGreaterThan(0, 0, nums.length - 1, ql, qr, val);
    }

    // 找到范围内的最小值的位置
    public int queryLeftMostGreaterThan(int node, int l, int r, int ql, int qr, int val) {
      if (qr < l || r < ql || maxTree[node] < val) {
        return -1;  // 剪枝
      }

      if (l == r) {
        return nums[l] >= val ? l : -1;  // 叶子节点判断
      }

      int mid = (l + r) / 2;

      // 优先在左子树找最左满足条件的位置
      int leftResult = queryLeftMostGreaterThan(2 * node + 1, l, mid, ql, qr, val);
      if (leftResult != -1) {
        return leftResult;
      }

      return queryLeftMostGreaterThan(2 * node + 2, mid + 1, r, ql, qr, val);

    }

    public void update(int idx, int val) {
      update(0, 0, nums.length - 1, idx, val);
    }


    public void update(int node, int l, int r, int idx, int val) {
      if (l == r) {
        nums[idx] = val;
        maxTree[node] = val;
      } else {
        int mid = (l + r) / 2;
        if (idx <= mid) {
          update(node * 2 + 1, l, mid, idx, val);
        } else {
          update(node * 2 + 2, mid + 1, r, idx, val);
        }
        maxTree[node] = Math.max(maxTree[node * 2 + 1], maxTree[node * 2 + 2]);
      }

    }


  }


}
