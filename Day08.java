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

//part 2 (not finished
package org.thehuglio;

import java.io.File;
import java.util.*;

public class Main {

    private static final List<String> data = new Reader(new File("data.txt")).data;
    public static List<Integer> sorted = new LinkedList<>();

    public static void main(String[] args) {
        Directions directions = new Directions();
        List<String> current = new LinkedList<>();
        for (int i = 2; i < data.size(); i++) {
            if (!data.get(i).equals("")) {
                String[] split = data.get(i).split(" = ");
                directions.setDirections(split[0], split[1].split(", ")[0].replace("(", ""), split[1].split(", ")[1].replace(")", ""));
                if (split[0].endsWith("A")) {
                    current.add(split[0]);
                }
            }
        }
        String[] rl = data.get(0).split("");
        int tot = 0;
        boolean check = true;
        List<List<Integer>> loops = new LinkedList<>();
        List<Integer> done = new LinkedList<>();
        while (check) {
            for (String direction : rl) {
                List<String> temp = new LinkedList<>();
                for (String s : current) {
                    temp.add(directions.getDirections(s, direction));
                }
                tot++;
                for (int i = 0; i < temp.size(); i++) {
                    if (!done.contains(i)) {
                        if (temp.get(i).endsWith("Z")) {
                            int tempi = 0;
                            if (loops.size() <= i) {
                                loops.add(List.of(tot));
                            } else {
                                for (int j : loops.get(i)) {
                                    tempi += j;
                                }
                                int t = tot - tempi;
                                if (loops.get(i).contains(t)) {
                                    done.add(i);
                                } else {
                                    List<Integer> templist = new LinkedList<>(loops.get(i));
                                    templist.add(t);
                                    loops.set(i, templist);
                                }
                            }
                        }
                        check = true;
                    }
                    if (done.size() == current.size()) {
                        List<Long> lastlist = new LinkedList<>();
                        for (List<Integer> loop : loops) {
                            lastlist.add(Long.valueOf(loop.get(0)));
                        }
                        List<Integer> counter = new LinkedList<>(List.of(0,0,0,0,0,0,0));
                        boolean check1 = true;
                        long firstint = 0;
                        while (check1) {
                            for (int j = 0; j < loops.size(); j++) {
                                List<Integer> loop = loops.get(j);
                                for (int k = 0; k < loops.size(); k++) {
                                    if (lastlist.get(j) < lastlist.get(k)) {
                                        lastlist.set(j, lastlist.get(j) + loop.get(counter.get(j) % (loop.size() - 1) + 1));
                                        counter.set(j, counter.get(j) + 1);
                                    } else {break;}
                                }
                            }
                            firstint = lastlist.get(0);
                            for (long j : lastlist) {
                                if (j != firstint) {
                                    check1 = true;
                                    break;
                                }
                                check1 = false;
                            }
                        }
                        System.out.println(firstint);
                        check = false;
                        break;
                    }
                }
                current = temp;
                if (!check) { break; }
            }
        }
    }
}
