package liudu.niuke;

public class BM84 {

  public static void main(String[] args) {
    BM84 bm84 = new BM84();
    bm84.longestCommonPrefix(new String[]{"abca","abc","abca","abc","abcc"});
  }

  public String longestCommonPrefix(String[] strs) {
    // write code here
    if (strs.length == 0){
      return "";
    }
    if (strs.length == 1){
      return strs[0];
    }
    String pre = strs[0];
    for (int i = 1; i < strs.length; i++) {
      pre = longestCommonPrefix(pre,strs[i]);
    }

    return pre;
  }

  public String longestCommonPrefix(String str1, String str2) {
    int length = Math.min(str1.length(), str2.length());
    char[] chars1 = str1.toCharArray();
    char[] chars2 = str2.toCharArray();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < length; i++) {
      if (chars1[i] == chars2[i]) {
        sb.append(chars1[i]);
      } else {
        return sb.toString();
      }
    }

    return sb.toString();
  }

}
