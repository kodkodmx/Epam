package edu.epam.fop.lambdas.calculator;

import edu.epam.fop.lambdas.insurance.Accommodation;
import edu.epam.fop.lambdas.insurance.Currency;
import edu.epam.fop.lambdas.insurance.RepeatablePayment;

import java.math.BigInteger;
import java.time.Period;
import java.util.Optional;

public final class AccommodationInsurancePolicies {

  private AccommodationInsurancePolicies() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  public static InsuranceCalculator<Accommodation> rentDependentInsurance(BigInteger divider) {
    return accommodation -> {
      if (accommodation == null || accommodation.rent().isEmpty()) {
        return Optional.empty();
      }

      RepeatablePayment rent = accommodation.rent().get();
      if (rent.currency() != Currency.USD || !rent.unit().equals(Period.ofMonths(1)) || rent.amount().compareTo(BigInteger.ZERO) <= 0) {
        return Optional.empty();
      }

      BigInteger coefficientValue = rent.amount().multiply(BigInteger.valueOf(100)).divide(divider);
      int coefficient = coefficientValue.intValue();
      if (coefficient > 100) {
        return Optional.of(InsuranceCoefficient.MAX);
      }
      return Optional.of(new InsuranceCoefficient(coefficient));
    };
  }

  public static InsuranceCalculator<Accommodation> priceAndRoomsAndAreaDependentInsurance(BigInteger priceThreshold, int roomsThreshold, BigInteger areaThreshold) {
    return accommodation -> {
      if (accommodation == null || accommodation.price() == null || accommodation.rooms() == null || accommodation.area() == null) {
        return Optional.of(InsuranceCoefficient.MIN);
      }

      if (accommodation.price().compareTo(priceThreshold) >= 0 &&
          accommodation.rooms() >= roomsThreshold &&
          accommodation.area().compareTo(areaThreshold) >= 0) {
        return Optional.of(InsuranceCoefficient.MAX);
      }

      return Optional.of(InsuranceCoefficient.MIN);
    };
  }
}