// this code consists of 2 classes used in both parts, there are comments of the code that needs to be changed bethween the 2 parts
package org.thehuglio;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Numbers {
    private final List<List<Integer>> lists = new LinkedList<>();

    public Numbers(List<Integer> firstlist) {
        lists.add(firstlist);
        List<Integer> curentlist = firstlist;
        boolean check = true;
        while (check) {
            List<Integer> temp = new LinkedList<>();
            for (int i = 1; i < curentlist.size(); i++) {
                temp.add(curentlist.get(i) - curentlist.get(i - 1));
            }
            lists.add(temp);
            curentlist = temp;
            for (int i = 1; i < curentlist.size(); i++) {
                if (!Objects.equals(curentlist.get(i), curentlist.get(i - 1))) {
                    check = true;
                    break;
                }
                check = false;
            }
        }
    }
    public int calcsidenumber() {
        int safe = 0;
        for (int i = lists.size() - 1; i >= 0; i--) {
            safe = lists.get(i).get(lists.get(i).size() - 1) + safe; // for the second part change this line to safe = lists.get(i).get(lists.get(0)) - safe;
        }
        return safe;
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
        int tot = 0;
        for (String data : data) {
            String[] split = data.split(" ");
            List<Integer> mainvalues = new LinkedList<>();
            for (int i = 0; i < data.split(" ").length; i++) {
                mainvalues.add(Integer.parseInt(split[i]));
            }
            tot += new Numbers(mainvalues).calcsidenumber();
        }
        System.out.println(tot);
    }
}
