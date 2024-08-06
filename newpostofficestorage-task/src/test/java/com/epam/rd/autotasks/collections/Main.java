package com.epam.rd.autotasks.collections;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Crear algunas cajas de ejemplo
        Box box1 = new Box("John Doe", "Jane Doe", 1.0, 0.5, new BigDecimal("10.00"), "CityA", 101);
        Box box2 = new Box("Alice", "Bob", 2.0, 0.6, new BigDecimal("20.00"), "CityB", 102);
        Box box3 = new Box("Charlie", "Diana", 1.5, 0.4, new BigDecimal("15.00"), "CityC", 103);
        Box box4 = new Box("Eve", "Frank", 0.8, 0.3, new BigDecimal("5.00"), "CityD", 104);

        // Crear una colección inicial de cajas
        List<Box> initialBoxes = new ArrayList<>();
        initialBoxes.add(box1);
        initialBoxes.add(box2);
        initialBoxes.add(box3);
        initialBoxes.add(box4);

        // Crear una instancia de NewPostOfficeStorageImpl con la colección inicial
        NewPostOfficeStorageImpl storage = new NewPostOfficeStorageImpl(initialBoxes);

        // Mostrar el estado inicial
        System.out.println("Estado inicial:");
        printStorage(storage);

        // Cajas para llevar
        List<Box> carryOutBoxes = new ArrayList<>();
        carryOutBoxes.add(box1);
        carryOutBoxes.add(box3);

        // Llevar cajas y verificar el resultado
        System.out.println("\nLlevar cajas: " + carryOutBoxes);
        boolean changed = storage.carryOutBoxes(carryOutBoxes);
        System.out.println("Se ha cambiado el almacenamiento: " + changed);

        // Mostrar el estado después de llevar cajas
        System.out.println("\nEstado después de llevar cajas:");
        printStorage(storage);

        // Volver a la configuración inicial para otro test
        storage = new NewPostOfficeStorageImpl(initialBoxes);

        // Llevar otras cajas y verificar el resultado
        List<Box> carryOutBoxes2 = new ArrayList<>();
        carryOutBoxes2.add(box2);
        carryOutBoxes2.add(box4);
        System.out.println("\nLlevar cajas: " + carryOutBoxes2);
        boolean changed2 = storage.carryOutBoxes(carryOutBoxes2);
        System.out.println("Se ha cambiado el almacenamiento: " + changed2);

        // Mostrar el estado después de llevar otras cajas
        System.out.println("\nEstado después de llevar otras cajas:");
        printStorage(storage);
    }

    private static void printStorage(NewPostOfficeStorageImpl storage) {
        // Imprimir el contenido del almacenamiento
        List<Box> boxes = storage.searchBoxes(box -> true); // Obtener todas las cajas
        for (Box box : boxes) {
            System.out.println(box);
        }
    }
}
