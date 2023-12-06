//part 1
package org.thehuglio;

import java.io.File;
import java.util.*;

public class Main {

    private static final List<String> data = new Reader(new File("data.txt")).data;

    public static void main(String[] args) {
        int tot = 1;
        List<Integer> time = new LinkedList<>();
        List<Integer> distance = new LinkedList<>();
        String[] split = data.get(0).split(":")[1].split(" ");
        for (String s : split) {
            if (!Objects.equals(s, "") && !Objects.equals(s," ")) {
                time.add(Integer.parseInt(s));
            }
        }
        split = data.get(1).split(":")[1].split(" ");
        for (String s : split) {
            if (!Objects.equals(s, "") && !Objects.equals(s," ")) {
                distance.add(Integer.parseInt(s));
            }
        }
        for (int i = 0; i < time.size(); i++) {
            int speed = 0;
            int maxspeed = 0;
            int minspeed = 0;
            while (speed < time.get(i)) {
                if (distance.get(i) < speed * (time.get(i) - speed) && minspeed == 0) {
                    minspeed = speed;
                }
                else if (distance.get(i) < speed * (time.get(i) - speed)) {
                    maxspeed = speed;
                }
                speed++;
            }
            tot *= (maxspeed - minspeed) + 1;
        }
        System.out.println(tot);
    }
}