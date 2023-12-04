//part 1
package org.thehuglio;

import java.io.File;
import java.util.*;

public class Main {

    private static final List<String> data = new Reader(new File("data.txt")).data;

    public static void main(String[] args) {
        int tot = 0;
        for (String datas : data) {
            String[] split = datas.split(":")[1].split("\\| ");
            List<String> winners = Arrays.asList(split[0].split(" "));
            int totalwinners = 0;
            for (String s : split[1].split(" ")) {
                if (!s.equals("")) {
                    if (winners.contains(s)) {
                        totalwinners++;
                    }
                }
            }
            if (totalwinners > 0) {
                int score = 1;
                for (int i = 1; i < totalwinners; i++) {
                    score *= 2;
                }
                tot += score;
            }
        }
        System.out.println(tot);
    }
}
//part 2
package org.thehuglio;

import org.thehuglio.Reader;

import java.io.File;
import java.util.*;

public class Main {

    private static final List<String> data = new Reader(new File("data.txt")).data;

    public static void main(String[] args) {
        int tot = 0;
        int[] gamesafe = new int[data.size()];
        Arrays.fill(gamesafe, 1);
        for (int i = 1; i <= data.size(); i++) {
            String[] split = data.get(i - 1).split(":")[1].split("\\| ");
            List<String> winners = Arrays.asList(split[0].split(" "));
            int gamepointer = i;
            for (String s : split[1].split(" ")) {
                if (!s.equals("")) {
                    if (winners.contains(s)) {
                        gamesafe[gamepointer] += gamesafe[i - 1];
                        gamepointer++;
                    }
                }
            }
        }
        for (int i : gamesafe) {
            tot += i;
        }
        System.out.println(tot);
    }
}
