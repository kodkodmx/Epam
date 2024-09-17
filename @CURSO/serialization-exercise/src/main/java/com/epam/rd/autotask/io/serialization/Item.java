package com.epam.rd.autotask.io.serialization;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Item implements Serializable {
    private Long id;
    private String name;
    private String description;
    private List<ItemCharacteristic> characteristics;
    private BigDecimal price;

    public Item(Long id, String name, String description, List<ItemCharacteristic> characteristics, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.characteristics = characteristics;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ItemCharacteristic> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<ItemCharacteristic> characteristics) {
        this.characteristics = characteristics;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Item item = (Item) obj;
        return Objects.equals(id, item.id) &&
                Objects.equals(name, item.name) &&
                Objects.equals(description, item.description) &&
                Objects.equals(characteristics, item.characteristics) &&
                Objects.equals(price, item.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, characteristics, price);
    }
}
