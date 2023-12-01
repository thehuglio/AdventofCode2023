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

//part 2
package org.thehuglio;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Main {

    private static final List<String> data = new Reader(new File("data.txt")).data;


    public static void main(String[] args) {
        int tot = 0;
        String first = null;
        String last = null;
        List<String> numbers = Arrays.asList("1234567890".split(""));
        List<String> stringnumbers = Arrays.asList("null, one, two, three, four, five, six, seven, eight, nine".split(", "));
        for (String d : data) {
            first  = null;
            last = null;
            String[] split = d.split("");
            for (int i = 0; i < d.length(); i++) {
                if (numbers.contains(split[i])) {
                    first = split[i];
                    break;
                }
                int stringint = 0;
                for (String snumbers : stringnumbers) {
                    for (int p = 0; p < snumbers.length() || i + p < d.length(); p++) {
                        if (!Objects.equals(split[i + p], snumbers.split("")[p])) {
                            break;
                        } else if (p == snumbers.length()-1) {
                            stringint = stringnumbers.indexOf(snumbers);
                            break;
                        }
                    }
                }
                if (stringint != 0) {
                    first = String.valueOf(stringint);
                    break;
                }
            }
            for (int i = d.length() - 1; i >= 0; i--) {
                if (numbers.contains(split[i])) {
                    last = split[i];
                    break;
                }
                int stringint = 0;
                for (String snumbers : stringnumbers) {
                    for (int p = 0; p < snumbers.length() || i - p > 0; p++) {
                        if (!Objects.equals(split[i - p], snumbers.split("")[snumbers.length() - p - 1])) {
                            break;
                        } else if (p == snumbers.length()-1) {
                        stringint = stringnumbers.indexOf(snumbers);
                        break;
                        }
                    }
                }
                if (stringint != 0) {
                    last = String.valueOf(stringint);
                    break;
                }
            }
            tot += Integer.parseInt(first + last);
            System.out.println(first + last);
        }
        System.out.println(tot);
    }
}
