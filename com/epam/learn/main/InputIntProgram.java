package com.epam.learn.main;

import java.util.Scanner;

public class InputIntProgram {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a Int: ");
        int number = scan.nextInt();
        System.out.println(number);
        scan.close();
    }
}