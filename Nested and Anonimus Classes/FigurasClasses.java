// Interfaz para figuras geométricas
interface Figure {
    double calculateArea();
    double calculatePerimeter();
}

// Clase para círculos
class Circle implements Figure {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
}

// Clase para rectángulos
class Rectangle implements Figure {
    private final double base;
    private final double height;

    public Rectangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return base * height;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (base + height);
    }
}

public class FigurasClasses {
    public static void main(String[] args) {
        // Crear instancias de círculo y rectángulo
        Circle circle = new Circle(5);
        Rectangle rectangle = new Rectangle(4, 6);

        // Calcular y mostrar área y perímetro
        System.out.println("Círculo:");
        System.out.println("Área: " + circle.calculateArea());
        System.out.println("Perímetro: " + circle.calculatePerimeter());

        System.out.println("\nRectángulo:");
        System.out.println("Área: " + rectangle.calculateArea());
        System.out.println("Perímetro: " + rectangle.calculatePerimeter());
    }
}
