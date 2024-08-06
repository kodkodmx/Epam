package com.epam.rd.autocode.collection.array;

import java.util.Optional;

public class SimpleArrayListImpl implements SimpleArrayList {

    private static final int DEFAULT_CAPACITY = 10;
    private static final int FACTOR_MULTIPLIER = 2;
    private static final double INCREASE_LOAD_FACTOR = 0.75;
    private static final double DECREASE_LOAD_FACTOR = 0.4;

    private Object[] elements;
    private int size;

    public SimpleArrayListImpl() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public boolean add(Object element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (size+1 >= elements.length * INCREASE_LOAD_FACTOR) {
            increaseCapacity();
        }
        elements[size++] = element;
        return true;
    }

    @Override
    public int capacity() {
        return elements.length;
    }

    @Override
    public boolean decreaseCapacity() {
        int newCapacity = (int) (size * FACTOR_MULTIPLIER);
        System.out.println("newCapacity: " + newCapacity);
        if (newCapacity == capacity()) {
            return false;
        } else if (newCapacity < elements.length) {
            Object[] newElements = new Object[newCapacity];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
        return true;
    }

    

    @Override
    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return elements[index];
    }

    @Override
    public Optional<Object> remove(Object el) {
        if (el == null) {
            throw new NullPointerException();
        }

        for (int i = 0; i < size; i++) {
            if (elements[i].equals(el)) {
                Object removedElement = elements[i];
                for (int j = i; j < size - 1; j++) {
                    elements[j] = elements[j + 1];
                }
                elements[--size] = null;
                
                return Optional.of(removedElement);
            }
        }
        return Optional.empty();
    }




    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(elements[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    private void increaseCapacity() {
        int newCapacity = (int) (elements.length * INCREASE_LOAD_FACTOR * FACTOR_MULTIPLIER);
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
    }
}
