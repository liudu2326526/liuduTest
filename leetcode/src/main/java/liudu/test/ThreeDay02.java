package liudu.test;

public class ThreeDay02 {

  public static void main(String[] args) {
    String s = "abcdefg";
    int k = 2;
    System.out.println(new SolutionThreeDay02().reverseLeftWords(s, k));
//    System.out.println(11 % 5);
  }

}


class SolutionThreeDay02 {

  public String reverseLeftWords(String s, int n) {
    StringBuffer sb = new StringBuffer();

    sb.append(s.substring(n));
    sb.append(s, 0, n);

    return sb.toString();
  }
}