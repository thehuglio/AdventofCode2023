//part 1 (not done yet)
package org.example;

import java.io.File;
import java.util.*;

public class Main {

    private static final List<String> data = new Reader(new File("data.txt")).data;

    public static void main(String[] args) {
        List<List<String>> datalists = new LinkedList<>();
        List<String> templist = new LinkedList<>();
        List<Long> seedlist = new LinkedList<>();
        for (String s : data.get(0).split(": ")[1].split(" ")) {
            seedlist.add(Long.parseLong(s));
        }
        for (int i = 3; i < data.size(); i++) {
            if (Objects.equals(data.get(i), "")) {
                i++;
                datalists.add(templist);
                templist = new LinkedList<>();
                continue;
            }
            templist.add(data.get(i));
        }
        for (List<String> datas : datalists) {
            List<Long> templist1 = new LinkedList<>();
            for (long i : seedlist) {
                for (int j = 0; j < datas.size(); j++) {
                    String[] datasplit = datas.get(j).split(" ");
                    long newlong = convert(Long.parseLong(datasplit[1]),Long.parseLong(datasplit[0]),Long.parseLong(datasplit[2]),i);
                    if (newlong != i) {
                        templist1.add(newlong);
                        break;
                    }
                    if (j == datas.size() - 1)  {
                        templist1.add(newlong);
                    }
                }
            }
            seedlist = templist1;
            System.out.println(seedlist);
        }
        long lowest = seedlist.get(0);
        for (long i : seedlist) {
            if (i < lowest) {
                lowest = i;
            }
        }
        System.out.println(lowest);
    }
    public static long convert(long dest, long source, long range, long convert){
        if (convert >= source && convert < source + range) {
            return dest + (convert - source);
        }
        return convert;
    }
}
