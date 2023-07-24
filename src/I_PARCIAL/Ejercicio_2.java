package I_PARCIAL;
import java.util.Scanner;

public class Ejercicio_2 {
    public static void main(String[] args) {
        System.out.println("Ejemplo de estructura secuencial");
        Scanner teclado = new Scanner(System.in);
        int num1, num2, suma, multiplicacion;
        System.out.println("Ingrese el primer número");
        num1 = teclado.nextInt();
        System.out.println("Ingrese el segundo número");
        num2 = teclado.nextInt();
        suma = num1 + num2;
        multiplicacion = num1 * num2;
        System.out.println("La suma es: " + suma);
        System.out.println("La multiplicación es: " + multiplicacion);
    }
}
