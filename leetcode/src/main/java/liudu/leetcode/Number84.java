package liudu.leetcode;

import java.util.Stack;

/**
 * 84. 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 */
public class Number84 {

    public int largestRectangleArea(int[] heights) {
        // 获取数组长度
        int n = heights.length;
        // 创建左边界数组，记录每个柱子左边第一个比它小的柱子的位置
        int[] left = new int[n];
        // 创建右边界数组，记录每个柱子右边第一个比它小的柱子的位置
        int[] right = new int[n];
        // 创建单调栈，用于维护递增的柱子高度
        Stack<Integer> stack = new Stack<>(); // 存放数组下标
        // 初始化结果数组为 -1
        for (int i = 0; i < n; i++) {
            // 初始化左边界为数组长度（表示右边没有比它小的）
            left[i] = n;
            // 初始化右边界为-1（表示左边没有比它小的）
            right[i] = -1;
        }

        // 从左到右遍历，找到每个柱子左边第一个比它小的柱子
        for (int i = 0; i < n; i++) {
            // 当栈不为空且栈顶柱子高度大于当前柱子时，弹出栈顶元素
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                // 栈顶柱子的左边界就是当前柱子的位置
                left[stack.pop()] = i;
            }
            // 将当前柱子入栈
            stack.push(i);
        }
        // 清空栈，准备从右到左遍历
        stack.empty();
        // 从右到左遍历，找到每个柱子右边第一个比它小的柱子
        for (int i = n - 1; i >= 0; i--) {
            // 当栈不为空且栈顶柱子高度大于当前柱子时，弹出栈顶元素
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                // 栈顶柱子的右边界就是当前柱子的位置
                right[stack.pop()] = i;
            }
            // 将当前柱子入栈
            stack.push(i);
        }
        // 初始化最大面积
        int max = 0;

        // 计算每个柱子能构成的最大矩形面积
        for (int i = 0; i < n; i++) {
            // 面积 = 高度 * (右边界 - 左边界 - 1)
            max = Math.max(max, heights[i] * (left[i] - right[i] - 1));
        }

        // 返回最大面积
        return max;
    }
}
