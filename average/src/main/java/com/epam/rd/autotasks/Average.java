package com.epam.rd.autotasks;

import java.util.Scanner;

public class Average {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Use Scanner methods to read input
        int sum = 0;
        int count = 0;
        while (true) {
            int value = scanner.nextInt();
            if (value == 0) {
                break;
            }
            sum += value;
            count++;
        }

        scanner.close();
        System.out.println((int) sum / count);

    }

}