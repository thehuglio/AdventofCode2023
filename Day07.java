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
