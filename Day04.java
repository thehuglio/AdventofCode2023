//part 1
package org.example;

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
