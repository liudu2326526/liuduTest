package liudu.leetcode;

public class Number58 {

  public static void main(String[] args) {
    Number58 number58 = new Number58();
    System.out.println(number58.lengthOfLastWord("   fly me   to   the moon  "));
  }

  public int lengthOfLastWord(String s) {
    int num = 0;
    boolean flag = false;
    for (int i = s.length()-1; i >= 0; i--) {
      if (s.charAt(i) !=' '){
        num++;
        flag = true;
      }

      if (s.charAt(i) == ' ' && flag){
        return num;
      }
    }


    return num;
  }

}
