package liudu.test;

public class FiveDay02 {

  public static void main(String[] args) {
    System.out.println(new SolutionFiveDay02().minArray(new int[]{1,3,5}));
  }

}

class SolutionFiveDay02 {

  public int minArray(int[] numbers) {
    return exe(numbers, 0, numbers.length - 1);
  }

  public static int exe(int[] numbers, int p1, int p2) {

    // 边界条件
    if (p1 == p2) {
      return numbers[p1];
    }

//    if (p2 - p1 == 1) {
//      return Math.min(numbers[p2], numbers[p1]);
//    }

    //循环体
    int mid = (p1 + p2) / 2;
    if (numbers[p2] > numbers[mid]) {
      p2 = mid;
    } else if (numbers[p2] < numbers[mid]){
      p1 = mid + 1;
    } else {
      p2--;
    }

//    int mid = (p1 + p2) / 2;
//    if (numbers[p1] < numbers[mid]) {
//      p2 = mid + 1;
//    } else if (numbers[p1] > numbers[mid]){
//      p1 = mid;
//    } else {
////      p2--;
//      p1++;
//    }

    return exe(numbers, p1, p2);

  }
}