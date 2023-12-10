package org.thehuglio;

import java.io.File;
import java.util.*;

public class Main {

    private static final List<String> data = new Reader(new File("data.txt")).data;

    public static void main(String[] args) {
        Pipes pipes = new Pipes(data);
        Location[] locations = pipes.setstartlocations();
        Location location1 = locations[0];
        Location location2 = locations[1];
        int tot = 1;
        location1 = pipes.change(location1);
        location2 = pipes.change(location2);
        while(location1.getX() != location2.getX() || location1.getY() != location2.getY()) {
            tot++;
            location1 = pipes.change(location1);
            location2 = pipes.change(location2);
        }
        System.out.println(tot);
    }
}