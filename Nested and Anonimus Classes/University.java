// Archivo: University.java
public class University {
    private String name;

    public University(String name) {
        this.name = name;
    }

    // Clase no estática anidada Student
    public class Student {
        private String studentName;
        private int age;

        public Student(String studentName, int age) {
            this.studentName = studentName;
            this.age = age;
        }

        public void display() {
            System.out.println("University: " + University.this.name);
            System.out.println("Student Name: " + studentName);
            System.out.println("Student Age: " + age);
        }
    }

    // Clase estática anidada Masters
    public static class Masters {
        private String masterName;
        private String specialization;

        public Masters(String masterName, String specialization) {
            this.masterName = masterName;
            this.specialization = specialization;
        }

        public void display(University university) {
            System.out.println("University: " + university.name);
            System.out.println("Master's Name: " + masterName);
            System.out.println("Specialization: " + specialization);
        }
    }

    public static void main(String[] args) {
        
        University myUniversity = new University("MyUni");
        University myUniversity2 = new University("MyUni2");
        University myUniversity3 = new University("MyUni3");

        // Crear estudiantes usando la clase interna Student
        University.Student student1 = myUniversity.new Student("Alice", 20);
        University.Student student2 = myUniversity.new Student("Bob", 22);

        // Crear maestro usando la clase estática anidada Masters
        University.Masters master1 = new University.Masters("Dr. Smith", "Computer Science");

        // Mostrar información de estudiantes y maestro
        System.out.println("Student information:");
        student1.display();
        System.out.println();
        student2.display();

        System.out.println("\nMaster's information:");
        master1.display(myUniversity);
        System.out.println();
        master1.display(myUniversity2);
        System.out.println();
        master1.display(myUniversity3); 
    }
}
