package liudu.leetcode.node4;

public class Number427 {

  public Node construct(int[][] grid) {

    return construct(grid, 0, grid[0].length-1,0,grid.length-1 );
  }


  public Node construct(int[][] grid, int left, int right, int top, int bottom) {
    if (left == right && top == bottom) {
      return new Node(grid[top][left] == 1, true);
    }
    int i = (left + right) / 2;
    int j = (top + bottom) / 2;

    Node construct1 = construct(grid, left, i, top, j);
    Node construct2 = construct(grid, i + 1, right, j + 1, bottom);
    Node construct3 = construct(grid, left, i, j + 1, bottom);
    Node construct4 = construct(grid, i + 1, right, top, j);

    if (construct1.isLeaf
        && construct2.isLeaf
        && construct3.isLeaf
        && construct4.isLeaf
        && construct1.val == construct2.val
        && construct3.val == construct4.val
        && construct3.val == construct2.val
    ) {
      return new Node(construct1.val, true);
    }

    Node node = new Node(true, false);
    node.topLeft = construct1;
    node.topRight = construct4;
    node.bottomLeft = construct3;
    node.bottomRight = construct2;

    return node;
  }


}

class Node {

  public boolean val;
  public boolean isLeaf;
  public Node topLeft;
  public Node topRight;
  public Node bottomLeft;
  public Node bottomRight;


  public Node() {
    this.val = false;
    this.isLeaf = false;
    this.topLeft = null;
    this.topRight = null;
    this.bottomLeft = null;
    this.bottomRight = null;
  }

  public Node(boolean val, boolean isLeaf) {
    this.val = val;
    this.isLeaf = isLeaf;
    this.topLeft = null;
    this.topRight = null;
    this.bottomLeft = null;
    this.bottomRight = null;
  }

  public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft,
      Node bottomRight) {
    this.val = val;
    this.isLeaf = isLeaf;
    this.topLeft = topLeft;
    this.topRight = topRight;
    this.bottomLeft = bottomLeft;
    this.bottomRight = bottomRight;
  }
}
