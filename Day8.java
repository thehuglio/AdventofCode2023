//class Directions used in both parts
package org.thehuglio;

import java.util.LinkedList;
import java.util.List;

public class Directions {
    private final List<String> current = new LinkedList<>();
    private final List<String> left = new LinkedList<>();
    private final List<String> right = new LinkedList<>();

    public void setDirections(String current,String left,String right) {
        this.current.add(current);
        this.left.add(left);
        this.right.add(right);
    }
    public String getDirections(String current,String leftright) {
        if (leftright.equals("L")) {
            return left.get(this.current.indexOf(current));
        } else {
            return right.get(this.current.indexOf(current));
        }
    }
    public Boolean currentcontains(String s) {
        return current.contains(s);
    }
    public int currentsize() {
        return current.size();
    }
}

//part 1
package org.thehuglio;

import java.io.File;
import java.util.*;

public class Main {

    private static final List<String> data = new Reader(new File("data.txt")).data;
    public static List<Integer> sorted = new LinkedList<>();

    public static void main(String[] args) {
        Directions directions = new Directions();
        for (int i = 2; i < data.size(); i++) {
            if (!data.get(i).equals("")) {
                String[] split = data.get(i).split(" = ");
                directions.setDirections(split[0], split[1].split(", ")[0].replace("(", ""), split[1].split(", ")[1].replace(")", ""));
            }
        }
        String[] rl = data.get(0).split("");
        String current = "AAA";
        int tot = 0;
        while (!current.equals("ZZZ")) {
            for (String direction : rl) {
                current = directions.getDirections(current,direction);
                tot++;
            }
        }
        System.out.println(tot);
    }
}

//part 2
package org.thehuglio;

import java.io.File;
import java.util.*;

public class Main {

    private static final List<String> data = new Reader(new File("data.txt")).data;
    public static List<Integer> sorted = new LinkedList<>();

    public static void main(String[] args) {
        Directions directions = new Directions();
        List<String> current = new LinkedList<>();
        List<String> end = new LinkedList<>();
        for (int i = 2; i < data.size(); i++) {
            if (!data.get(i).equals("")) {
                String[] split = data.get(i).split(" = ");
                directions.setDirections(split[0], split[1].split(", ")[0].replace("(", ""), split[1].split(", ")[1].replace(")", ""));
                if (split[0].endsWith("A")) {
                    current.add(split[0]);
                }
                if (split[0].endsWith("Z")) {
                    end.add(split[0]);
                }
            }
        }
        String[] rl = data.get(0).split("");
        long tot = 0;
        boolean check = true;
        while (check) {
            List<String> temp = new LinkedList<>();
            for (String s : current) {
                for (String direction : rl) {
                    temp.add(directions.getDirections(s, direction));
                }
            }
            tot++;
            for (String s : temp) {
                if (!end.contains(s)) {
                    check = true;
                    break;
                }
                check = false;
            }
            current = temp;
        }
        System.out.println(tot);
    }
}
