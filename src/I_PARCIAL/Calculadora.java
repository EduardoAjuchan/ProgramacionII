package I_PARCIAL;
import java.util.Scanner;
public class Calculadora {
    public static void main(String[] args) {
        int opcion;
        Scanner teclado = new Scanner(System.in);
        do {
            System.out.println("Ingrese la operación que desea realizar");
            System.out.println("1. Suma");
            System.out.println("2. Resta");
            System.out.println("3. Multiplicación");
            System.out.println("4. División");
            System.out.println("5. Salir");
            opcion = teclado.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el primer número");
                    int num1 = teclado.nextInt();
                    System.out.println("Ingrese el segundo número");
                    int num2 = teclado.nextInt();
                    System.out.println("El resultado de la suma es: " + (num1 + num2));
                    break;
                case 2:
                    System.out.println("Ingrese el primer número");
                    num1 = teclado.nextInt();
                    System.out.println("Ingrese el segundo número");
                    num2 = teclado.nextInt();
                    System.out.println("El resultado de la resta es: " + (num1 - num2));
                    break;
                case 3:
                    System.out.println("Ingrese el primer número");
                    num1 = teclado.nextInt();
                    System.out.println("Ingrese el segundo número");
                    num2 = teclado.nextInt();
                    System.out.println("El resultado de la multiplicación es: " + (num1 * num2));
                    break;
                case 4:
                    System.out.println("Ingrese el primer número");
                    num1 = teclado.nextInt();
                    System.out.println("Ingrese el segundo número");
                    num2 = teclado.nextInt();
                    System.out.println("El resultado de la división es: " + (num1 / num2));
                    break;
                case 5:
                    System.out.println("Gracias por utilizar la calculadora");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (opcion != 5);
    }
    }
