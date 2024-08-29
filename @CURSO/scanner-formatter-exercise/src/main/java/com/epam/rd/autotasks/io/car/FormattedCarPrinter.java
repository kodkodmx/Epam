package com.epam.rd.autotasks.io.car;

import java.util.List;

import com.epam.rd.autotasks.io.car.model.Car;

public class FormattedCarPrinter {

    public void print(List<Car> cars) {
        for (Car car : cars) {
            System.out.printf("%10s %10s %5d ccm %3d kw%6.2f sec%n",
                    car.getBrand(),
                    car.getModel(),
                    car.getCylinderCapacityCcm(),
                    car.getPerformanceKwh(),
                    car.getAccelerationSec());
        }
    }
}
