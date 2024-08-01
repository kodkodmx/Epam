package com.epam.rd.autotasks;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collection;

public class NewPostOffice {
    private final Collection<Box> listBox;
    private static final int COST_KILOGRAM = 5;
    private static final int COST_CUBIC_METER = 100;
    private static final double COEFFICIENT = 0.5;

    public NewPostOffice() {
        listBox = new ArrayList<>();
    }

    public Collection<Box> getListBox() {
        return new ArrayList<>(listBox);
    }

    static BigDecimal calculateCostOfBox(double weight, double volume, int value) {
        BigDecimal costWeight = BigDecimal.valueOf(weight)
                .multiply(BigDecimal.valueOf(COST_KILOGRAM), MathContext.DECIMAL64);
        BigDecimal costVolume = BigDecimal.valueOf(volume)
                .multiply(BigDecimal.valueOf(COST_CUBIC_METER), MathContext.DECIMAL64);
        return costVolume.add(costWeight)
                .add(BigDecimal.valueOf(COEFFICIENT * value), MathContext.DECIMAL64);
    }

    public boolean addBox(String addresser, String recipient, double weight, double volume, int value) {
        if (addresser == null || addresser.trim().isEmpty() || recipient == null || recipient.trim().isEmpty()) {
            throw new IllegalArgumentException("Sender and recipient must be valid.");
        }
        if (weight < 0.5 || weight > 20.0 || volume <= 0 || volume > 0.25 || value <= 0) {
            throw new IllegalArgumentException("Invalid weight, volume, or value.");
        }

        BigDecimal cost = calculateCostOfBox(weight, volume, value);
        Box newBox = new Box(addresser, recipient, weight, volume);
        newBox.setCost(cost);
        return listBox.add(newBox);
    }

    public Collection<Box> deliveryBoxToRecipient(String recipient) {
        Collection<Box> deliveredBoxes = new ArrayList<>();
        for (Box box : listBox) {
            if (box.getRecipient().equalsIgnoreCase(recipient)) {
                deliveredBoxes.add(box);
            }
        }
        listBox.removeAll(deliveredBoxes);
        return deliveredBoxes;
    }

    public void declineCostOfBox(double percent) {
        BigDecimal decline = BigDecimal.valueOf(1).subtract(BigDecimal.valueOf(percent / 100));
        for (Box box : listBox) {
            BigDecimal newCost = box.getCost().multiply(decline, MathContext.DECIMAL64);
            box.setCost(newCost);
        }
    }
}
