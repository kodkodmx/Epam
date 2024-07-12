package com.epam.learn.main;

import java.util.Scanner;

public class InputStringProgram {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //System.out.print("Enter a String: ");
        String input = scan.next();
        System.out.println("hello " + input);
        scan.close();
    }
}