package liudu.test;

public class FourDay01 {

}


class SolutionFourDay01 {

  public int findRepeatNumber(int[] nums) {
    int[] bitmap = new int[nums.length];
    for (int num : nums) {
      if (bitmap[num] == 0) {
        bitmap[num] = 1;
      } else {
        return num;
      }
    }
    return 0;
  }
}