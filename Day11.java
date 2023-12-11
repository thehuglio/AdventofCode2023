//this version consists of 3 clases, part 1 is made with the change inticated by a notation in the code
package org.thehuglio;

import java.io.File;
import java.util.*;

public class Main {

    private static final List<String> data = new Reader(new File("data.txt")).data;
    public static long expantionsize = 999999; //for part 1 change this to 1
    public static void main(String[] args) {
        Universe universe = new Universe(data);
        Starlocations starlocations = universe.getStarlocations();
        long tot = 0;
        for (int i = 0; i < starlocations.size() - 1; i++) {
            for (int j = i + 1; j < starlocations.size(); j++) {
                System.out.println(starlocations.distance(i,j));
                tot += starlocations.distance(i,j);
            }
        }
        System.out.println(tot);
    }
}

package org.thehuglio;

import java.util.LinkedList;
import java.util.List;
import static org.thehuglio.Main.expantionsize;
public class Starlocations {
    private List<Long> x = new LinkedList<>();
    private List<Long> y = new LinkedList<>();

    public void addstarlocation(long x, long y) {
        this.y.add(y);
        this.x.add(x);
    }
    public void expantionx(long loc) {
        List<Long> temp = new LinkedList<>();
        for (long x : this.x) {
            if (x > loc) {
                temp.add(x + expantionsize);
                continue;
            }
            temp.add(x);
        }
        this.x = temp;
    }
    public void expantiony(long loc) {
        List<Long> temp = new LinkedList<>();
        for (long y : this.y) {
            if (y > loc) {
                temp.add(y + expantionsize);
                continue;
            }
            temp.add(y);
        }
        this.y = temp;
    }
    public boolean xcontainsstar(long x) {
        return this.x.contains(x);
    }
    public boolean ycontainsstar(long y) {
        return this.y.contains(y);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < x.size(); i++) {
            stringBuilder.append("[").append(x.get(i)).append(",").append(y.get(i)).append("]" + "\n");
        }
        return stringBuilder.toString();
    }
    public int size() {
        return this.x.size();
    }
    public long distance(int i,int j) {
        long x = Math.abs(this.x.get(i) - this.x.get(j));
        long y = Math.abs(this.y.get(i) - this.y.get(j));
        return x+y;
    }
}

package org.thehuglio;

import java.util.*;

import static org.thehuglio.Main.expantionsize;

public class Universe {
    private final Starlocations starlocations = new Starlocations();
    public Universe(List<String> data) {
        for (int i = 0; i < data.size(); i++) {
            String[] datas = data.get(i).split("");
            for (int j = 0; j < datas.length; j++) {
                if (datas[j].equals("#")) {
                    starlocations.addstarlocation(j,i);
                }
            }
        }
        long j = 0;
        for (long i = 0; i < starlocations.size() + j; i++) {
            if (!starlocations.xcontainsstar(i)) {
                starlocations.expantionx(i);
                i += expantionsize;
                j += expantionsize;
            }
        }
        j = 0;
        for (long i = 0; i < starlocations.size() + j; i++) {
            if (!starlocations.ycontainsstar(i)) {
                starlocations.expantiony(i);
                i += expantionsize;
                j += expantionsize;
            }
        }
        System.out.println(starlocations);
    }

    public Starlocations getStarlocations() {
        return starlocations;
    }
}
