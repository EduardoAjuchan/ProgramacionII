package I_PARCIAL;
import java.util.Scanner;
public class CalculadoraMetodos {
    Scanner Entrada = new Scanner (System.in);
    public void Sumar(){
        float num1, num2, resultado;
        System.out.println("Ingrese el primer numero: ");
        num1 = Entrada.nextFloat();
        System.out.println("Ingrese el segundo numero: ");
        num2 = Entrada.nextFloat();
        resultado = num1 + num2;
        System.out.println("El resultado de la suma es: " + resultado);
    }
    public void Restar(){
        float num1, num2, resultado;
        System.out.println("Ingrese el primer numero: ");
        num1 = Entrada.nextFloat();
        System.out.println("Ingrese el segundo numero: ");
        num2 = Entrada.nextFloat();
        resultado = num1 - num2;
        System.out.println("El resultado de la resta es: " + resultado);
    }
    public void Multiplicar(){
        float num1, num2, resultado;
        System.out.println("Ingrese el primer numero: ");
        num1 = Entrada.nextFloat();
        System.out.println("Ingrese el segundo numero: ");
        num2 = Entrada.nextFloat();
        resultado = num1 * num2;
        System.out.println("El resultado de la multiplicacion es: " + resultado);
    }
    public void Dividir(){
        float num1, num2, resultado;
        System.out.println("Ingrese el primer numero: ");
        num1 = Entrada.nextFloat();
        System.out.println("Ingrese el segundo numero: ");
        num2 = Entrada.nextFloat();
        if (num1 >= 0 && num2 > 0) {
            System.out.println("El resultado de la división es: " + (num1 / num2));
        } else if (num2 == 0) {
            System.out.println("Error al dividir por 0");
        } else {
            System.out.println("Error: Los numeros no deben ser negativos");
        }
    }
    public static void main (String[] args){
        Scanner Entrada = new Scanner (System.in);
        CalculadoraMetodos Calculadora = new CalculadoraMetodos();
        int opcion;
        do{
            System.out.println("Bienvenido a la calculadora");
            System.out.println("1. Sumar");
            System.out.println("2. Restar");
            System.out.println("3. Multiplicar");
            System.out.println("4. Dividir");
            System.out.println("5. Salir");
            System.out.println("Ingrese la opcion que desea realizar: ");
            opcion = Entrada.nextInt();
            switch (opcion){
                case 1:
                    Calculadora.Sumar();
                    break;
                case 2:
                    Calculadora.Restar();
                    break;
                case 3:
                    Calculadora.Multiplicar();
                    break;
                case 4:
                    Calculadora.Dividir();
                    break;
                case 5:
                    System.out.println("Gracias por usar la calculadora");
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }while (opcion != 5);
    }


}
