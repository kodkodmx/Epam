package com.epam.rd.autotasks.sequence;

import java.util.Scanner;

public class FindMaxInSeq {
    public static int max() {

        Scanner scanner = new Scanner(System.in);
        int maxValue = Integer.MIN_VALUE; 
        while (true) {
            int value = scanner.nextInt();
            if (value == 0) {
                break; 
            }
            if (value > maxValue) {
                maxValue = value; 
            }
        }
        scanner.close();
        return maxValue;
    }
    public static void main(String[] args) {
        System.out.println(max());
    }
}
