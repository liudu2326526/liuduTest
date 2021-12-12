package liudu.test;

public class FourDay03 {

  public static void main(String[] args) {
    int[] a = {0, 1, 3};
    System.out.println(new SolutionFourDay03().missingNumber(a));
  }

}


class SolutionFourDay03 {

  public int missingNumber(int[] nums) {
    return missingNumber(nums, 0, nums.length - 1);
  }


  public int missingNumber(int[] nums, int begin, int end) {

    if (end - begin == 1 || begin == end) {
      if (nums[begin] > begin) {
        return begin;
      } else if (nums[end] > end) {
        return end;
      } else {
        return end + 1;
      }
    }

    int middle = (begin + end) / 2;
    if (nums[middle] > middle ) {
      return missingNumber(nums, begin, middle);
    } else {
      return missingNumber(nums, middle, end);
    }
  }


}