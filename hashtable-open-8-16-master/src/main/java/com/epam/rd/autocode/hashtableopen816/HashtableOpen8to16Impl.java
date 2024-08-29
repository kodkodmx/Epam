package com.epam.rd.autocode.hashtableopen816;

public class HashtableOpen8to16Impl implements HashtableOpen8to16 {
    private static final int INITIAL_CAPACITY = 8;
    private static final int MAX_CAPACITY = 16;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;
    private static final int MIN_CAPACITY = 2;
    
    private Entry[] table;
    private int size;

    public HashtableOpen8to16Impl() {
        table = new Entry[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public void insert(int key, Object value) {
        if (size >= table.length * LOAD_FACTOR_THRESHOLD) {
            if (table.length == MAX_CAPACITY) {
                throw new IllegalStateException("Cannot insert; maximum capacity reached.");
            }
            resize(table.length * 2);
        }

        int index = hash(key);
        while (table[index] != null && table[index].isActive && table[index].key != key) {
            index = (index + 1) % table.length;
        }

        if (table[index] == null || !table[index].isActive) {
            size++;
        }
        table[index] = new Entry(key, value);
    }

    @Override
    public Object search(int key) {
        int index = hash(key);
        while (table[index] != null) {
            if (table[index].isActive && table[index].key == key) {
                return table[index].value;
            }
            index = (index + 1) % table.length;
        }
        return null;
    }

    @Override
    public void remove(int key) {
        int index = hash(key);
        while (table[index] != null) {
            if (table[index].isActive && table[index].key == key) {
                table[index].isActive = false;
                size--;
                // Reducción de capacidad si es necesario
                if (size > 0 && size <= table.length / 4 && table.length > MIN_CAPACITY) {
                    int newCapacity = Math.max(MIN_CAPACITY, table.length / 2);
                    resize(newCapacity);
                }
                return;
            }
            index = (index + 1) % table.length;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int[] keys() {
        int[] keys = new int[size];
        int i = 0;
        for (Entry entry : table) {
            if (entry != null && entry.isActive) {
                keys[i++] = entry.key;
            }
        }
        return keys;
    }

    private int hash(int key) {
        return Math.abs(key % table.length);
    }

    private void resize(int newCapacity) {
        if (newCapacity > MAX_CAPACITY) {
            throw new IllegalStateException("Cannot insert; maximum capacity reached.");
        }

        Entry[] oldTable = table;
        table = new Entry[newCapacity];
        size = 0;

        for (Entry entry : oldTable) {
            if (entry != null && entry.isActive) {
                insert(entry.key, entry.value);  // Reinsertar elementos activos
            }
        }
    }

    // Clase interna para representar una entrada en la tabla hash
    private static class Entry {
        int key;
        Object value;
        boolean isActive;

        Entry(int key, Object value) {
            this.key = key;
            this.value = value;
            this.isActive = true;
        }
    }
}
