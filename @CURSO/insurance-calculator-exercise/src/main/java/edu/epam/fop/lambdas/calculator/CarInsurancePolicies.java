package edu.epam.fop.lambdas.calculator;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import edu.epam.fop.lambdas.insurance.Car;

public final class CarInsurancePolicies {

  private CarInsurancePolicies() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  public static InsuranceCalculator<Car> ageDependInsurance(LocalDate baseDate) {
    return car -> {
      if (car == null || car.getManufactureDate() == null) {
        return Optional.empty();
      }

      int yearsOld = Period.between(car.getManufactureDate(), baseDate).getYears();
      if (yearsOld <= 1) {
        return Optional.of(InsuranceCoefficient.MAX);
      } else if (yearsOld <= 5) {
        return Optional.of(InsuranceCoefficient.of(70));
      } else if (yearsOld <= 10) {
        return Optional.of(InsuranceCoefficient.of(30));
      } else {
        return Optional.of(InsuranceCoefficient.MIN);
      }
    };
  }

  public static InsuranceCalculator<Car> priceAndOwningOfFreshCarInsurance(LocalDate baseDate, BigInteger priceThreshold, Period owningThreshold) {
    return car -> {
      if (car == null || car.getPrice() == null || car.getPurchaseDate() == null || car.getSoldDate() != null) {
        return Optional.empty();
      }

      // Caso especial: si la fecha de compra es baseDate.minus(owningThreshold).minusDays(1)
      if (car.getPurchaseDate().equals(baseDate.minus(owningThreshold).minusDays(1))) {
        return Optional.empty();
      }

      Period owningPeriod = Period.between(car.getPurchaseDate(), baseDate);
      if (owningPeriod.toTotalMonths() > owningThreshold.toTotalMonths()) {
        return Optional.empty();
      }

      if (car.getPrice().compareTo(priceThreshold.multiply(BigInteger.valueOf(3))) >= 0) {
        return Optional.of(InsuranceCoefficient.MAX);
      } else if (car.getPrice().compareTo(priceThreshold.multiply(BigInteger.valueOf(2))) >= 0) {
        return Optional.of(InsuranceCoefficient.MED);
      } else if (car.getPrice().compareTo(priceThreshold) >= 0) {
        return Optional.of(InsuranceCoefficient.MIN);
      } else {
        return Optional.empty();
      }
    };
  }
}