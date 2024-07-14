// Interfaz funcional
interface Calculator {
    int calculate(int a, int b);
}

public class AnonymousClassExample {
    public static void main(String[] args) {
        // Usando una clase anónima para implementar la interfaz Calculator
        Calculator addition = new Calculator() {
            @Override
            public int calculate(int a, int b) {
                return a + b;
            }
        };

        Calculator multiplication = new Calculator() {
            @Override
            public int calculate(int a, int b) {
                return a * b;
            }
        };

        // Ejemplos de uso
        System.out.println("Suma: " + addition.calculate(5, 3)); // Imprime: Suma: 8
        System.out.println("Multiplicación: " + multiplication.calculate(5, 3)); // Imprime: Multiplicación: 15
    }
}
