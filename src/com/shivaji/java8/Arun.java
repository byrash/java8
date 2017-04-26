package com.shivaji.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Arun {
    private static final String BAC24 = "BAC24";
    private static final String BAC13 = "BAC13";
    private static final String PRI24 = "PRI24";
    private static final String PRI13 = "PRI13";

    public static void main(String[] args) {

        List<String> providedGantries = new ArrayList<String>();
        // providedGantries.add(PRI13);
        // providedGantries.add(PRI24);
        providedGantries.add(BAC13);
        // providedGantries.add(BAC24);
        providedGantries.add("1A");
        List<String> toExcemptGantries = getExemptableGantries(providedGantries);
        System.out.println(toExcemptGantries);
    }

    private static List<String> getExemptableGantries(List<String> providedGantries) {
        if (isAllMatched(providedGantries)) {
            providedGantries.remove(PRI13);
            providedGantries.remove(PRI24);
        } else if (isAnyMatched(providedGantries)) {
            providedGantries.retainAll(Arrays.asList(PRI13, PRI24, BAC13, BAC24));
        }
        return providedGantries;
    }

    public static boolean isAnyMatched(List<String> gantries) {
        for (String gantry : gantries) {
            if (gantry.equals(BAC13) || gantry.equals(BAC24) || gantry.equals(PRI13) || gantry.equals(PRI24)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAllMatched(List<String> gantries) {
        boolean isAllMatched = true;
        for (String gantry : gantries) {
            if (gantry.equals(BAC13) || gantry.equals(BAC24) || gantry.equals(PRI13) || gantry.equals(PRI24)) {
                isAllMatched &= true;
            } else {
                isAllMatched &= false;
            }
        }
        return isAllMatched;
    }

}
