package org.thehuglio;

import java.io.File;
import java.util.*;

public class Main {

    private static final List<String> data = new Reader(new File("data.txt")).data;
    public static List<Integer> sorted = new LinkedList<>();

    public static void main(String[] args) {
        HashMap<List<Integer>,Integer> fiveoak = new HashMap<>();
        HashMap<List<Integer>,Integer> fouroak = new HashMap<>();
        HashMap<List<Integer>,Integer> fullhouse = new HashMap<>();
        HashMap<List<Integer>,Integer> threeoak = new HashMap<>();
        HashMap<List<Integer>,Integer> twopair = new HashMap<>();
        HashMap<List<Integer>,Integer> onepair = new HashMap<>();
        HashMap<List<Integer>,Integer> highcard = new HashMap<>();

        for (String s : data) {
            String[] split = s.split(" ");
            String[] cards = split[0].split("");
            List<Integer> cardn = new LinkedList<>();
            for (String card : cards) {
                cardn.add(Arrays.asList("23456789TJQKA".split("")).indexOf(card));
            }
            int paren = 0;
            int safe = 0;
            for (int i = 0; i < cardn.size(); i++) {
                for (int j = i + 1; j < cardn.size(); j++) {
                    if (Objects.equals(cardn.get(i),cardn.get(j))) {
                        paren ++;
                    }
                }
            }
            switch (paren) {
                case 0:
                    highcard.put(cardn,Integer.parseInt(split[1]));
                    break;
                case 1:
                    onepair.put(cardn,Integer.parseInt(split[1]));
                    break;
                case 2:
                    twopair.put(cardn,Integer.parseInt(split[1]));
                    break;
                case 3:
                    threeoak.put(cardn,Integer.parseInt(split[1]));
                    break;
                case 4:
                    fullhouse.put(cardn,Integer.parseInt(split[1]));
                    break;
                case 6:
                    fouroak.put(cardn,Integer.parseInt(split[1]));
                    break;
                case 10:
                    fiveoak.put(cardn,Integer.parseInt(split[1]));
                    break;
            }
        }
        rank(highcard);
        rank(onepair);
        rank(twopair);
        rank(threeoak);
        rank(fullhouse);
        rank(fouroak);
        rank(fiveoak);
        int tot = 0;
        for (int i = 0; i < sorted.size(); i++) {
            System.out.println(String.valueOf(sorted.get(i)) + " " + sorted.get(i) * (i+1));
            tot += sorted.get(i) * (i+1);
        }
        System.out.println(tot);
    }
    private static void rank(HashMap<List<Integer>,Integer> hash) {
        for (int i = 0; i <= 12; i++) {
            for (int j = 0; j <= 12; j++) {
                for (int k = 0; k <= 12; k++) {
                    for (int l = 0; l <= 12; l++) {
                        for (int p = 0; p <= 12; p++) {
                            if (hash.containsKey(List.of(i, j, k, l, p))) {
                                sorted.add(hash.get(List.of(i, j, k, l, p)));
                            }
                        }
                    }
                }
            }
        }
    }
}

//part 2 (not done)
package org.thehuglio;

import java.io.File;
import java.util.*;

public class Main {

    private static final List<String> data = new Reader(new File("data.txt")).data;
    public static List<Integer> sorted = new LinkedList<>();

    public static void main(String[] args) {
        HashMap<List<Integer>,Integer> fiveoak = new HashMap<>();
        HashMap<List<Integer>,Integer> fouroak = new HashMap<>();
        HashMap<List<Integer>,Integer> fullhouse = new HashMap<>();
        HashMap<List<Integer>,Integer> threeoak = new HashMap<>();
        HashMap<List<Integer>,Integer> twopair = new HashMap<>();
        HashMap<List<Integer>,Integer> onepair = new HashMap<>();
        HashMap<List<Integer>,Integer> highcard = new HashMap<>();

        for (String s : data) {
            String[] split = s.split(" ");
            String[] cards = split[0].split("");
            List<Integer> cardn = new LinkedList<>();
            for (String card : cards) {
                cardn.add(Arrays.asList("J23456789TQKA".split("")).indexOf(card));
            }
            HashMap<Integer,Integer> sets = new HashMap<>();
            sets.put(0,0);
            for (int i = 0; i < cardn.size(); i++) {
                if (cardn.get(i) != 0) {
                    if (sets.containsKey(cardn.get(i))) {
                        sets.replace(cardn.get(i),sets.get(cardn.get(i)) + 1);
                    } else {
                        sets.put(cardn.get(i),1);
                    }
                } else {
                    for (int j = 0; j < 13; j++) {
                        if (sets.containsKey(j)) {
                            sets.replace(j,sets.get(j) + 1);
                        } else {
                            sets.put(j,1);
                        }
                    }
                }
            }
            if (sets.containsValue(5)) {
                fiveoak.put(cardn, Integer.parseInt(split[1]));
                continue;
            } else if (sets.containsValue(4)) {
                fouroak.put(cardn, Integer.parseInt(split[1]));
                continue;
            } else if (sets.containsValue(3)) {
                int temp = 0;
                for (Map.Entry<Integer, Integer> i : sets.entrySet()) {
                    if (i.getValue() == 3) {
                        temp++;
                    }
                }
                if ((sets.containsValue(2) && sets.get(0) == 0) || temp == 2) {
                    fullhouse.put(cardn,Integer.parseInt(split[1]));
                } else {
                    threeoak.put(cardn,Integer.parseInt(split[1]));
                }
                continue;
            } else if (sets.containsValue(2)) {
                int temp = 0;
                for (Map.Entry<Integer, Integer> i : sets.entrySet()) {
                    if (i.getValue() == 2) {
                        temp++;
                    }
                }
                if (temp == 2) {
                    twopair.put(cardn,Integer.parseInt(split[1]));
                } else {
                    onepair.put(cardn,Integer.parseInt(split[1]));
                }
                continue;
            }
            int temp = 0;
            for (Map.Entry<Integer, Integer> i : sets.entrySet()) {
                if (i.getValue() == 1) {
                    temp++;
                }
            }
            if (temp == 5) {
                highcard.put(cardn, Integer.parseInt(split[1]));
            } else {
                System.out.println("error");
            }
        }
        rank(highcard);
        rank(onepair);
        rank(twopair);
        rank(threeoak);
        rank(fullhouse);
        rank(fouroak);
        rank(fiveoak);
        int tot = 0;
        for (int i = 0; i < sorted.size(); i++) {
            System.out.println(sorted.get(i) + " " + sorted.get(i) * (i+1));
            tot += sorted.get(i) * (i+1);
        }
        System.out.println(tot);
    }
    private static void rank(HashMap<List<Integer>,Integer> hash) {
        for (int i = 0; i <= 12; i++) {
            for (int j = 0; j <= 12; j++) {
                for (int k = 0; k <= 12; k++) {
                    for (int l = 0; l <= 12; l++) {
                        for (int p = 0; p <= 12; p++) {
                            if (hash.containsKey(List.of(i, j, k, l, p))) {
                                sorted.add(hash.get(List.of(i, j, k, l, p)));
                            }
                        }
                    }
                }
            }
        }
    }
}
