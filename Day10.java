//3 files per part
//part 1
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

package org.thehuglio;

import java.util.List;
import java.util.Objects;

public class Pipes {

    String[][] grid;
    boolean[][] bgrid;
    int startx,starty = 0;

    public Pipes(List<String> data) {
        grid = new String[data.size()][data.get(1).length()];
        bgrid = new boolean[data.size()][data.get(1).length()];
        for (int i = 0; i < data.size(); i++) {
            String[] datas = data.get(i).split("");
            for (int j = 0; j < datas.length; j++) {
                grid[i][j] = datas[j];
                if (Objects.equals(datas[j], "S")) {
                    startx = j; starty = i;
                }
            }
        }
    }
    public Location change(Location location) {
        int i = location.getY();
        int j = location.getX();
        String output = location.getOutput();
        if (Objects.equals(output, "N")) {
            i -= 1;
        } else if (Objects.equals(output, "E")) {
            j += 1;
        } else if (Objects.equals(output, "W")) {
            j -= 1;
        } else {
            i += 1;
        }
        String s = grid[i][j];
        location.setX(j);
        location.setY(i);
        if (s.equals("L")) {
            if (output.equals("W")) {
                location.setOutput("N");
            } else if (output.equals("S")) {
                location.setOutput("E");
            }
        } else if (s.equals("J")) {
            if (output.equals("S")) {
                location.setOutput("W");
            } else if (output.equals("E")) {
                location.setOutput("N");
            }
        } else if (s.equals("7")) {
            if (output.equals("E")) {
                location.setOutput("S");
            } else if (output.equals("N")) {
                location.setOutput("W");
            }
        } else if (s.equals("F")) {
            if (output.equals("W")) {
                location.setOutput("S");
            } else if (output.equals("N")) {
                location.setOutput("E");
            }
        }
        return location;
    }

    public int getStartx() {
        return startx;
    }

    public int getStarty() {
        return starty;
    }
    public Location[] setstartlocations() {
        Location[] locations = new Location[]{new Location(startx,starty,"Null"), new Location(startx,starty,"Null")};
        int i = 0;
        if (starty + 1 < grid.length) {
            if (grid[starty - 1][startx].equals("|") || grid[starty - 1][startx].equals("7") || grid[starty - 1][startx].equals("F")) {
                locations[i].setOutput("N");
                i++;
            }
        } if (startx + 1 < grid[1].length) {
            if (grid[starty][startx + 1].equals("J") || grid[starty][startx + 1].equals("7") || grid[starty][startx + 1].equals("-")) {
                locations[i].setOutput("E");
                i++;
            }
        } if (startx - 1 >= 0) {
            if (grid[starty][startx - 1].equals("F") || grid[starty][startx - 1].equals("L") || grid[starty][startx - 1].equals("-")) {
                locations[i].setOutput("W");
                i++;
            }
        }
        if (starty - 1 >= 0) {
            if (grid[starty + 1][startx].equals("|") || grid[starty + 1][startx].equals("L") || grid[starty + 1][startx].equals("J")) {
                locations[i].setOutput("S");
                i++;
            }
        } if (i < 2) {
            System.out.println("error");
        }
        return locations;
    }
}

package org.thehuglio;

public class Location {
    private int x;
    private int y;
    private String output;
    public Location(int x,int y,String output) {
        this.x = x;
        this.y = y;
        this.output = output;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}

//part 2
