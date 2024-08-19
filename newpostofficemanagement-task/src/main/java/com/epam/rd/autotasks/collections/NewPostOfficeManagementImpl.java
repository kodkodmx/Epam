package com.epam.rd.autotasks.collections;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class NewPostOfficeManagementImpl implements NewPostOfficeManagement {
    private List<Box> parcels;

    public NewPostOfficeManagementImpl(Collection<Box> boxes) {
        if (boxes == null) {
            throw new NullPointerException("Collection cannot be null");
        }
        // Check if any element in the collection is null
        for (Box box : boxes) {
            if (box == null) {
                throw new NullPointerException("Collection contains null values");
            }
        }
        // Initialize the list
        parcels = new ArrayList<>(boxes);
    }

    @Override
    public Optional<Box> getBoxById(int id) {
        parcels.sort(Comparator.comparingInt(Box::getId));
        Box searchBox = new Box(null, null, 0, 0, BigDecimal.ZERO, null, 0) {
            @Override
            public int getId() {
                return id;
            }
        };
        int index = Collections.binarySearch(parcels, searchBox, Comparator.comparingInt(Box::getId));
        return index >= 0 ? Optional.of(parcels.get(index)) : Optional.empty();
    }

    @Override
    public String getDescSortedBoxesByWeight() {
        parcels.sort(Comparator.comparingDouble(Box::getWeight).reversed());
        StringBuilder result = new StringBuilder();
        for (Box box : parcels) {
            result.append(box).append("\n");
        }
        return result.toString().trim();  // Remove the trailing newline
    }

    @Override
    public String getAscSortedBoxesByCost() {
        parcels.sort(Comparator.comparing(Box::getCost));
        StringBuilder result = new StringBuilder();
        for (Box box : parcels) {
            result.append(box).append("\n");
        }
        return result.toString().trim();  // Remove the trailing newline
    }

    @Override
    public List<Box> getBoxesByRecipient(String recipient) {
        if (recipient == null) {
            throw new NullPointerException("Recipient cannot be null");
        }
        parcels.sort(Comparator.comparing(Box::getRecipient));
        List<Box> result = new ArrayList<>();
        Box searchBox = new Box(null, recipient, 0, 0, BigDecimal.ZERO, null, 0) {
            @Override
            public String getRecipient() {
                return recipient;
            }
        };
        int index = Collections.binarySearch(parcels, searchBox, Comparator.comparing(Box::getRecipient));
        if (index >= 0) {
            int start = index;
            while (start > 0 && parcels.get(start - 1).getRecipient().equals(recipient)) {
                start--;
            }
            int end = index;
            while (end < parcels.size() - 1 && parcels.get(end + 1).getRecipient().equals(recipient)) {
                end++;
            }
            for (int i = start; i <= end; i++) {
                result.add(parcels.get(i));
            }
        }
        return result;
    }
}
