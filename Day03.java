//part 1
package org.thehuglio;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Main {

    private static final List<String> data = new Reader(new File("data.txt")).data;
    static List<String> numbers = Arrays.asList("1234567890".split(""));
    static String[][] numgrid = new String[data.size()][data.get(1).split("").length];

    public static void main(String[] args) {
        int tot = 0;
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).split("").length; j++) {
                String[] split = data.get(i).split("");
                if (numbers.contains(split[j])) {
                    numgrid[i][j] = split[j];
                } else if (split[j].equals(".")) {
                    continue;
                } else {
                    numgrid[i][j] = "T";
                }
            }
        }
        for (int i = 0; i < numgrid.length; i++) {
            for (int j = 0; j < numgrid[1].length; j++) {
                if (Objects.equals(numgrid[i][j], ".")) {
                    continue;
                } else if (numbers.contains(numgrid[i][j])) {
                    if (checksymbol(i,j)) {
                        int j1 = j;
                        StringBuilder number = new StringBuilder();
                        boolean begin = false;
                        while (true) {
                            if (!begin) {
                                if (j1 > 0) {
                                    if (numbers.contains(numgrid[i][j1 - 1])) {
                                        j1--;
                                    } else {
                                        begin = true;
                                    }
                                } else {
                                    begin = true;
                                }
                            }

                            else {
                                if (j1 < data.get(1).split("").length) {
                                    if (numbers.contains(numgrid[i][j1])) {
                                        number.append(numgrid[i][j1]);
                                        j1++;
                                    } else {
                                        j = j1;
                                        break;
                                    }
                                }else {
                                    j = j1;
                                    break;
                                }
                            }
                        }
                        tot += Integer.parseInt(number.toString());
                    }
                }
            }
        }
        System.out.println(tot);
    }

    private static boolean checksymbol(int i, int j) {
        int num = data.get(1).split("").length - 1;
        if (i > 0) {
            if (j > 0) {
                if (Objects.equals(numgrid[i - 1][j - 1], "T")) {
                    return true;
                }
            }
            if (Objects.equals(numgrid[i - 1][j], "T")) {
                return true;
            }
            if (j < num) {
                if (Objects.equals(numgrid[i - 1][j + 1], "T")) {
                    return true;
                }
            }
        }
        if (j > 0) {
            if (Objects.equals(numgrid[i][j - 1], "T")) {
                return true;
            }
        }
        if (j < num) {
            if (Objects.equals(numgrid[i][j + 1], "T")) {
                return true;
            }
        }
        if (i < data.size() - 1) {
            if (j > 0) {
                if (Objects.equals(numgrid[i + 1][j - 1], "T")) {
                    return true;
                }
            }
            if (Objects.equals(numgrid[i + 1][j], "T")) {
                return true;
            }
            if (j < num) {
                if (Objects.equals(numgrid[i + 1][j + 1], "T")) {
                    return true;
                }
            }
        }
        return false;
    }
}
//part 2
package org.thehuglio;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Main {

    private static final List<String> data = new Reader(new File("data.txt")).data;
    static List<String> numbers = Arrays.asList("1234567890".split(""));
    static String[][] numgrid = new String[data.size()][data.get(1).split("").length];
    public static void main(String[] args) {
        int tot = 0;
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).split("").length; j++) {
                String[] split = data.get(i).split("");
                if (numbers.contains(split[j])) {
                    numgrid[i][j] = split[j];
                } else if (split[j].equals("*")) {
                    numgrid[i][j] = "T";
                } else {
                    numgrid[i][j] = ".";
                }
            }
        }
        for (int i = 0; i < numgrid.length; i++) {
            for (int j = 0; j < numgrid[1].length; j++) {
                if (numgrid[i][j].equals("T")) {
                    tot += checkscore(i,j);
                }
            }
        }
        System.out.println(tot);
    }

    private static int checkscore(int i, int j) {
        int num = data.get(1).split("").length - 1;
        int num1 = 0;
        int num2 = 0;
        Boolean islastnumber = false;
        if (i > 0) {
            if (j > 0) {
                if (numbers.contains(numgrid[i - 1][j - 1])) {
                    islastnumber = true;
                    num1 = fullnumber(i - 1, j - 1);
                }
            }
            if (numbers.contains(numgrid[i - 1][j])) {
                if (!islastnumber) {
                    num1 = fullnumber(i - 1,j);
                }
                islastnumber = true;
            } else {islastnumber = false;}
            if (j < num && !islastnumber) {
                if (numbers.contains(numgrid[i - 1][j + 1])) {
                    if (num1 == 0) {
                        num1 = fullnumber(i - 1,j + 1);
                    }
                    else {
                        num2 = fullnumber(i - 1,j + 1);
                    }
                }
            }
        }
        if (j > 0) {
            if (numbers.contains(numgrid[i][j - 1])) {
                if (num1 == 0) {
                    num1 = fullnumber(i,j - 1);
                }
                else {
                    num2 = fullnumber(i,j - 1);
                }
            }
        }
        if (j < num) {
            if (numbers.contains(numgrid[i][j + 1])) {
                if (num1 == 0) {
                    num1 = fullnumber(i,j + 1);
                }
                else {
                    num2 = fullnumber(i,j + 1);
                }
            }
        }
        islastnumber = false;
        if (i < data.size() - 1) {
            if (j > 0) {
                if (numbers.contains(numgrid[i + 1][j - 1])) {
                    if (num1 == 0) {
                        num1 = fullnumber(i + 1,j - 1);
                    }
                    else {
                        num2 = fullnumber(i + 1,j - 1);
                    }
                    islastnumber = true;
                }
            }
            if (numbers.contains(numgrid[i + 1][j])) {
                if (!islastnumber) {
                    if (num1 == 0) {
                        num1 = fullnumber(i + 1,j);
                    }
                    else {
                        num2 = fullnumber(i + 1,j);
                    }
                }
                islastnumber = true;
            } else { islastnumber = false;}
            if (j < num && !islastnumber) {
                if (numbers.contains(numgrid[i + 1][j + 1])) {
                    if (num1 == 0) {
                        num1 = fullnumber(i + 1,j + 1);
                    }
                    else {
                        num2 = fullnumber(i + 1,j + 1);
                    }
                }
            }
        }
        return num1 * num2;
    }
    private static int fullnumber(int i, int j) {
        int j1 = j;
        StringBuilder number = new StringBuilder();
        boolean begin = false;
        while (true) {
            if (!begin) {
                if (j1 > 0) {
                    if (numbers.contains(numgrid[i][j1 - 1])) {
                        j1--;
                    } else {
                        begin = true;
                    }
                } else {
                    begin = true;
                }
            }

            else {
                if (j1 < data.get(1).split("").length) {
                    if (numbers.contains(numgrid[i][j1])) {
                        number.append(numgrid[i][j1]);
                        j1++;
                    } else {
                        break;
                    }
                }else {
                    break;
                }
            }
        }
        return Integer.parseInt(number.toString());
    }
}
