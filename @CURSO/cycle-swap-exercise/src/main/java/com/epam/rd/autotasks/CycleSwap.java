package com.epam.rd.autotasks;

import java.util.Arrays;

class CycleSwap {
    static void cycleSwap(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1];
            array[array.length - 1] = temp;
        }


    }

    static void cycleSwap(int[] array, int shift) {
        for (int j = 0; j < shift; j++) {
            for (int i = 0; i < array.length; i++) {
                int temp = array[i];
                array[i] = array[array.length - 1];
                array[array.length - 1] = temp;
            }
        }
    }
}
