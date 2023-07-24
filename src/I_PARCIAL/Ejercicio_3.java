package I_PARCIAL;
import java.util.Scanner;

public class Ejercicio_3 {
public static void main(String[] args) {
    System.out.println("Ejemplo de estructura condicional Simple");
    Scanner teclado = new Scanner(System.in);
    float sueldo;
    System.out.println("Ingrese el sueldo del empleado");
    sueldo = teclado.nextFloat();
    if (sueldo > 3000) {
        System.out.println("Debe pagar impuestos");
    }
    }
}
