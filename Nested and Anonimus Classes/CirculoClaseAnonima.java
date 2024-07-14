// Interfaz para figuras geométricas
interface Circulo {
    double radio(int radius);
}

public class CirculoClaseAnonima {
    public static void main(String[] args) {
        
        Circulo area = new Circulo(){
            @Override
            public double radio(int radius) {
                return Math.PI * radius * radius;
            }
        };

        Circulo perimetro = new Circulo(){        
            @Override
            public double radio(int radius) {
                return 2 * Math.PI * radius;
            }
        };
       

        // Calcular y mostrar área y perímetro
        System.out.println("Círculo:");
        System.out.println("Área: " + area.radio(5));
        System.out.println("Perímetro: " + perimetro.radio(5));
    }
}
