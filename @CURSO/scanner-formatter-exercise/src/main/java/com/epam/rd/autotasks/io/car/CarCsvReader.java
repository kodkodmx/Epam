package com.epam.rd.autotasks.io.car;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.epam.rd.autotasks.io.car.model.Car;

public class CarCsvReader {

    public List<Car> readCars(File file) {
        List<Car> cars = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Scanner scanner = new Scanner(line);
                scanner.useDelimiter(",");

                String brand = scanner.next();
                String model = scanner.next();
                int cylinderCapacity = scanner.nextInt();
                int performance = scanner.nextInt();
                double acceleration = scanner.nextDouble();

                Car car = new Car(brand, model, cylinderCapacity, performance, acceleration);
                cars.add(car);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cars;
    }
}
