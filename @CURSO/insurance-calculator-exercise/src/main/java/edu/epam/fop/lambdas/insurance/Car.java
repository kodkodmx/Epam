package edu.epam.fop.lambdas.insurance;

import java.math.BigInteger;
import java.time.LocalDate;

public final class Car implements Subject {
  private String model;
  private BigInteger price;
  private LocalDate manufactureDate;
  private LocalDate purchaseDate;
  private LocalDate soldDate;

  public Car() {}

  public Car model(String model) {
    this.model = model;
    return this;
  }

  public Car price(BigInteger price) {
    this.price = price;
    return this;
  }

  public Car manufactureDate(LocalDate manufactureDate) {
    this.manufactureDate = manufactureDate;
    return this;
  }

  public Car purchaseDate(LocalDate purchaseDate) {
    this.purchaseDate = purchaseDate;
    return this;
  }

  public Car soldDate(LocalDate soldDate) {
    this.soldDate = soldDate;
    return this;
  }

  public String getModel() {
    return model;
  }

  public BigInteger getPrice() {
    return price;
  }

  public LocalDate getManufactureDate() {
    return manufactureDate;
  }

  public LocalDate getPurchaseDate() {
    return purchaseDate;
  }

  public LocalDate getSoldDate() {
    return soldDate;
  }
}