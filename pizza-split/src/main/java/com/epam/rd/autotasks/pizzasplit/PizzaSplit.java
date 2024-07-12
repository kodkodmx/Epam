package com.epam.rd.autotasks.pizzasplit;

import java.util.Scanner;

public class PizzaSplit {
    static int pizzas;
    public static void main(String[] args) {
        //Write a program, reading number of people and number of pieces per pizza and then
        //printing minimum number of pizzas to order to split all the pizzas equally and with no remainder

        Scanner scanner = new Scanner(System.in);
        int people = scanner.nextInt();
        int pieces = scanner.nextInt();
        scanner.close();

        pizzas = (people * pieces) / GCF(people, pieces) / pieces;
        System.out.println(pizzas);

    }
        public static int GCF(int a, int b) {
            if (b == 0) {
                return a;
            } else {
                return (GCF(b, a % b));
            }
        } 
    }
