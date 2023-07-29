package EsturturasRepetitivas;
import java.util.Scanner;

public class EstructuraRepetitivaWhile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int notasMayoresOIgualesA7 = 0;
        int notasMenoresA7 = 0;

        System.out.println("Ingrese las 10 notas de los alumnos:");

        for (int i = 1; i <= 10; i++) {
            System.out.print("Nota del alumno " + i + ": ");
            int nota = scanner.nextInt();

            if (nota >= 7) {
                notasMayoresOIgualesA7++;
            } else {
                notasMenoresA7++;
            }
        }

        System.out.println("Cantidad de alumnos con notas mayores o iguales a 7: " + notasMayoresOIgualesA7);
        System.out.println("Cantidad de alumnos con notas menores a 7: " + notasMenoresA7);
    }

}
