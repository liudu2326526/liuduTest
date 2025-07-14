package liudu.leetcode;

import java.util.Arrays;

public class Number2410 {

    public static void main(String[] args) {
        int[] players = { 4, 7, 9 };
        int[] trainers = { 8, 2, 5, 8 };
        Number2410 number2410 = new Number2410();
        System.out.println(number2410.matchPlayersAndTrainers(players, trainers));
    }

    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);

        int index = 0;
        int num = 0;

        for (int i = 0; i < players.length; i++) {
            if (index >= trainers.length) {
                return num;
            }
            while (index < trainers.length && players[i] > trainers[index]) {
                index++;
                if (index >= trainers.length) {
                    return num;
                }
            }
            num++;
            index++;
        }

        return num;
    }

}
