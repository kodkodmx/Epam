# Cars

The purpose of this exercise is to practice the usage of java Java `Scanner` and `Formatter` classes.

Estimated workload of this exercise is _60 minutes_.

## Description

### Car Domain

The Car class represents a car with some attributes: 
- brand
- model
- cylinder capacity
- performance
- acceleration

### Read CSV

The first part of the exercise is to read cars from a CSV (comma-separated values) file.
The CSV file contains the car attributes in the same order as listed above.

Implement the `CarCsvReader` class's `readCars(File file)` method to do the job.

- Read the file content line-by-line with `BufferedReader`. Then create a `Scanner` and configure it to use comma (,) as delimiter.

Input file example:

```
Toyota,Corolla,1599,75,6.8
Kia,Rio,1225,68,10.3
```

### Formatted Printing

Once the cars are in memory, print them to the console in the following way

- brand: 10 characters width
- model: 10 characters width
- cylinder capacity: 5 digits, add measurement unit: ccm
- performance: 3 digits, add measurement unit: kw
- acceleration: 5 digits, add measurement unit: sec

Implement the `FormattedCarPrinter` class's `print(List<Car> cars)` method.

Output example, given the above list of cars:

```
    Toyota    Corolla  1599 ccm  75 kw  6.80 sec
       Kia        Rio  1250 ccm  68 kw 10.30 sec
```

### The Application

The `Application` class is already implemented, which delegates
the reading task to `CarCsvReader` class, and the formatting task to `FormattedCarPrinter` class.

The starter project includes a sample `cars.csv` file in `data` directory. The application has to read that file.
