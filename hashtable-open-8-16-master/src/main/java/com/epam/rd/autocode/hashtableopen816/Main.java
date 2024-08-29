package com.epam.rd.autocode.hashtableopen816;

public class Main {
    public static void main(String[] args) {
        HashtableOpen8to16 hashtable = HashtableOpen8to16.getInstance();

        // Prueba de inserción básica
        testInsertion(hashtable);

        // Prueba de actualización de valores existentes
        testUpdateValues(hashtable);

        // Prueba de búsqueda de una clave inexistente
        testSearchNonExistentKey(hashtable);

        // Prueba de eliminación de una clave existente
        testRemoveExistingKey(hashtable);

        // Prueba de eliminación de una clave inexistente
        testRemoveNonExistentKey(hashtable);

        // Prueba de capacidad inicial y aumento al insertar más elementos
        testCapacityIncrease(hashtable);

        // Prueba de eliminación y reducción de capacidad
        testCapacityDecrease(hashtable);

        // Prueba de manejo de colisiones
        testCollisionHandling(hashtable);

        // Prueba de inserción de más de 16 elementos
        testInsertMoreThanMaxCapacity(hashtable);

        // Prueba de inserción de valores extremos y límites (con capacidad suficiente)
        testExtremeValuesInsertion(hashtable);
    }

    private static void testInsertion(HashtableOpen8to16 hashtable) {
        hashtable.insert(1, "Value 1");
        hashtable.insert(2, "Value 2");
        hashtable.insert(3, "Value 3");
        System.out.println("Prueba básica de inserción:");
        System.out.println("Size: " + hashtable.size()); // Debe ser 3
        printKeysAndValues(hashtable);
    }

    private static void testUpdateValues(HashtableOpen8to16 hashtable) {
        hashtable.insert(1, "Updated Value 1");
        System.out.println("\nPrueba de actualización de valores:");
        System.out.println("Valor de la clave 1: " + hashtable.search(1)); // Debe ser "Updated Value 1"
        printKeysAndValues(hashtable);
    }

    private static void testSearchNonExistentKey(HashtableOpen8to16 hashtable) {
        System.out.println("\nPrueba de búsqueda de clave inexistente:");
        System.out.println("Valor de la clave 99: " + hashtable.search(99)); // Debe ser null
    }

    private static void testRemoveExistingKey(HashtableOpen8to16 hashtable) {
        hashtable.remove(2);
        System.out.println("\nPrueba de eliminación de clave existente:");
        System.out.println("Size: " + hashtable.size()); // Debe ser 2
        printKeysAndValues(hashtable);
    }

    private static void testRemoveNonExistentKey(HashtableOpen8to16 hashtable) {
        hashtable.remove(99);
        System.out.println("\nPrueba de eliminación de clave inexistente:");
        System.out.println("Size: " + hashtable.size()); // Debe seguir siendo 2
        printKeysAndValues(hashtable);
    }

    private static void testCapacityIncrease(HashtableOpen8to16 hashtable) {
        System.out.println("\nPrueba de aumento de capacidad:");
        for (int i = 4; i <= 9; i++) {
            hashtable.insert(i, "Value " + i);
        }
        System.out.println("Size después de insertar más elementos: " + hashtable.size()); // Debe ser 8
        printKeysAndValues(hashtable);
    }

    private static void testCapacityDecrease(HashtableOpen8to16 hashtable) {
        System.out.println("\nPrueba de reducción de capacidad:");
        hashtable.remove(9);
        hashtable.remove(8);
        System.out.println("Size después de eliminar elementos: " + hashtable.size()); // Debe ser 6
        printKeysAndValues(hashtable);
    }

    private static void testCollisionHandling(HashtableOpen8to16 hashtable) {
        System.out.println("\nPrueba de manejo de colisiones:");
        hashtable.insert(18, "Collision 1"); // 18 colisiona con 10
        hashtable.insert(26, "Collision 2"); // 26 colisiona con 18
        System.out.println("Size después de manejar colisiones: " + hashtable.size()); // Debe ser 8
        printKeysAndValues(hashtable);
    }

    private static void testInsertMoreThanMaxCapacity(HashtableOpen8to16 hashtable) {
        System.out.println("\nPrueba de inserción de más de 16 elementos (debe lanzar una excepción):");
        try {
            for (int i = 11; i <= 17; i++) {
                hashtable.insert(i, "Value " + i);
            }
        } catch (IllegalStateException e) {
            System.out.println("Excepción capturada correctamente: " + e.getMessage());
        }
        printKeysAndValues(hashtable);
    }

    private static void testExtremeValuesInsertion(HashtableOpen8to16 hashtable) {
        System.out.println("\nPrueba de inserción de valores extremos y límites:");
        try {
            hashtable.remove(17);
            hashtable.remove(16);
            hashtable.remove(15);
            hashtable.insert(Integer.MIN_VALUE, "Min Value");
            hashtable.insert(Integer.MAX_VALUE, "Max Value");
            System.out.println("Size después de insertar valores extremos: " + hashtable.size());
            System.out.println("Valor de la clave Integer.MIN_VALUE: " + hashtable.search(Integer.MIN_VALUE)); // Debe ser "Min Value"
            System.out.println("Valor de la clave Integer.MAX_VALUE: " + hashtable.search(Integer.MAX_VALUE)); // Debe ser "Max Value"
        } catch (IllegalStateException e) {
            System.out.println("Excepción capturada: " + e.getMessage());
        }
        printKeysAndValues(hashtable);
    }

    private static void printKeysAndValues(HashtableOpen8to16 hashtable) {
        int[] keys = hashtable.keys();
        for (int key : keys) {
            System.out.println("Clave: " + key + ", Valor: " + hashtable.search(key));
        }
    }
}
