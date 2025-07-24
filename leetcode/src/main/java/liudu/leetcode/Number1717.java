package liudu.leetcode;

import java.util.Stack;

/**
 * @author Liu Du
 * @Date 2025/7/23
 * 
 * LeetCode 1717: Maximum Score From Removing Substrings
 * 
 * Problem: Remove "ab" and "ba" substrings to maximize score
 * - Removing "ab" gives x points
 * - Removing "ba" gives y points
 * 
 * Approach: Greedy algorithm with stack
 * 1. Always remove higher-scoring pattern first using stack
 * 2. Then remove lower-scoring pattern from remaining string
 * 3. This ensures maximum total score
 * 
 * Time Complexity: O(n) - two passes through string
 * Space Complexity: O(n) - stack storage in worst case
 *
 * 1. Always removes higher-scoring pattern first - ensures maximum total score
 * 2. Uses stack for O(n) efficiency - eliminates recursive overhead and string copying
 * 3. Two-pass algorithm - first removes higher-scoring pattern, then lower-scoring from remainder
 * 4. Handles both x > y and y > x cases - recursively swaps parameters when needed
 *
 * Key improvements:
 * - Time: O(2^n) → O(n)
 * - Space: O(n) HashMap strings → O(n) stack characters
 * - Much cleaner and more efficient implementation
 */
public class Number1717 {

  public static void main(String[] args) {
    Number1717 number1717 = new Number1717();
    System.out.println(number1717.maximumGain("cdbcbbaaabab", 4, 5));
  }

  public int maximumGain(String s, int x, int y) {
    // Determine which pattern to remove first (higher score)
    if (x < y) {
      s = s.replace('a', '\0').replace('b', 'a').replace('\0', 'b');
      // If y > x, swap them to always handle higher score first
      return maximumGain(s, y, x);
    }
    
    // x >= y, so remove "ab" first, then "ba"
    int score = 0;
    
    // First pass: remove "ab" (higher score x)
    Stack<Character> stack = new Stack<>();
    for (char c : s.toCharArray()) {
      if (c == 'b' && !stack.isEmpty() && stack.peek() == 'a') {
        // Found "ab", remove it and add score
        stack.pop();
        score += x;
      } else {
        stack.push(c);
      }
    }
    
    // Second pass: remove "ba" from remaining characters (lower score y)
    StringBuilder remaining = new StringBuilder();
    while (!stack.isEmpty()) {
      remaining.append(stack.pop());
    }
    remaining.reverse(); // Stack gives reversed order
    
    stack.clear();
    for (char c : remaining.toString().toCharArray()) {
      if (c == 'a' && !stack.isEmpty() && stack.peek() == 'b') {
        // Found "ba", remove it and add score
        stack.pop();
        score += y;
      } else {
        stack.push(c);
      }
    }
    
    return score;
  }

}
