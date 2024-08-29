package com.epam.rd.autotasks.io.car;

import com.epam.rd.autotasks.io.car.model.Car;

import java.io.File;
import java.util.List;

public class Application {

    private static final CarCsvReader fileScanner = new CarCsvReader();
    private static final FormattedCarPrinter formattedCarPrinter = new FormattedCarPrinter();

    public static void main(String[] args) {
        File inputFile = new File("data/cars.csv");
        List<Car> cars = fileScanner.readCars(inputFile);
        formattedCarPrinter.print(cars);
    }

}
