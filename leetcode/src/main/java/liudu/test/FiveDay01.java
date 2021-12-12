package liudu.test;

public class FiveDay01 {

  public static void main(String[] args) {
//    int[][] a = {{1, 4}, {2, 5}};
//    int[][] a = {{-5}};
    int[][] a = {
        {1, 2, 3, 4, 5}
        , {6, 7, 8, 9, 10}
        , {11, 12, 13, 14, 15}
        , {16, 17, 18, 19, 20}
        , {21, 22, 23, 24, 25}};
    System.out.println(new SolutionFiveDay01().findNumberIn2DArray(a, 15));
  }

}


class SolutionFiveDay01 {

  public static boolean findNumberIn2DArray(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0) {
      return false;
    }
    return exe(matrix, target, 0, matrix[0].length - 1, matrix.length - 1, 0);
  }

  public static boolean exe(int[][] martix, int target, int l, int r, int d, int t) {
    if (l > r || t > d) {
      return false;
    }
    if (l == r && d == t) {
      return martix[d][l] == target;
    }
    int xmid = (d + t) / 2, ymid = (r + l) / 2;
    if (martix[xmid][ymid] == target) {
      return true;
    } else if (martix[xmid][ymid] > target) {
      return exe(martix, target, l, ymid - 1, xmid - 1, t) || exe(martix, target, l, ymid - 1, d,
          xmid) || exe(martix, target, ymid, r, xmid - 1, t);
    } else {
      return exe(martix, target, ymid + 1, r, d, xmid + 1) || exe(martix, target, l, ymid, d,
          xmid + 1) || exe(martix, target, ymid + 1, r, xmid, t);
    }
  }
}


class SolutionFiveDay01V2 {

  public static boolean findNumberIn2DArray(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0) {
      return false;
    }
    return exe(matrix, target, 0, matrix.length - 1);
  }

  public static boolean exe(int[][] martix, int target, int l, int d) {

    if (l > martix[0].length - 1 || d < 0) {
      return false;
    }

    if (martix[d][l] == target) {
      return true;
    } else if (martix[d][l] > target) {
      return exe(martix, target, l, d - 1);
    } else {
      return exe(martix, target, l + 1, d);
    }

  }

}