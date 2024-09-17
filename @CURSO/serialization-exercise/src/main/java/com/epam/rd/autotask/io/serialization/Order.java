package com.epam.rd.autotask.io.serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L; // Ensure consistent serialization
    private Long id;
    private transient BigDecimal total; // Transient field to avoid serialization
    private Map<Item, Integer> items;

    public Order(Long id, Map<Item, Integer> items) {
        this.id = id;
        this.items = items;
        calculateTotal(); // Calculate total at the time of order creation
    }

    public Long getId() {
        return id;
    }

    public Map<Item, Integer> getItems() {
        return items;
    }

    public BigDecimal getTotal() {
        if (total == null) {
            calculateTotal(); // Recalculate total if needed
        }
        return total;
    }

    private void calculateTotal() {
        if (items != null && !items.isEmpty()) {
            total = items.entrySet().stream()
                    .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } else {
            total = BigDecimal.ZERO; // Set total to zero if no items
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        // Do not write `total` to the stream
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        // Recalculate `total` after deserialization
        calculateTotal();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        return items != null ? items.equals(order.items) : order.items == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (items != null ? items.hashCode() : 0);
        return result;
    }
}
