package liudu.leetcode;

import java.util.HashMap;

public class Number13 {

  public static void main(String[] args) {
    Number13 number13 = new Number13();
    System.out.println(number13.romanToInt("III"));
  }

  public int romanToInt(String s) {
    HashMap<Character, Character> charMap = new HashMap<>();
    charMap.put('X', 'I');
    charMap.put('V', 'I');
    charMap.put('C', 'X');
    charMap.put('L', 'X');
    charMap.put('M', 'C');
    charMap.put('D', 'C');
    HashMap<Character, Integer> charValue = new HashMap<>();
    charValue.put('I', 1);
    charValue.put('V', 5);
    charValue.put('X', 10);
    charValue.put('L', 50);
    charValue.put('C', 100);
    charValue.put('D', 500);
    charValue.put('M', 1000);

    char upper = s.charAt(s.length() - 1);

    int sum = 0;

    for (int i = s.length() - 1; i >= 0; i--) {
      if (i < s.length() - 1) {
        if (charMap.containsKey(upper) && charMap.get(upper) == s.charAt(i)) {
          sum -= charValue.get(s.charAt(i));
        } else {
          sum += charValue.get(s.charAt(i));
        }
      } else {
        sum += charValue.get(s.charAt(i));
      }
      upper = s.charAt(i);

    }

    return sum;
  }

}
