// Interfaz funcional
interface Calculator {
    int calculate(int a, int b);
}

// Clase con nombre que implementa la interfaz Calculator para suma
class Addition implements Calculator {
    @Override
    public int calculate(int a, int b) {
        return a + b;
    }
}

// Clase con nombre que implementa la interfaz Calculator para multiplicación
class Multiplication implements Calculator {
    @Override
    public int calculate(int a, int b) {
        return a * b;
    }
}

public class NamedClassExample {
    public static void main(String[] args) {
        // Crear instancias de las clases con nombre
        Calculator addition = new Addition();
        Calculator multiplication = new Multiplication();

        // Ejemplos de uso
        System.out.println("Suma: " + addition.calculate(5, 3)); // Imprime: Suma: 8
        System.out.println("Multiplicación: " + multiplication.calculate(5, 3)); // Imprime: Multiplicación: 15
    }
}