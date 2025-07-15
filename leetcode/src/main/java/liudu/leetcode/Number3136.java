package liudu.leetcode;

public class Number3136 {


    public boolean isValid(String word) {
        if (word.length() < 3) {
            return false;
        }
        
        boolean hasVowel = false;
        boolean hasConsonant = false;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(!((c>='A' && c<='Z') || (c>='a' && c<='z') || (c>='0' && c<='9'))) {
                return false;
            }

            if (c == 'a' || c == 'A' || c == 'e' || c == 'E' || c == 'i' || c == 'I' || c == 'o' || c == 'O' || c == 'u' || c == 'U') {
                hasVowel = true;
            }
            if (c == 'b' || c == 'B' || c == 'c' || c == 'C' || c == 'd' || c == 'D' || c == 'f' || c == 'F' || c == 'g' || c == 'G' || c == 'h' || c == 'H' || c == 'j' || c == 'J' || c == 'k' || c == 'K' || c == 'l' || c == 'L' || c == 'm' || c == 'M' || c == 'n' || c == 'N' || c == 'p' || c == 'P' || c == 'q' || c == 'Q' || c == 'r' || c == 'R' || c == 's' || c == 'S' || c == 't' || c == 'T' || c == 'v' || c == 'V' || c == 'w' || c == 'W' || c == 'x' || c == 'X' || c == 'y' || c == 'Y' || c == 'z' || c == 'Z') {
                hasConsonant = true;
            }
        }

        return hasVowel && hasConsonant ;
    }
}
