package EsturturasRepetitivas;

public class EstructuraRepetitivaFor {
    public static void main(String[] args) {
        int numero = 5;
        int limite = 50;

        System.out.println("Tabla de multiplicar del 5 (del 5 al 50):");
        System.out.println("--------------------------------------");

        for (int i = numero; i <= limite; i += numero) {
            System.out.println(numero + " x " + (i / numero) + " = " + i);
        }
    }

}
