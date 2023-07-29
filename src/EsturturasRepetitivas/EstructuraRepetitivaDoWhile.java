package EsturturasRepetitivas;
import java.util.Scanner;

public class EstructuraRepetitivaDoWhile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        int count = 0;

        System.out.println("Ingrese números para calcular el promedio.");
        System.out.println("Ingrese 0 para terminar la carga de valores.");

        int numero = scanner.nextInt();

        while (numero != 0) {
            sum += numero;
            count++;

            numero = scanner.nextInt();
        }

        if (count == 0) {
            System.out.println("No se ingresaron valores para calcular el promedio.");
        } else {
            double promedio = (double) sum / count;
            System.out.println("El promedio es: " + promedio);
        }
    }
}
