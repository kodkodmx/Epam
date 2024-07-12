package com.epam.learn.main;

import java.io.IOException;

public class InputProgram {
    public static void main(String[] args) {
        int x;
        try {
            //System.out.print("Enter a Int: ");
            x = System.in.read();
            System.out.println("Code = " + x + " x = " + (char)x);
        } catch (IOException e) {
           e.printStackTrace();
        }
    }
}