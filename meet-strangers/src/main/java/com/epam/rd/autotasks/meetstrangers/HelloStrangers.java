package com.epam.rd.autotasks.meetstrangers;

import java.io.IOException;
import java.util.Scanner;

public class HelloStrangers {
    public static void main(String[] args) throws IOException {
        //Write a program, asks for a number - amount of strangers to meet.
        //Then reads stranger names line by line and prints line by line "Hello, ...".

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        if (n < 0) {
            System.out.println("Seriously? Why so negative?");
        }

        else if (n == 0) {
            System.out.println("Oh, it looks like there is no one here");
        }

        else {
            scanner.nextLine();
            for (int i = 0; i < n; i++) {
                System.out.println("Hello, " + scanner.nextLine());
            }
        }
    }
}
