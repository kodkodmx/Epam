package com.epam.rd.autotasks;

import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        NewPostOffice office = new NewPostOffice();
        
        System.out.println("Adding boxes...");
        try {
            office.addBox("Alice", "Bob", 1.5, 0.1, 50);
            office.addBox("Charlie", "Dave", 2.0, 0.2, 100);
            office.addBox("Eve", "Frank", 0.5, 0.05, 20);
            System.out.println("Boxes added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding boxes: " + e.getMessage());
        }

        System.out.println("\nAll boxes:");
        for (Box box : office.getListBox()) {
            System.out.println("Sender: " + box.getAddresser() + ", Recipient: " + box.getRecipient() +
                    ", Weight: " + box.getWeight() + ", Volume: " + box.getVolume() +
                    ", Cost: " + box.getCost());
        }

        System.out.println("\nDelivering boxes to Dave...");
        Collection<Box> deliveredBoxes = office.deliveryBoxToRecipient("Dave");
        System.out.println("Delivered boxes:");
        for (Box box : deliveredBoxes) {
            System.out.println("Sender: " + box.getAddresser() + ", Weight: " + box.getWeight() +
                    ", Volume: " + box.getVolume() + ", Cost: " + box.getCost());
        }

        System.out.println("\nAll boxes after delivery:");
        for (Box box : office.getListBox()) {
            System.out.println("Sender: " + box.getAddresser() + ", Recipient: " + box.getRecipient() +
                    ", Weight: " + box.getWeight() + ", Volume: " + box.getVolume() +
                    ", Cost: " + box.getCost());
        }

        System.out.println("\nApplying 10% discount to all boxes...");
        office.declineCostOfBox(10);
        System.out.println("All boxes after 10% discount:");
        for (Box box : office.getListBox()) {
            System.out.println("Sender: " + box.getAddresser() + ", Recipient: " + box.getRecipient() +
                    ", Weight: " + box.getWeight() + ", Volume: " + box.getVolume() +
                    ", Cost: " + box.getCost());
        }
    }
}
