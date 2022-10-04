package liudu.test.bitset;

public class RegularTest {

    public static void main(String[] args) {

        String s = "茅台|郎酒|国台|习酒|珍酒|金沙酒|钓鱼台|怀庄|潭酒|钓鱼";
        System.out.println(regular(s));
    }

    public static String regular(String s) {
        String s1 = s.replaceAll("\\(", "&(&")
                .replaceAll("\\)", "&)&")
                .replaceAll("\\|", "&|&");

        String[] s2 = s1.split("&");
        StringBuilder sb = new StringBuilder();
        for (String word : s2) {
            if (word.length() > 0) {
                System.out.println(word);
                switch (word) {
                    case "(":
                    case ")":
                        sb.append(word);
                        break;
                    case "+":
                        sb.append(" and ");
                        break;
                    case "|":
                        sb.append(" or ");
                        break;
                    default:
                        sb.append("keyword like '%").append(word).append("%'");
                        break;
                }
            }

        }

        return sb.toString();
    }
}
