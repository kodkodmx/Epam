package com.epam.rd.autotasks.meetautocode;

import java.util.Scanner;

public class ElectronicWatch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int seconds = scanner.nextInt();
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int sec = seconds % 60;
        hours = hours > 23 ? hours % 24 : hours;
        String minutesS = String.format("%02d",minutes);
        String secS = String.format("%02d",sec);
        System.out.println(hours + ":" + minutesS + ":" + secS);
        scanner.close();
    }
    
}
