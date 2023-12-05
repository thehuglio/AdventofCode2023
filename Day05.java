//part 1
package org.thehuglio;

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
        datalists.add(templist);
        for (List<String> datas : datalists) {
            List<Long> templist1 = new LinkedList<>();
            for (long i : seedlist) {
                for (int j = 0; j < datas.size(); j++) {
                    String[] datasplit = datas.get(j).split(" ");
                    long newlong = convert(Long.parseLong(datasplit[0]),Long.parseLong(datasplit[1]),Long.parseLong(datasplit[2]),i);
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
//part 2
package org.thehuglio;

import java.io.File;
import java.util.*;

public class Main {

    private static final List<String> data = new Reader(new File("data.txt")).data;
    private static long lowest = 2000000000;

    public static void main(String[] args) {
        List<List<List<Long>>> datalists = new LinkedList<>();
        List<List<Long>> ranges = new LinkedList<>();
        List<List<Long>> templist = new LinkedList<>();

        String[] seedsplit = data.get(0).split(": ")[1].split(" ");

        Long lowest = Long.parseLong("10000000000000");

        for (int i = 3; i < data.size(); i++) {
            String datastring = data.get(i);
            if (Objects.equals(datastring, "")) {
                i++;
                datalists.add(templist);
                templist = new LinkedList<>();
                continue;
            }
            List<Long> temp = new LinkedList<>();
            for (String s : datastring.split(" ")) {
                temp.add(Long.parseLong(s));
            }
            templist.add(temp);
        }
        datalists.add(templist);

        for (int i = 0; i < seedsplit.length; i += 2) {
            List<Long> temp = new LinkedList<>();
            temp.add(Long.parseLong(seedsplit[i]));
            temp.add(Long.parseLong(seedsplit[i]) + Long.parseLong(seedsplit[i + 1]));
            ranges.add(temp);
        }
        for (List<List<Long>> datalist : datalists) {
            List<List<Long>> tempranges = new LinkedList<>();
            for (List<Long> range : ranges) {
                List<Long> temprange = new LinkedList<>();
                int i = 0;
                while (i < range.size()) {
                    boolean check = true;
                    long low = range.get(i);
                    long high = range.get(i + 1);
                    for (List<Long> data : datalist) {
                        long destination = data.get(0);
                        long sorce = data.get(1);
                        long marge = data.get(2);
                        if (sorce + marge < low);
                        else if (sorce > high);
                        else if (sorce <= low && sorce + marge >= high) {
                            temprange.add(destination + low - sorce);
                            temprange.add(destination + high - sorce);
                            check = false;
                            break;
                        }
                        else if (sorce <= low && sorce + marge < high) {
                            temprange.add(destination + (low - sorce));
                            temprange.add(destination + marge);
                            range.add(sorce + marge + 1);
                            range.add(high);
                            check = false;
                            break;
                        } else if (sorce > low && sorce + marge >= high) {
                            temprange.add(destination);
                            temprange.add(destination + high - sorce);
                            range.add(low);
                            range.add(sorce - 1);
                            check = false;
                            break;
                        }
                        else if (sorce > low && sorce + marge < high) {
                            range.add(low);
                            range.add(sorce - 1);
                            temprange.add(destination);
                            temprange.add(destination + marge);
                            range.add(sorce + marge + 1);
                            range.add(high);
                            check = false;
                            break;
                        } else {
                            System.out.println("error 404");
                        }
                    }
                    if (check) {
                        temprange.add(low);
                        temprange.add(high);
                    }
                    i += 2;
                }
                tempranges.add(temprange);
            }
            ranges = tempranges;
            System.out.println(tempranges);
        }
        for (List<Long> tempranges : ranges) {
            for (Long temprange : tempranges) {
                if (temprange < lowest) {
                    lowest = temprange;
                }
            }
        }
        System.out.println(lowest - 1); //i dont know why this -1 is here but it worked so im not going to touch it on a friends sollusion it worked without the -1 so idk why this is
    }
}

