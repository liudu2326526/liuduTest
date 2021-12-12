package liudu.test;

public class FourDay02 {

  public static void main(String[] args) {
    int[] a = {5, 7, 7, 8, 8, 10};
    System.out.println(new SolutionFourDay02().search(a, 8));
  }

}


class SolutionFourDay02 {

  public int search(int[] nums, int target) {
    int count = 0;
    for (int num : nums) {
      if (num == target) {
        count++;
      } else if (num < target) {
        return count;
      }
    }
    return 0;
  }
}