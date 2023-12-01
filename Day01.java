//part 1
package org.thehuglio;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static final List<String> data = new Reader(new File("data.txt")).data;


    public static void main(String[] args) {
        int tot = 0;
        String first = null;
        String last = null;
        List<String> numbers = Arrays.asList("1234567890".split(""));
        for (String d : data) {
            first  = null;
            last = null;
            String[] split = d.split("");
            for (int i = 0; i < d.length(); i++) {
                if (numbers.contains(split[i])) {
                    first = split[i];
                    break;
                }
            }
            for (int i = d.length() - 1; i >= 0; i--) {
                if (numbers.contains(split[i])) {
                    last = split[i];
                    break;
                }
            }
            tot += Integer.parseInt(first + last);
            System.out.println(tot);
        }
        System.out.println(tot);
    }
}
