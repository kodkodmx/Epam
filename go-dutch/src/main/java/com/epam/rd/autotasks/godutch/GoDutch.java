package com.epam.rd.autotasks.godutch;
import java.util.Scanner;

public class GoDutch {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int bill = scanner.nextInt();
            if (bill < 0) {
                System.out.println("Bill total amount cannot be negative");
                return;
            }
            int friends = scanner.nextInt();
            if (friends <= 0) {
                System.out.println("Number of friends cannot be negative or zero");
                return;
            }
            double total = bill * 1.1;
            int result = (int)total/friends;
            System.out.println(result);
        }


    }
}
