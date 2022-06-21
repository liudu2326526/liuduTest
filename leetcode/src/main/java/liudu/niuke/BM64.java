package liudu.niuke;

import java.util.Scanner;

public class BM64 {


  public static void main(String[] args) {
    try (Scanner in = new Scanner(System.in)) {
      while (in.hasNext()) {
        int a = in.nextInt();
        int b = in.nextInt();
        System.out.println(a + b);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


}
