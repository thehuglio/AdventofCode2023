//part 1
package org.thehuglio;

import java.io.File;
import java.util.List;

public class Main {

    private static final List<String> data = new Reader(new File("data.txt")).data;


    public static void main(String[] args) {
        int tot = 0;
        for (int i = 0; i < data.size(); i++) {
            boolean check = true;
            for (String game : data.get(i).split(": ")[1].split("; ")) {
                for (String balls : game.split(", ")) {
                    String[] split = balls.split(" ");
                    if (Integer.parseInt(split[0]) > 14 && split[1].equals("blue")) {
                        check = false;
                    }
                    if (Integer.parseInt(split[0]) > 12 && split[1].equals("red")) {
                        check = false;
                    }
                    if (Integer.parseInt(split[0]) > 13 && split[1].equals("green")) {
                        check = false;
                    }
                }
            }
            if (check) {
                tot += i + 1;
            }
        }
        System.out.println(tot);
    }
}

//part 2
package org.thehuglio;

import java.io.File;
import java.util.List;

public class Main {

    private static final List<String> data = new Reader(new File("data.txt")).data;


    public static void main(String[] args) {
        int tot = 0;
        for (int i = 0; i < data.size(); i++) {
            int blue = 0;
            int red = 0;
            int green = 0;
            for (String game : data.get(i).split(": ")[1].split("; ")) {
                for (String balls : game.split(", ")) {
                    String[] split = balls.split(" ");
                    int number = Integer.parseInt(split[0]);
                    if (number > blue && split[1].equals("blue")) {
                        blue = number;
                    }
                    else if (number > red && split[1].equals("red")) {
                        red = number;
                    }
                    else if (number > green && split[1].equals("green")) {
                        green = number;
                    }
                }
            }
            tot += blue * red * green;
        }
        System.out.println(tot);
    }
}
