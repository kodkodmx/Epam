package com.epam.rd.autotasks.max;

public class MaxMethod {
    public static int max(int[] values) {

        int max = values[0]; // or Integer.MIN_VALUE and i = 
        for (int i = 1; i < values.length; i++) {
            if (values[i] > max) {
                max = values[i];
            }
        }
        return max;
    }
}