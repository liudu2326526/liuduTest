package liudu.leetcode;

public class Number165 {

  public static void main(String[] args) {
    Number165 number165 = new Number165();
    System.out.println(number165.compareVersion("1", "1.0"));
  }

  public int compareVersion(String version1, String version2) {
    String[] split1 = version1.split("\\.");
    String[] split2 = version2.split("\\.");

    int l1 = split1.length;
    int l2 = split2.length;

    int l = Math.min(l1, l2);

//    对比位数相同的
    for (int i = 0; i < l; i++) {
      long num1 = Long.parseLong(split1[i]);
      long num2 = Long.parseLong(split2[i]);
      if (num1 > num2) {
        return 1;
      } else if (num2 > num1) {
        return -1;
      }
    }

//    对比超出的位数
    if (l1 > l2) {
      for (int i = l; i < l1; i++) {
        long num1 = Long.parseLong(split1[i]);
        if (num1 > 0) {
          return 1;
        }
      }
    } else if (l2 > l1) {
      for (int i = l; i < l2; i++) {
        long num2 = Long.parseLong(split2[i]);
        if (num2 > 0) {
          return -1;
        }
      }
    }

    return 0;
  }

}
