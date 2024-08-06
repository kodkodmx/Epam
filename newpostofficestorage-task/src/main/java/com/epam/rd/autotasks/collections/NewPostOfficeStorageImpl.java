package com.epam.rd.autotasks.collections;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class NewPostOfficeStorageImpl implements NewPostOfficeStorage {
    private final List<Box> parcels;

    public NewPostOfficeStorageImpl() {
        this.parcels = new LinkedList<>();
    }

    public NewPostOfficeStorageImpl(Collection<Box> boxes) {
        if (boxes == null || boxes.contains(null)) {
            throw new NullPointerException("Collection cannot be null or contain null values");
        }
        this.parcels = new LinkedList<>(boxes);
    }

    @Override
    public boolean acceptBox(Box box) {
        if (box == null) {
            throw new NullPointerException("Box cannot be null");
        }
        return parcels.add(box);
    }

    @Override
    public boolean acceptAllBoxes(Collection<Box> boxes) {
        if (boxes == null || boxes.contains(null)) {
            throw new NullPointerException("Collection cannot be null or contain null values");
        }
        try {
            return parcels.addAll(boxes);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean carryOutBoxes(Collection<Box> boxes) {
        if (boxes == null) {
            throw new NullPointerException("Collection cannot be null");
        }

        // Verificar si alguna caja en la colección es nula
        for (Box box : boxes) {
            if (box == null) {
                throw new NullPointerException("Collection cannot contain null values");
            }
        }

        boolean changed = false;
        Iterator<Box> iterator = boxes.iterator();
        while (iterator.hasNext()) {
            Box box = iterator.next();
            if (parcels.remove(box)) {
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public List<Box> carryOutBoxes(Predicate<Box> predicate) {
        if (predicate == null) {
            throw new NullPointerException("Predicate cannot be null");
        }
        List<Box> removedBoxes = new LinkedList<>();
        Iterator<Box> iterator = parcels.iterator();
        while (iterator.hasNext()) {
            Box box = iterator.next();
            if (predicate.test(box)) {
                removedBoxes.add(box);
                iterator.remove();
            }
        }
        return removedBoxes;
    }

    @Override
    public List<Box> getAllWeightLessThan(double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be greater than zero");
        }
        return searchBoxes(new Predicate<Box>() {
            @Override
            public boolean test(Box box) {
                return box.getWeight() < weight;
            }
        });
    }

    @Override
    public List<Box> getAllCostGreaterThan(BigDecimal cost) {
        if (cost == null) {
            throw new NullPointerException("Cost cannot be null");
        }
        if (cost.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Cost must be greater than zero");
        }
        return searchBoxes(new Predicate<Box>() {
            @Override
            public boolean test(Box box) {
                return box.getCost().compareTo(cost) > 0;
            }
        });
    }

    @Override
    public List<Box> getAllVolumeGreaterOrEqual(double volume) {
        if (volume < 0) {
            throw new IllegalArgumentException("Volume must be greater than or equal to zero");
        }
        return searchBoxes(new Predicate<Box>() {
            @Override
            public boolean test(Box box) {
                return box.getVolume() >= volume;
            }
        });
    }

    @Override
    public List<Box> searchBoxes(Predicate<Box> predicate) {
        if (predicate == null) {
            throw new NullPointerException("Predicate cannot be null");
        }
        List<Box> result = new LinkedList<>();
        for (Box box : parcels) {
            if (predicate.test(box)) {
                result.add(box);
            }
        }
        return result;
    }

    @Override
    public void updateOfficeNumber(Predicate<Box> predicate, int newOfficeNumber) {
        if (predicate == null) {
            throw new NullPointerException("Predicate cannot be null");
        }
        if (newOfficeNumber <= 0) {
            throw new IllegalArgumentException("Office number must be greater than zero");
        }
        for (Box box : parcels) {
            if (predicate.test(box)) {
                box.setOfficeNumber(newOfficeNumber);
            }
        }
    }
}
