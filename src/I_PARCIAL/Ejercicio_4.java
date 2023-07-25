//Author Eduardo Ajuchan
package I_PARCIAL;
import java.util.Scanner;
public class Ejercicio_4 {
    public static void main(String[] args) {
        System.out.println("Ejemplo de estructura condicional compuesta");
        Scanner teclado = new Scanner(System.in);
        int num1, num2;
        System.out.println("Ingrese el primer número");
        num1 = teclado.nextInt();
        System.out.println("Ingrese el segundo número");
        num2 = teclado.nextInt();
        if (num1 > num2) {
            System.out.println("El número mayor es: " + num1);
        } else {
            System.out.println("El número mayor es: " + num2);
        }
    }
}
