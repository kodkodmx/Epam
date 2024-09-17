package edu.epam.fop.lambdas.calculator;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

import edu.epam.fop.lambdas.insurance.Accommodation;
import edu.epam.fop.lambdas.insurance.Currency;
import edu.epam.fop.lambdas.insurance.Employment;
import edu.epam.fop.lambdas.insurance.Injury;
import edu.epam.fop.lambdas.insurance.Person;

public final class PersonInsurancePolicies {

  private PersonInsurancePolicies() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  public static InsuranceCalculator<Person> childrenDependent(int childrenCountThreshold) {
    return person -> {
      if (person == null || person.family().isEmpty() || person.family().get().children() == null) {
        return Optional.of(InsuranceCoefficient.MIN);
      }

      int childrenCount = person.family().get().children().size();
      int coefficientValue = Math.min(100, childrenCount * 100 / childrenCountThreshold);
      return Optional.of(new InsuranceCoefficient(coefficientValue));
    };
  }

  public static InsuranceCalculator<Person> employmentDependentInsurance(BigInteger salaryThreshold, Set<Currency> currencies) {
    return person -> {
      if (person == null || person.employmentHistory() == null || person.employmentHistory().size() < 4 ||
          person.account() == null || person.injuries() != null || person.accommodations() == null || person.accommodations().isEmpty()) {
        return Optional.empty();
      }

      Optional<Employment> lastEmployment = person.employmentHistory().stream().max(Comparator.comparing(Employment::startDate));
      if (lastEmployment.isEmpty() || lastEmployment.get().endDate().isPresent() ||
          !lastEmployment.get().salary().isPresent() || !currencies.contains(lastEmployment.get().salary().get().currency()) ||
          lastEmployment.get().salary().get().amount().compareTo(salaryThreshold) < 0) {
        return Optional.empty();
      }

      return Optional.of(new InsuranceCoefficient(50));
    };
  }

  public static InsuranceCalculator<Person> accommodationEmergencyInsurance(Set<Accommodation.EmergencyStatus> statuses) {
    return person -> {
      if (person == null || person.accommodations() == null) {
        return Optional.empty();
      }

      Optional<Accommodation> smallestAccommodation = person.accommodations().stream().min(Comparator.comparing(Accommodation::area));
      if (smallestAccommodation.isEmpty() || !smallestAccommodation.get().emergencyStatus().isPresent() || !statuses.contains(smallestAccommodation.get().emergencyStatus().get())) {
        return Optional.empty();
      }

      int totalStatuses = Accommodation.EmergencyStatus.values().length;
      int coefficient;
      switch (smallestAccommodation.get().emergencyStatus().get()) {
        case NONE:
          coefficient = 100;
          break;
        case LOW:
          coefficient = 80;
          break;
        case MEDIUM:
          coefficient = 60;
          break;
        case HIGH:
          coefficient = 40;
          break;
        case CRITICAL:
          coefficient = 20;
          break;
        default:
          coefficient = 0;
      }
      return Optional.of(new InsuranceCoefficient(coefficient));
    };
  }

  public static InsuranceCalculator<Person> injuryAndRentDependentInsurance(BigInteger rentThreshold) {
    return person -> {
      if (person == null || person.injuries() == null || person.accommodations() == null) {
        return Optional.empty();
      }

      Optional<Injury> injury = person.injuries().stream().findFirst();
      if (injury.isEmpty() || injury.get().culprit().isEmpty()) {
        return Optional.empty();
      }

      Optional<Accommodation> largestAccommodation = person.accommodations().stream().max(Comparator.comparing(Accommodation::area));
      if (largestAccommodation.isEmpty() || largestAccommodation.get().rent().isEmpty() ||
          largestAccommodation.get().rent().get().currency() != Currency.GBP) {
        return Optional.empty();
      }

      int coefficient = Math.min(100, largestAccommodation.get().rent().get().amount().multiply(BigInteger.valueOf(100)).divide(rentThreshold).intValue());
      return Optional.of(new InsuranceCoefficient(coefficient));
    };
  }
}