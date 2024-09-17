package edu.epam.fop.lambdas;

import java.util.function.DoubleFunction;

public interface PercentageFormatter {

  DoubleFunction<String> INSTANCE = value -> {
    double percentage = value * 100;
    if (percentage == (long) percentage) {
      return String.format("%d %%", (long) percentage);
    } else {
      return String.format("%.1f %%", percentage);
    }
  };

  public static void main(String[] args) {
    // Ejemplos de prueba
    System.out.println(PercentageFormatter.INSTANCE.apply(0.42));    // 42.0 %
    System.out.println(PercentageFormatter.INSTANCE.apply(0.427));   // 42.7 %
    System.out.println(PercentageFormatter.INSTANCE.apply(0.4273));  // 42.7 %
    System.out.println(PercentageFormatter.INSTANCE.apply(0.4275));  // 42.8 %
    System.out.println(PercentageFormatter.INSTANCE.apply(-0.4275)); // -42.8 %
    System.out.println(PercentageFormatter.INSTANCE.apply(0.5));     // 50 %
    System.out.println(PercentageFormatter.INSTANCE.apply(-0.43));   // -43 %
  }
}