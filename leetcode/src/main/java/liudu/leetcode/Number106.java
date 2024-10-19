package liudu.leetcode;

public class Number106 {

  public static void main(String[] args) {
    int[] inorder = {1, 2, 3, 4, 5, 6};
    int[] postorder = {1, 4, 3, 6, 5, 2};

    Number106 number106 = new Number106();
    number106.buildTree(inorder, postorder);
  }

  public TreeNode buildTree(int[] inorder, int[] postorder) {
    return buildTree(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
  }


  public TreeNode buildTree(int[] inorder, int[] postorder, int inLeft, int inRight, int poLeft,
      int poRight) {
    if (inLeft == inRight) {
      return new TreeNode(inorder[inLeft]);
    }

    int i = inLeft;
    for (; i <= inRight; i++) {
      if (inorder[i] == postorder[poRight]) {
        break;
      }
    }
    TreeNode treeNode = new TreeNode(inorder[i]);

    if (i == inLeft) {
      treeNode.right = buildTree(inorder, postorder, inLeft + 1, inRight, poLeft, poRight - 1);
    } else if (i == inRight) {
      treeNode.left = buildTree(inorder, postorder, inLeft, inRight - 1, poLeft, poRight - 1);
    } else {
      treeNode.left = buildTree(inorder, postorder, inLeft, i - 1, poLeft, poLeft + i - 1 - inLeft);
      treeNode.right = buildTree(inorder, postorder, i + 1, inRight, poRight - 1 - (inRight - i - 1),
          poRight - 1);
    }

    return treeNode;
  }


}
