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
