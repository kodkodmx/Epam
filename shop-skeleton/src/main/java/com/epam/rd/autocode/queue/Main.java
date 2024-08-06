package com.epam.rd.autocode.queue;

public class Main {
    public static void main(String[] args) {
        // Test 2 setup
        Shop shop = new Shop(4); // Create shop with 4 cash boxes

        // Set up the initial state of cash boxes
        shop.setCashBoxState(0, CashBox.State.ENABLED);
        shop.setCashBoxState(1, CashBox.State.DISABLED);
        shop.setCashBoxState(2, CashBox.State.ENABLED);
        shop.setCashBoxState(3, CashBox.State.DISABLED);

        // Add buyers
        for (int i = 0; i < 8; i++) {
            shop.addBuyer(Buyer.nextBuyer());
        }

        // Perform the transaction step
        shop.tact();

        // Print state after test 2
        System.out.println("State after test2:");
        shop.print();

        // Test 6 setup
        shop = new Shop(2); // Create shop with 2 cash boxes

        // Set up the initial state of cash boxes
        shop.setCashBoxState(0, CashBox.State.ENABLED);
        shop.setCashBoxState(1, CashBox.State.ENABLED);

        // Add buyers
        for (int i = 0; i < 6; i++) {
            shop.addBuyer(Buyer.nextBuyer());
        }

        // Add one more buyer to test closing state handling
        shop.setCashBoxState(0, CashBox.State.IS_CLOSING);
        shop.addBuyer(Buyer.nextBuyer());

        // Perform the transaction step
        shop.tact();

        // Print state after test 6
        System.out.println("State after test6:");
        shop.print();
    }
}
