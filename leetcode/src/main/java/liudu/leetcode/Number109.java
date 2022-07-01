package liudu.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 109. 有序链表转换二叉搜索树
 * <p>
 * 给定一个单链表的头节点 head，其中的元素 按升序排序 ，将其转换为高度平衡的二叉搜索树。
 * <p>
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点的左右两个子树的高度差不超过 1。
 */
public class Number109 {

  public static void main(String[] args) {
    ListNode listNode1 = new ListNode(-10);
    ListNode listNode2 = new ListNode(-3);
    ListNode listNode3 = new ListNode(0);
    ListNode listNode4 = new ListNode(5);
    ListNode listNode5 = new ListNode(9);

    listNode1.next = listNode2;
    listNode2.next = listNode3;
    listNode3.next = listNode4;
    listNode4.next = listNode5;

    TreeNode treeNode = new Number109().sortedListToBST(listNode1);
    treeNode.BFSPrint();
  }


  public TreeNode sortedListToBST(ListNode head) {
    if (head == null){
      return null;
    }
    ArrayList<Integer> list = new ArrayList<>();
    while (head != null) {
      list.add(head.val);
      head = head.next;
    }
    Integer[] ints = list.toArray(Integer[]::new);

    return sortedListToBST(ints, 0, ints.length - 1);
  }

  public TreeNode sortedListToBST(Integer[] ints, int left, int right) {
    int mid = (left + right) / 2;
    TreeNode treeNode = new TreeNode(ints[mid]);
    if (mid > left) {
      treeNode.left = sortedListToBST(ints, left, mid - 1);
    }
    if (mid < right) {
      treeNode.right = sortedListToBST(ints, mid + 1, right);
    }
    return treeNode;
  }

}
